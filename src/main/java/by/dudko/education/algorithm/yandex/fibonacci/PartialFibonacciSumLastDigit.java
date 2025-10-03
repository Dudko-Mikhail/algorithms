package by.dudko.education.algorithm.yandex.fibonacci;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/47496/problem?id=215%2F2023_03_10%2FL7uqmRjxML
 * <p>
 * Надо найти последнюю цифру F0 + F1 + F2 + ... + Fn
 * 0 <= n <= 10^14
 */
public class PartialFibonacciSumLastDigit {
    private static final int PISANO_PERIOD = 60;
    private static final int[] remainders = fillRemainders();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(findLastDigitOfPartialSum(n));

        stressTest();
        System.out.println(Arrays.toString(remainders));
    }

    private static void stressTest() {
        for (int i = 0; i < 102; i++) {
            BigInteger sum = BigInteger.ZERO;
            for (int j = 0; j <= i; j++) {
                sum = sum.add(CalculateFibonacciNumber.calculateFibonacci(j));
            }
            int expected = sum.mod(BigInteger.valueOf(10)).intValue();
            int actual = findLastDigitOfPartialSum(i);
            if (expected != actual) {
                throw new IllegalStateException("Invalid algorithm. Expected=%s Actual=%s".formatted(expected, actual));
            }
        }
    }

    private static int findLastDigitOfPartialSum(long n) {
        int resultPosition = (int) (n % PISANO_PERIOD);
        int sum = 0;
        for (int i = 0; i <= resultPosition; i++) {
            sum += remainders[i];
        }
        System.out.format("Input=%s ResultPosition=%s sum=%s digit=%s%n", n, resultPosition, sum, sum % 10);
        return sum % 10;
    }

    private static int[] fillRemainders() {
        int[] remainders = new int[PISANO_PERIOD];
        remainders[1] = 1;

        int previous = 0;
        int current = 1;
        for (int i = 2; i < PISANO_PERIOD; i++) {
            int temp = current;
            current = (previous + current) % 10;
            previous = temp;
            remainders[i] = current;
        }
        return remainders;
    }
}
