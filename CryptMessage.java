package WebServer;

import java.math.BigInteger;
import java.util.Random;

import WebServer.crypto.CryptMethods;
import WebServer.crypto.Key;
import WebServer.crypto.KeyPair;

public class CryptMessage {
    public static void main(String[] args) {
        String msg1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        //BigInteger n = new BigInteger(81920, new Random());
        System.out.println("Original Message: " + msg1);
        BigInteger msg1_bigint = strToBigInt(msg1);
        System.out.println("Message as BigInt: " + msg1_bigint);
        KeyPair kp = KeyPair.generateKeyPair(2018);
        BigInteger msg1_crypt = CryptMethods.rsa_crypt(msg1_bigint, kp.getPub());
        System.out.println("Encrypted BigInt: " + msg1_crypt);
        BigInteger msg1_decrypt = CryptMethods.rsa_crypt(msg1_crypt, kp.getPriv());
        System.out.println("Decypted message as BigInt: " + msg1_decrypt);
        System.out.println("Decrypted: " + bigIntToStr(msg1_decrypt));
        //System.out.println(strToAscii(msgO));
    }

    public static BigInteger strToAscii(String s) {
        char[] chArr = s.toCharArray();
        BigInteger mult = BigInteger.ONE;
        BigInteger ret = BigInteger.ZERO;
        for (int x = (chArr.length-1); x >= 0; x--) {
            char c = chArr[x];
            ret = ret.add((new BigInteger(((int)c) + "")).multiply(mult));
            mult = mult.multiply(BigInteger.TEN.pow(2));
        }
        return ret;
    }
    public static BigInteger strToBigInt(String s) {
        return new BigInteger(s.getBytes());
    }

    public static String bigIntToStr(BigInteger b) {
        return new String(b.toByteArray());
    }
}