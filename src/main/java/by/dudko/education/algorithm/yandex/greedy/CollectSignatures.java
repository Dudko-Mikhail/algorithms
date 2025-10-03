package by.dudko.education.algorithm.yandex.greedy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48628/problem?id=215%2F2023_04_08%2FY6NNiWMX0V
 */
public class CollectSignatures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        List<Interval> intervals = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            String[] intervalData = scanner.nextLine().split("\\s");
            intervals.add(new Interval(Long.parseLong(intervalData[0]), Long.parseLong(intervalData[1])));

        }

        StringBuilder points = new StringBuilder();
        int count = 0;
        while (!intervals.isEmpty()) {
            Iterator<Interval> iterator = intervals.iterator();
            long min = Long.MAX_VALUE;
            Interval minEndOwnerInterval = null;
            do {
                Interval interval = iterator.next();
                long end = interval.end;
                if (end < min) {
                    min = end;
                    minEndOwnerInterval = interval;
                }
            } while (iterator.hasNext());
            cleanIntersections(intervals.iterator(), minEndOwnerInterval);
            points.append(minEndOwnerInterval.end).append(" ");
            count++;
        }
        System.out.println(count);
        System.out.println(points.toString().trim());
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
        long start;
        long end;

        Interval(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
