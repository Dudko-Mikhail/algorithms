package by.dudko.education.algorithm.yandex.sum;

import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/42492/problem?id=215%2F2022_11_08%2Fr1Y2AFKOd5
 */
public class PolynomialSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] older = readPolynomial(scanner, n);

        int m = Integer.parseInt(scanner.nextLine());
        int[] younger = readPolynomial(scanner, m);

        if (n < m) {
            int[] temp = older;
            older = younger;
            younger = temp;
        }
        for (int i = 0; i < younger.length; i++) {
            older[i] += younger[i];
        }

        int finalOrder = older.length - 1;
        while (finalOrder > 0 && older[finalOrder] == 0) {
            finalOrder--;
        }
        System.out.println(finalOrder);
        for (int i = finalOrder; i > 0; i--) {
            System.out.print(older[i] + " ");
        }
        System.out.println(older[0]);
    }


    private static int[] readPolynomial(Scanner scanner, int n) {
        int[] coefficients = new int[n + 1];
        String[] data = scanner.nextLine().split(" ");
        for (int i = 0, r = data.length - 1; i <= n; i++, r--) {
            coefficients[i] = Integer.parseInt(data[r]);
        }
        return coefficients;
    }
}
