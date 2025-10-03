package by.dudko.education.algorithm.yandex.fibonacci;

import java.math.BigInteger;
import java.util.Scanner;

public class CalculateFibonacciNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(calculateFibonacci(n));
    }

    public static BigInteger calculateFibonacci(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        }
        BigInteger previous = BigInteger.ZERO;
        BigInteger current = BigInteger.ONE;
        for (int i = 1; i < n; i++) {
            BigInteger temp = current;
            current = previous.add(current);
            previous = temp;
        }
        return current;
    }
}
