package by.dudko.education.algorithm.leetcode.interview150.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/summary-ranges/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 228. Summary Ranges
 * <p>
 * You are given a sorted unique integer array nums.
 * <p>
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * Each range [a,b] in the list should be output as:
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * <p>
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * Constraints:
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */
public class SummaryRanges {
    public static List<String> summaryRanges(int[] nums) {
        List<String> intervals = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            i = processInterval(i, nums, intervals);
        }
        return intervals;
    }

    private static int processInterval(int i, int[] nums, List<String> intervals) {
        int start = nums[i++];
        int end = start;

        while (i < nums.length && nums[i] == end + 1) {
            i++;
            end++;
        }
        if (start == end) {
            intervals.add(Integer.toString(start));
        } else {
            intervals.add(start + "->" + end);
        }
        return i;
    }
}
