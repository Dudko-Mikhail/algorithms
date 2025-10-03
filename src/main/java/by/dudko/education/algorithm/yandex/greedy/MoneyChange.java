package by.dudko.education.algorithm.yandex.greedy;

import java.util.Scanner;

/**
 * Предположим, что у кассира есть бесконечное количество монет номиналами 1,5,10,20,50.
 * Найдите набор с минимальным количеством монет, которые в сумме дают money.
 * <br/>
 * Вывести номиналы в результирующем наборе в любом порядке.
 * <br/>
 * 1 <= money <= 10^5
 */
public class MoneyChange {
    private static final int[] MONEY_DENOMINATIONS = new int[]{50, 20, 10, 5, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        int i = 0;
        StringBuilder result = new StringBuilder();
        int sum = 0;
        while (money > 0) {
            int denomination = MONEY_DENOMINATIONS[i++];
            int count = money / denomination;
            sum += count;
            result.append((denomination + " ").repeat(count));
            money %= denomination;
        }
        System.out.println(sum);
        System.out.println(result);
    }
}
