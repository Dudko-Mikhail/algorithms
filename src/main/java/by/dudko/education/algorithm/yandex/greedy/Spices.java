package by.dudko.education.algorithm.yandex.greedy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48626/problem?id=215%2F2023_04_08%2FGQQcaeYtbT
 */
public class Spices {
    private static final int FLOATING_POINT_DIGITS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int weight = Integer.parseInt(firstLine[1]);
        List<Map.Entry<BigDecimal, BigDecimal>> spicesPriceToWeight = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String[] spicesData = scanner.nextLine().split(" ");
            spicesPriceToWeight.add(Map.entry(new BigDecimal(spicesData[0]), new BigDecimal(spicesData[1])));
        }
        spicesPriceToWeight.sort((e1, e2) -> {
            BigDecimal first = e1.getKey().divide(e1.getValue(), FLOATING_POINT_DIGITS, RoundingMode.HALF_EVEN);
            BigDecimal second = e2.getKey().divide(e2.getValue(), FLOATING_POINT_DIGITS, RoundingMode.HALF_EVEN);
            return second.compareTo(first);
        });

        int i = 0;
        BigDecimal sum = BigDecimal.ZERO;
        while (weight > 0 && i < spicesPriceToWeight.size()) {
            Map.Entry<BigDecimal, BigDecimal> spicesToWeight = spicesPriceToWeight.get(i);
            int amount = Math.min(weight, spicesToWeight.getValue().intValue());
            sum = sum.add(spicesToWeight.getKey().multiply(BigDecimal.valueOf(amount))
                    .divide(spicesToWeight.getValue(), FLOATING_POINT_DIGITS, RoundingMode.HALF_EVEN));
            i++;
            weight -= amount;
        }
        System.out.println(sum);
    }
}
