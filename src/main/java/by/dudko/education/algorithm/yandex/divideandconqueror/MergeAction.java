package by.dudko.education.algorithm.yandex.divideandconqueror;

import java.util.Scanner;

/**
 * Задано n отсортированных по неубыванию последовательностей.
 * <p>
 * Требуется найти отсортированную по неубыванию конкатенацию этих последовательностей.
 * <p>
 * Вводится n - число отрезков. Дальше для каждого отрезка k - длина отрезка. На след строке k чисел через пробел
 */
public class MergeAction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] result = readArray(scanner);
        for (int i = 1; i < n; i++) {
            int[] second = readArray(scanner);
            result = merge(result, second);
        }
        printResult(result);
    }


    private static int[] merge(int[] first, int[] second) {
        int firstPointer = 0;
        int secondPointer = 0;
        int[] result = new int[first.length + second.length];
        int i = 0;
        for (; firstPointer < first.length && secondPointer < second.length; i++) {
            int number;
            if (first[firstPointer] <= second[secondPointer]) {
                number = first[firstPointer++];
            } else {
                number = second[secondPointer++];
            }
            result[i] = number;
        }

        for (int j = firstPointer; j < first.length; j++) {
            result[i++] = first[j];
        }
        for (int j = secondPointer; j < second.length; j++) {
            result[i++] = second[j];
        }
        return result;
    }

    private static int[] readArray(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());
        int[] array = new int[size];
        String[] numbers = scanner.nextLine().split(" ");
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(numbers[i]);
        }
        return array;
    }

    private static void printResult(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print(array[array.length - 1]);
    }
}
