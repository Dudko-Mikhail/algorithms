package by.dudko.education.algorithm.yandex.greedy;

import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48629/problem?id=215%2F2023_04_08%2Fpm6yN9wtbB
 * <p>
 * Вы занимаетесь организацией соревнований для детей, и у вас есть n конфет, которые вы раздадите в качестве призов.
 * Вы хотите отдать эти конфеты тем, кто займет первые k мест в соревнованиях, и распределить конфеты так, чтобы за
 * более высокое место всегда выходило больше конфет. Чтобы порадовать как можно больше детей,
 * вам понадобится найти самое большое значение k, при котором это возможно.
 * <p>
 * Моя идея: (a1 + an) * n / 2 = sum - сумма арифметической прогрессии
 * a1 = 1, an = n, sum - приходит как input задачи.
 * Решение уравнения: n = (-1 + sqrt(1 + 8 * sum)) / 2
 */
public class NumberOfPrizes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = scanner.nextInt();
        int result = (int) ((-1 + Math.sqrt(1 + 8.0 * sum)) / 2);
        System.out.println(result);
    }
}
