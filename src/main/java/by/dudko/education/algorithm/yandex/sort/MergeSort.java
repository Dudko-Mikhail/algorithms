package by.dudko.education.algorithm.yandex.sort;

import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = readArray(scanner);
        printResult(sort(input, 0, input.length));
    }


    public static int[] sort(int[] array, int start, int end) {
        if (end - start <= 1) {
            return new int[]{array[start]};
        }
        int mid = (start + end) / 2;
        int[] left = sort(array, start, mid);
        int[] right = sort(array, mid, end);
        return merge(left, right);
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
