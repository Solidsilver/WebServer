package WebServer.crypto;

import java.util.Random;
import java.util.concurrent.*;

public class TestServer {
    private KeyPair kPair;

    public TestServer() {
        this.kPair = KeyPair.generateKeyPair(1024);
    }

    public Key getPubKey() {
        return this.kPair.getPub();
    }
}