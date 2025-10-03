package by.dudko.education.algorithm.yandex.gcd;

import java.util.Scanner;

public class Gcd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        System.out.println(calculateGCD(a, b));
    }

    public static long calculateGCD(long max, long min) {
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
