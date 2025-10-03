package by.dudko.education.algorithm.yandex.recursive;

import java.util.Scanner;

/**
 * Головоломка <<Ханойские башни>> состоит из трёх стержней, пронумерованных слева направо: 1, 2 и 3.
 * Также в головоломке используется столп дисков с отверстием посередине. Радиус дисков уменьшается снизу вверх.
 * Изначально диски располагаются на левом стержне (стержень 1), самый большой диск находится вниз.
 * Диски в игре перемещаются по одному со стержня на стержень. Диск можно надеть на стержень, только если он пустой
 * или верхний диск на нём большего размера, чем перемещаемый.
 * Цель головоломки — перенести все диски со стержня 1 на стержень 3.
 * <p>
 * Требуется найти последовательность ходов, которая решает головоломку <<Ханойские башни>>.
 * Формат ввода
 * В первой строке задано одно число n (3≤n≤10) — количество дисков на первой башне.
 * <p>
 * Формат вывода
 * В первой строке выведите количество операций k.
 * В следующих k строках выведите по два числа в каждой xi, yi (1≤xi<yi≤3) — переместить верхний диск со стержня
 * xi на стержень yi.
 */
public class HanoiTowers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(countMoves(n));
        move(n, 1, 3);
    }


    private static void move(int n, int start, int end) {
        int free = 6 - start - end;
        if (n <= 0) {
            return;
        }

        move(n - 1, start, free);

        System.out.format("%s %s%n", start, end);

        move(n - 1, free, end);
    }

    private static int countMoves(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum * 2 + 1;
        }
        return sum;
    }
}

