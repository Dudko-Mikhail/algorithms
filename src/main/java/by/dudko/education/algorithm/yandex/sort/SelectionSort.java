package by.dudko.education.algorithm.yandex.sort;

import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int[] array = new int[size];
        String[] numbers = scanner.nextLine().split(" ");
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(numbers[i]);
        }
        for (int i = 0; i < size - 1; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }

        printResult(size, array);
    }

    private static void printResult(int size, int[] array) {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print(array[size - 1]);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
