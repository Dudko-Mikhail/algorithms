package by.dudko.education.algorithm.yandex.randomisation;

import java.util.Scanner;

/**
 * Разбиение Ломуто
 */
public class LomutoPartition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = readArray(scanner);
        if (input.length == 1) {
            System.out.println(input[0]);
            return;
        }

        int targetElement = input[0];
        int leftPointer = 1;
        for (int i = 1; i < input.length; i++) {
            if (input[i] < targetElement) {
                swap(input, i, leftPointer++);
            }
        }

        swap(input, 0, leftPointer - 1);
        printResult(input);
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

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
