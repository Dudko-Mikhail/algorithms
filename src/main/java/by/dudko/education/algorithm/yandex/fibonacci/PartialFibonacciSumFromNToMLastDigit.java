package by.dudko.education.algorithm.yandex.fibonacci;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * https://new.contest.yandex.ru/contests/47496/problem?id=215%2F2023_03_10%2FukRd8G8nu3
 * <p>
 * Даны 2 числа m,n; 0 <= m <= n <= 10^14
 * Найти последнюю цифру суммы Fm + Fm+1 + Fm+2 + .. + Fn
 */
public class PartialFibonacciSumFromNToMLastDigit {
    private static final int PISANO_PERIOD = 60;
    private static final int[] remainders = fillRemainders();

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        long m = scanner.nextLong();
//        long n = scanner.nextLong();
//        System.out.println(findLastDigitOfPartialSum(n, m));

        stressTest();
        System.out.println(Arrays.toString(remainders));
    }

    private static void stressTest() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j <= i; j++) {
                BigInteger sum = BigInteger.ZERO;
                for (int k = j; k <= i; k++) {
                    sum = sum.add(CalculateFibonacciNumber.calculateFibonacci(k));
                }
                int expected = sum.mod(BigInteger.valueOf(10)).intValue();
                int actual = findLastDigitOfPartialSum(i, j);
                if (expected != actual) {
                    throw new IllegalStateException("Invalid algorithm. N=%s M=%s Expected=%s Actual=%s".formatted(
                            i, j, expected, actual));
                }
            }
        }
    }

    private static int findLastDigitOfPartialSum(long n, long m) {
        int startPosition = (int) (m % PISANO_PERIOD);
        int endPosition = (int) (n % PISANO_PERIOD);

        int sum = 0;
        if (startPosition <= endPosition) {
            for (int i = startPosition; i <= endPosition; i++) {
                sum += remainders[i];
            }
        } else {
            for (int i = startPosition; i < PISANO_PERIOD; i++) {
                sum += remainders[i];
            }
            for (int i = 0; i <= endPosition; i++) {
                sum += remainders[i];
            }
        }
        System.out.format("N=%s M=%s StartPos=%s EndPos=%s sum=%s digit=%s%n", n, m, startPosition, endPosition, sum,
                sum % 10);
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
