package WebServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import WebServer.mime.*;

final class HttpRequest implements Runnable {
    final static String CRLF = "\r\n";
    private Socket socket;
    private MIMETypeList mTypes;

    public HttpRequest(DataPass fromServer) throws Exception {
        this.socket = fromServer.getSocket();
        this.mTypes = fromServer.getTypeList();
    }

    // Implement the run() method of the Runnable interface.
    public void run() {
        try {
            processRequest();
        } catch (IOException io) {
            System.out.print("Socket I/O Error: ");
            System.out.println(io);
        }
        catch (Exception e) {
            System.out.println("There was an error processing the request");
            System.out.println(e);
        }
    }

    private void processRequest() throws Exception {
        MIMETypeList typeList = new MIMETypeList();

        // Get a reference to the socket's input and output streams.
        InputStream is = this.socket.getInputStream();
        DataOutputStream os = new DataOutputStream(this.socket.getOutputStream());
        // Set up input stream filters.
        InputStreamReader inRead = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(inRead);

        // Get the request line of the HTTP request message.
        String requestLine = br.readLine();

        // Display the request line.
        System.out.println();
        System.out.println(requestLine);
        // Get and display the header lines.
        String headerLine = null;
        while ((headerLine = br.readLine()).length() != 0) {
            System.out.println(headerLine);
        }

        if (requestLine.startsWith("GET")) {
            getRequest(requestLine, os);
        } else {
            os.writeBytes("HTTP/1.0 501 Not Implemented");
            os.writeBytes(CRLF);
        }
        // Close streams and socket.
        os.close();
        br.close();
        socket.close();
    }

    private void getRequest(String requestLine, DataOutputStream os) throws Exception {
        // Extract the filename from the request line.
        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken(); // skip over the method, which should be "GET"
        String fileName = tokens.nextToken();
        if (fileName.equals("/")) {
            fileName += "index.html";
        }

        // Prepend a "." so that file request is within the current directory.
        fileName = "." + fileName;
        // Open the requested file.
        FileInputStream fis = null;
        boolean fileExists = true;
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            fileExists = false;
        }
        // Construct the response message.
        String statusLine = null;
        String contentTypeLine = null;
        String entityBody = null;
        if (fileExists) {
            statusLine = "HTTP/1.0 200 OK";
            contentTypeLine = "Content-type: " + this.mTypes.contentType(fileName) + CRLF;
        } else {
            statusLine = "HTTP/1.0 404 Not Found";
            contentTypeLine = "Content-Type: text/html; charset=utf-8";
            entityBody = "<HTML>" + "<HEAD><TITLE>Not Found</TITLE></HEAD>" + "<BODY>Not Found</BODY></HTML>";
        }
        // Send the status line.
        os.writeBytes(statusLine);

        // Send the content type line.
        os.writeBytes(contentTypeLine);

        // Send a blank line to indicate the end of the header lines.
        os.writeBytes(CRLF);
        // Send the entity body.
        if (fileExists) {
            sendBytes(fis, os);
            fis.close();
        } else {
            os.writeBytes(entityBody);
        }
    }

    private static void sendBytes(InputStream is, OutputStream os) throws Exception {
        // Construct a 1K buffer to hold bytes on their way to the socket.
        byte[] buffer = new byte[1024];
        int bytes = 0;

        // Copy requsted file into the socket's output stream.
        while ((bytes = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }

}