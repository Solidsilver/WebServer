package WebServer;

import java.net.*;

public final class WebServer {
    public static void main(String argv[]) throws Exception {
        int port = 80;

        ServerSocket serverSocket = new ServerSocket(port);

        //Load up list of MIME types.
        MIMETypeList mTypes = new MIMETypeList();

        // Process HTTP service requests in an infinite loop.
        while (true) {
            // Listen for a TCP connection request.
            Socket clientSocket = serverSocket.accept();
            // Construct an object to process the HTTP request message.
            HttpRequest request = new HttpRequest(new DataPass(clientSocket, mTypes));

            // Create a new thread to process the request.
            Thread thread = new Thread(request);

            // Start the thread.
            thread.start();

        }
    }
}
