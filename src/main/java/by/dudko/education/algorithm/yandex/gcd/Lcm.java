package by.dudko.education.algorithm.yandex.gcd;

import java.math.BigInteger;
import java.util.Scanner;

public class Lcm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        System.out.println(calculateLCM(a, b));
    }

    public static BigInteger calculateLCM(long a, long b) {
        long gcd = calculateGCD(a, b);
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).divide(BigInteger.valueOf(gcd));
    }

    private static long calculateGCD(long max, long min) {
        if (max < min) {
            long temp = max;
            max = min;
            min = temp;
        }
        while (min != 0) {
            long temp = max % min;
            max = min;
            min = temp;
        }
        return max;
    }
}
