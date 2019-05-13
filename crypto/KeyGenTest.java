package WebServer.crypto;

import java.math.BigInteger;
import java.util.Random;

public class KeyGenTest {
    public static void main(String[] args) {
        KeyPair bob = KeyPair.generateKeyPair(512);

        BigInteger message = new BigInteger(50, new Random());

        System.out.println("Original Message: ");
        println(message);

        BigInteger encrypted = crypt(message, bob.getPub());
        System.out.println("Encrypted Message: ");
        println(encrypted);

        BigInteger decrypted = crypt(encrypted, bob.getPriv());
        System.out.println("Decrypted Message: ");
        println(decrypted);

    }

    public static BigInteger crypt(BigInteger message, Key key) {
        return message.modPow(key.getExp(), key.getN());
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }


}