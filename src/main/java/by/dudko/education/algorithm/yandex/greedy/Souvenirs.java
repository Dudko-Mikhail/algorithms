package by.dudko.education.algorithm.yandex.greedy;


import java.util.Arrays;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48626/problem?id=215%2F2023_04_08%2FUMoEbEa2Dk
 */
public class Souvenirs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int money = Integer.parseInt(firstLine[1]);
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        Arrays.sort(prices);
        int count = 0;
        for (int price : prices) {
            if (price > money) {
                break;
            }
            count++;
            money -= price;
        }
        System.out.println(count);
    }
}
