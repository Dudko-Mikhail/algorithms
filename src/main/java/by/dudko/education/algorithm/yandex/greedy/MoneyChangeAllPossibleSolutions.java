package by.dudko.education.algorithm.yandex.greedy;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Предположим, что у кассира есть бесконечное количество монет номиналами 1,5,10.
 * Найдите все наборы монет, которые в сумме дают money.
 * <br/>
 * Требуется вывести количество подходящих наборов монет и сами наборы.
 * Два набора считаются различными, если мультимножества монет не совпадают.
 * <br/>
 * 1 <= money <= 100
 */
public class MoneyChangeAllPossibleSolutions {
    private static final int[] MONEY_DENOMINATIONS = new int[]{10, 5, 1};
    private static final int MONEY_DENOMINATION_LENGTH = MONEY_DENOMINATIONS.length;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();

        Set<List<Integer>> results = new HashSet<>();
        int maxTenCount = money / 10;
        for (int i = 0; i <= maxTenCount; i++) {
            int remainingMoney = money - i * 10;
            fillResults(results, i, remainingMoney);
        }
        printResults(results);
    }


    private static void fillResults(Set<List<Integer>> results, int tenCount, int remainingMoney) {
        int oneCount = remainingMoney;
        int fiveCount = 0;
        while (oneCount >= 0) {
            results.add(List.of(tenCount, fiveCount, oneCount));
            oneCount -= 5;
            fiveCount++;
        }
    }


    private static void printResults(Set<List<Integer>> results) {
        System.out.println(results.size());
        results.forEach(resultCoefficients -> {
            int count = 0;
            StringBuilder builder = new StringBuilder("X");
            for (int i = 0; i < MONEY_DENOMINATION_LENGTH; i++) {
                Integer denominationCount = resultCoefficients.get(i);
                count += denominationCount;
                builder.append((" " + MONEY_DENOMINATIONS[i]).repeat(denominationCount));
            }
            builder.replace(0, 1, Integer.toString(count));
            System.out.println(builder);
        });
    }
}
