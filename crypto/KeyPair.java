package WebServer.crypto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class KeyPair implements Serializable {
    private static final long serialVersionUID = 1L;
    private Key privKey;
    private Key pubKey;

    private KeyPair(Key priv, Key pub) {
        this.privKey = priv;
        this.pubKey = pub;
    }

    public Key getPub() {
        return this.pubKey;
    }

    public Key getPriv() {
        return this.privKey;
    }

    public static KeyPair generateKeyPair(int primeSize) {
        Key privKey = null;
        Key pubKey = null;
        BigInteger p = BigInteger.probablePrime(primeSize, new Random());
        BigInteger q = BigInteger.probablePrime(primeSize, new Random());
        BigInteger n = p.multiply(q);
        BigInteger ph = lcm(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
        BigInteger e;

        do {
            e = BigInteger.probablePrime(ph.bitLength() - 1, new Random());
        } while (!ph.gcd(e).equals(BigInteger.ONE));

        BigInteger d = e.modInverse(ph);

        pubKey = new Key(n, e);
        privKey = new Key(n, d);

        if (privKey == null || pubKey == null) {
            throw new KeyGenerationError("a key was null");
        }
        return new KeyPair(privKey, pubKey);
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }

}