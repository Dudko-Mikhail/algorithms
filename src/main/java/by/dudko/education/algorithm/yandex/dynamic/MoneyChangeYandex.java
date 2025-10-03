package by.dudko.education.algorithm.yandex.dynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MoneyChangeYandex {
    private static final int[] NOMINALS = new int[]{1, 3, 4};

    public static int findBanknotesCount(int money) {
        Map<Integer, Integer> memory = new HashMap<>();
        memory.put(0, 0);
        Queue<Integer> toCalculate = new LinkedList<>();
        toCalculate.add(0);

        while (!toCalculate.isEmpty()) {
            int current = toCalculate.remove();
            for (int nominal : NOMINALS) {
                int sum = current + nominal;
                if (sum > money || memory.containsKey(sum)) {
                    continue;
                }

                memory.put(sum, memory.get(current) + 1);
                toCalculate.offer(sum);
            }
        }
        return memory.get(money);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.format("Money: %s, Count: %s%n", i, MoneyChangeYandex.findBanknotesCount(i));
        }
    }
}
