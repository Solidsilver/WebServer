package WebServer;

import java.net.*;
import WebServer.mime.*;

public final class WebServer {
    public static void main(String argv[]) throws Exception {
        //Default port
        int port = 4756;

        //Check for port as CLI argument
        if (argv.length > 0) {
            try {
                port = Integer.parseInt(argv[0]);
            } catch (Exception e) {
                port = 4756;
            }
        }

        ServerSocket serverSocket = new ServerSocket(port);

        // Load up list of MIME types.
        MIMETypeList mTypes = new MIMETypeList();
        System.out.println("Server started on port " + port);

        // Process HTTP service requests in an infinite loop.
        while (true) {
            Socket clientSocket = serverSocket.accept();
            HttpRequest request = new HttpRequest(new DataPass(clientSocket, mTypes));

            Thread thread = new Thread(request);
            // Start the thread.
            thread.start();

        }
    }
}
