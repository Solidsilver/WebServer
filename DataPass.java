package WebServer;

import java.net.Socket;

final class DataPass {
    private Socket socket;
    private MIMETypeList mType;

    public DataPass(Socket s, MIMETypeList m) {
        this.socket = s;
        this.mType = m;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public MIMETypeList getTypeList() {
        return this.mType;
    }
}