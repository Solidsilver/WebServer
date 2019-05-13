package WebServer.crypto;

public class KeyGenerationError extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KeyGenerationError() {
        super();
    }
    public KeyGenerationError(String message) {
        super(message);
    }
}