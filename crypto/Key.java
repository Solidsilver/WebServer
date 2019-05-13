package WebServer.crypto;

import java.math.BigInteger;

public class Key implements Serializable {
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