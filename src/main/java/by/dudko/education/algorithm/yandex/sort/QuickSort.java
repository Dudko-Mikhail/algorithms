package by.dudko.education.algorithm.yandex.sort;

import java.util.Random;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = readArray(scanner);
        sort(new Random(), input, 0, input.length);
        printResult(input);
    }

    public static void sort(Random random, int[] input, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        int finalTargetElementPosition = lomutoPartition(random, input, start, end);
        sort(random, input, start, finalTargetElementPosition);
        sort(random, input, finalTargetElementPosition + 1, end);
    }


    private static int lomutoPartition(Random random, int[] input, int start, int end) {
        int targetElementIndex = getNextInt(random, start, end);
        int targetElement = input[targetElementIndex];
        swap(input, start, targetElementIndex);
        targetElementIndex = start;
        int leftPointer = start + 1;

        for (int i = start + 1; i < end; i++) {
            if (input[i] < targetElement) {
                swap(input, i, leftPointer++);
            }
        }

        swap(input, targetElementIndex, leftPointer - 1);
        return leftPointer - 1;
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

    private static int getNextInt(Random random, int start, int end) {
        return random.nextInt(end - start) + start;
    }

    private static void printResult(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array[array.length - 1]);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
