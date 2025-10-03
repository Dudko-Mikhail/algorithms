package by.dudko.education.algorithm.yandex.greedy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Задано
 * n
 * n интервалов. Требуется найти максимальное количество взаимно непересекающихся интервалов.
 * <p>
 * Два интервала пересекаются, если они имеют хотя бы одну общую точку.
 * <p>
 * Формат ввода
 * В первой строке задано одно число n (1≤n≤100) - количество интервалов.
 * В следующих n строках заданы интервалы li, ri (1≤li≤ri≤50).
 * Формат вывода
 * Выведите ответ на задачу.
 */
public class MaxIntersectionsNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        List<Interval> intervals = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            String[] intervalData = scanner.nextLine().split("\\s");
            intervals.add(new Interval(Integer.parseInt(intervalData[0]), Integer.parseInt(intervalData[1])));

        }

        int count = 0;
        while (!intervals.isEmpty()) {
            Iterator<Interval> iterator = intervals.iterator();
            int min = 51;
            Interval minEndOwnerInterval = null;
            do {
                Interval interval = iterator.next();
                int end = interval.end;
                if (end < min) {
                    min = end;
                    minEndOwnerInterval = interval;
                }
            } while (iterator.hasNext());
            cleanIntersections(intervals.iterator(), minEndOwnerInterval);
            count++;
        }

        System.out.println(count);
    }

    private static void cleanIntersections(Iterator<Interval> intervals, Interval minEndOwnerInterval) {
        while (intervals.hasNext()) {
            Interval interval = intervals.next();
            if (intersects(minEndOwnerInterval, interval)) {
                intervals.remove();
            }
        }
    }

    private static boolean intersects(Interval first, Interval second) {
        return second.end == first.end || second.start <= first.end;
    }

    private static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}