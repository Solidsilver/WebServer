package WebServer;

import java.net.Socket;

final class DataPass {
    Socket socket;
    MIMETypeList mType;

    public DataPass(Socket s, MIMETypeList m) {
        this.socket = s;
        this.mType = m;
    }
}