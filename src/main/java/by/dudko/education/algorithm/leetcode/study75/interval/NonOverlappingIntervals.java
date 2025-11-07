package by.dudko.education.algorithm.leetcode.study75.interval;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 435. Non-overlapping Intervals
 * <p>
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals
 * you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
 * <p>
 * Example 1:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * <p>
 * Example 2:
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * <p>
 * Example 3:
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * Constraints:
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 */
public class NonOverlappingIntervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        System.out.println(Arrays.stream(intervals)
                .map(Arrays::toString)
                .collect(Collectors.joining(" ")));
        int count = 0;
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (nonIntersects(current, intervals[i])) {
                current = intervals[i];
            } else {
                count++;
            }
        }
        return count;
    }

    private static boolean nonIntersects(int[] first, int[] second) {
        return (first[0] < second[0] && first[1] <= second[0]) || (second[0] < first[0] && second[1] <= first[0]);
    }
}
