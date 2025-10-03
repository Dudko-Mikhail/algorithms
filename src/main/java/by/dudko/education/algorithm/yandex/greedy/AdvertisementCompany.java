package by.dudko.education.algorithm.yandex.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48627/problem?id=215%2F2023_04_08%2FJWscvwQNDq
 */
public class AdvertisementCompany {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] clicks = new int[n];
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            clicks[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        Arrays.sort(prices);
        Arrays.sort(clicks);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) clicks[i] * prices[i];
        }
        System.out.println(sum);
    }
}
