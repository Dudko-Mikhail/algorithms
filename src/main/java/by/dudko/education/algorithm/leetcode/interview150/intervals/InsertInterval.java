package by.dudko.education.algorithm.leetcode.interview150.intervals;

/**
 * https://leetcode.com/problems/insert-interval/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 57. Insert Interval
 * <p>
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent
 * the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals
 * still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.
 * <p>
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 * <p>
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^5
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 */
public class InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int[] cur = intervals[i];
            if (newInterval[0] > cur[0]) {
                if (newInterval[0] <= cur[1]) { // пересекаются мерж
                    return performMerge(i, intervals, newInterval);
                }
            } else {
                if (newInterval[0] == cur[0] || newInterval[1] >= cur[0]) { // пересекаются мерж
                    return performMerge(i, intervals, newInterval);
                } else { // не пересекаются просто вставляем.
                    int[][] result = new int[n + 1][2];
                    System.arraycopy(intervals, 0, result, 0, i);
                    result[i] = newInterval;
                    System.arraycopy(intervals, i, result, i + 1, n - i);
                    return result;
                }
            }
        }

        // вставляем новый интервал в конец
        int[][] result = new int[n + 1][2];
        System.arraycopy(intervals, 0, result, 0, n);
        result[n] = newInterval;
        return result;
    }

    private static int[][] performMerge(int startMergeIndex, int[][] intervals, int[] newInterval) {
        int i = startMergeIndex;
        int[] cur = intervals[startMergeIndex];
        int n = intervals.length;
        int start = Math.min(newInterval[0], cur[0]);

        do {
            if (i + 1 >= n) {
                break;
            }
            cur = intervals[++i];
        } while (intersects(cur, newInterval));

        int end;
        int endIndex;
        if (intersects(cur, newInterval)) {
            end = Math.max(newInterval[1], cur[1]);
            endIndex = i;
        } else {
            end = Math.max(newInterval[1], intervals[i - 1][1]);
            endIndex = i - 1;
        }

        int[][] result = new int[n - endIndex + startMergeIndex][2];
        System.arraycopy(intervals, 0, result, 0, startMergeIndex);
        result[startMergeIndex] = new int[]{start, end};
        if (endIndex + 1 < n) { // конца может не быть
            System.arraycopy(intervals, endIndex + 1, result, startMergeIndex + 1, n - endIndex - 1);
        }
        return result;
    }

    private static boolean intersects(int[] first, int[] second) {
        return (second[0] >= first[0] && second[0] <= first[1])
                || (second[0] <= first[0] && second[1] >= first[0]);
    }
}
