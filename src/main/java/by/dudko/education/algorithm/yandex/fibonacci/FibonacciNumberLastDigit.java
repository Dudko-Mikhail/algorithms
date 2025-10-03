package by.dudko.education.algorithm.yandex.fibonacci;

import java.util.Scanner;

/**
 * Вывести последнюю цифру n-го числа Фибоначчи. f(0) = 0, f(1) = 1;
 * n <= 10^6
 */
public class FibonacciNumberLastDigit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(findLastDigit(n));
    }

    public static byte findLastDigit(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        byte previous = 0;
        byte current = 1;
        for (int i = 1; i < n; i++) {
            byte temp = current;
            current = (byte) ((previous + current) % 10);
            previous = temp;
        }
        return current;
    }
}
