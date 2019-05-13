package WebServer.crypto;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.*;
import java.math.*;

public class DiffieHelmanTest {
    public static void main(String[] args) {
        System.out.println("******** Diffie-Hellman key exchange: *******");
        printLine(128);

        BigInteger g = new BigInteger(32, new Random());

        BigInteger n = new BigInteger(4096, new Random());

        System.out.println("g: " + g + "\nn: " + n);
        printLine(128);
        KeyPair bob = KeyPair.generateKeyPair(512);
        KeyPair alice = KeyPair.generateKeyPair(512);


        BigInteger sec1 = CryptMethods.diffieHelman(g, n, bob.getPriv());
        BigInteger sec2 = CryptMethods.diffieHelman(g, n, alice.getPriv());
        System.out.println("\nSec1:\n " + sec1 + "\nSec2:\n " + sec2);
        System.out.println();
        System.out.println("\nsecondarySame: " + sec1.equals(sec2));
        printLine(128);

        BigInteger shared1 = CryptMethods.diffieHelman(sec2, n, bob.getPriv());
        BigInteger shared2 = CryptMethods.diffieHelman(sec1, n, alice.getPriv());
        System.out.println("Shared1:\n " + shared1 + "\nShared2:\n " + shared2);
        printLine(128);
        System.out.println("\nsharedSame: " + shared1.equals(shared2));
    }

    private static void printLine(int len) {
        for (int x = 0; x < len; x++) {
            System.out.print("-");
        }
        System.out.println();
    }
}