package by.dudko.education.algorithm.yandex.multiplication;

import java.util.Arrays;
import java.util.Scanner;

public class MaxProduct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] numbers = scanner.nextLine().split(" ");
        System.out.println(findMaxProduct(numbers, n));
    }

    public static long findMaxProduct(String[] numbers, int productSuze) {
        int[] maximums = new int[productSuze];
        Arrays.fill(maximums, Integer.MIN_VALUE);
        for (String s : numbers) {
            int number = Integer.parseInt(s);
            if (number > maximums[0]) {
                updateMaximums(maximums, number);
            }
        }

        long product = 1;
        for (int maximum : maximums) {
            product *= maximum;
        }
        return product;
    }

    private static void updateMaximums(int[] maximums, int number) {
        int position = 0;
        if (maximums[maximums.length - 1] < number) {
            position = maximums.length - 1;
        } else {
            for (int i = 1; i < maximums.length - 1; i++) {
                if (number < maximums[i]) {
                    break;
                }
                position++;
            }
        }
        shift(maximums, number, position);
    }

    private static void shift(int[] maximums, int number, int position) {
        for (int i = 0; i < position; i++) {
            maximums[i] = maximums[i + 1];
        }
        maximums[position] = number;
    }
}
