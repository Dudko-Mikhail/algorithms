package by.dudko.education.algorithm.yandex.greedy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * https://new.contest.yandex.ru/contests/48628/problem?id=215%2F2023_04_08%2Fw3dl2hbEYB
 * <p>
 * Идея решения - исключение самых длинных отрезков
 */
public class MinimumTotalLengthOfSegments {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int segmentsCount = scanner.nextInt();
        TreeSet<Point> uniquePoints = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            uniquePoints.add(new Point(scanner.nextInt()));
        }
        Point[] points = uniquePoints.toArray(Point[]::new);
        points[0].start = true;
        points[points.length - 1].end = true;
        System.out.println(performSearch(segmentsCount, points));
    }

    private static int performSearch(int segmentsCount, Point[] points) {
        if (segmentsCount >= points.length) {
            return 0;
        }
        markPoints(points, segmentsCount);
        return calculateResult(points);
    }

    private static void markPoints(Point[] points, int segmentsCount) {
        Segment[] segments = new Segment[points.length - 1];
        for (int i = 0; i < points.length - 1; i++) {
            segments[i] = new Segment(points[i], points[i + 1]);
        }
        Arrays.sort(segments);
        for (int i = 0; i < segmentsCount - 1; i++) {
            Segment biggest = segments[segments.length - 1 - i];
            biggest.start.end = true;
            biggest.end.start = true;
        }
    }


    private static int calculateResult(Point[] points) {
        int sum = 0;

        int startPosition = 0;
        for (int i = 0; i < points.length; ) {
            if (points[i].end) {
                sum += points[i].x - points[startPosition].x;
                i++;
                while (i < points.length && !points[i].start) {
                    i++;
                }
                startPosition = i;
                continue;
            }
            i++;
        }
        return sum;
    }


    static class Point implements Comparable<Point> {
        int x;
        boolean start;
        boolean end;

        Point(int x) {
            this.x = x;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(x, o.x);
        }
    }


    static class Segment implements Comparable<Segment> {
        Point start;
        Point end;

        public Segment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(end.x - start.x, o.end.x - o.start.x);
        }
    }
}
