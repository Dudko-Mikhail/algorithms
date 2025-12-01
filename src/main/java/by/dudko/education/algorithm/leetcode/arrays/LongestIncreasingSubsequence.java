package by.dudko.education.algorithm.leetcode.arrays;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 * <p>
 * 300. Longest Increasing Subsequence
 * <p>
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * <p>
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * <p>
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 1;
            int cur = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < cur) {
                    max = Math.max(max, 1 + dp[j]);
                }
            }
            dp[i] = max;
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static int lengthOfLIS2(int[] nums) {
        int[] sequence = new int[nums.length + 1];
        Arrays.fill(sequence, 10001);
        int max = -10001;
        sequence[0] = max;
        int maxLen = 0;

        for (int num : nums) {
            if (num > max) {
                maxLen++;
                max = num;
                sequence[maxLen] = max;
            } else if (num < max) {
                int pos = findReplacePosition(num, maxLen, sequence);
                if (pos == maxLen) {
                    max = num;
                }
                sequence[pos] = num;
            }
        }

        return maxLen;
    }

    private static int findReplacePosition(int cur, int right, int[] sequence) {
        int left = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int element = sequence[mid];
            if (cur == element) {
                return mid;
            } else if (element > cur) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
