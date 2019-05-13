package WebServer.crypto;

import java.math.BigInteger;
import java.io.Serializable;


public class Key implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigInteger n;
    private BigInteger exp;

    public Key(BigInteger n, BigInteger exp) {
        this.n = n;
        this.exp = exp;
    }

    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getExp() {
        return this.exp;
    }
}