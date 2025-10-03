package by.dudko.education.algorithm.yandex.multiplication;

import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/42734/problem?id=215%2F2022_11_08%2F5Ai6zd8L0B
 */
public class CounterExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        if (1.5 * n >= 2 * n - 3) { // Yes при 7+
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.print(n + " ");
            for (int i = 1; i < n; i++) {
                System.out.print(i + " ");
            }
        }
    }
}