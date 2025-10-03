package by.dudko.education.algorithm.yandex.gcd;

import java.util.Map;
import java.util.Scanner;

public class MaxIterationsCountInGcpSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();
        Map.Entry<Long, Long> result = findClosestFibonacciPair(n);
        System.out.println(result.getKey() + " " + result.getValue());
    }

    public static Map.Entry<Long, Long> findClosestFibonacciPair(long n) {
        long previous = 0;
        long current = 1;
        while (true) {
            long next = previous + current;
            if (next > n) {
                break;
            }
            long temp = current;
            current = next;
            previous = temp;
        }
        return Map.entry(previous, current);
    }
}
