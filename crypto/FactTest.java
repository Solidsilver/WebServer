package WebServer.crypto;

import java.math.BigInteger;
import java.util.Scanner;

public class FactTest {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int in;
        BigInteger fact;
        do {
            System.out.print("Factorial of: ");
            in = Integer.parseInt(kb.nextLine());
            fact = fact(in);
            System.out.println("Fact: " + fact);
            System.out.println("Length: " + Math.log(fact.bitCount())/Math.log(2)+1);
        } while (in > 0);
    }

    public static BigInteger fact(int num) {
        BigInteger ret = BigInteger.ONE;
        for (BigInteger x = BigInteger.valueOf(num); !x.equals(BigInteger.ONE); x = x.subtract(BigInteger.ONE)) {
            //System.out.println("x is: " + x);
            ret = ret.multiply(x);
        }
        return ret;
    }
}