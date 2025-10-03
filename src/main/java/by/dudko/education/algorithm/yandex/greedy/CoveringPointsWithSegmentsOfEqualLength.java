package by.dudko.education.algorithm.yandex.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48628/problem?id=215%2F2023_04_08%2FuZEWKVQFX9
 */
public class CoveringPointsWithSegmentsOfEqualLength {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long length = scanner.nextLong();
        long[] points = new long[n];
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextLong();
        }
        Arrays.sort(points);

        int count = 0;
        for (int i = 0; i < n; ) {
            long currentStart = points[i];
            long end = currentStart + length;
            count++;
            while (i < n && points[i] <= end) {
                i++;
            }
        }
        System.out.println(count);
    }
}
