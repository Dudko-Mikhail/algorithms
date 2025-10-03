package by.dudko.education.algorithm.yandex.fibonacci;

import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/47496/problem?id=215%2F2023_03_10%2FAH1EGu1ioL
 * <p>
 * Найти Fn mod m
 */
public class FibonacciModNDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(performDivision(n, m));
    }

    private static int performDivision(long n, int m) {
        int period = findPisanoPeriod(m);
        int resultPosition = (int) (n % period);
        if (resultPosition == 0) {
            return 0;
        }
        int previous = 0;
        int current = 1;
        for (int i = 1; i < resultPosition; i++) {
            int temp = current;
            current = (previous + current) % m;
            previous = temp;
        }
        return current;
    }

    private static int findPisanoPeriod(int m) {
        int previous = 0;
        int current = 1;
        boolean firstMatch = false;
        for (int i = 1; i < m * m; i++) {
            int temp = current;
            current = (previous + current) % m;
            previous = temp;
            if (current == 0) {
                firstMatch = true;
            } else if (firstMatch && current == 1) {
                return i;
            } else {
                firstMatch = false;
            }
        }
        throw new IllegalStateException("Impossible situation");
    }
}
