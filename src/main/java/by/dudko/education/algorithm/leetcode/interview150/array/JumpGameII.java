package by.dudko.education.algorithm.leetcode.interview150.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 45. Jump Game II
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at index i, you can jump to any index (i + j) where:
 * <p>
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
 * <p>
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 */
public class JumpGameII {
    public static int jump(int[] nums) {
        int[] minMoves = new int[nums.length];
        Arrays.fill(minMoves, Integer.MAX_VALUE);
        minMoves[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            int currentMoves = minMoves[i] + 1;
            int endIndex = Math.min(nums.length, i + 1 + nums[i]);
            for (int j = i + 1; j < endIndex; j++) {
                minMoves[j] = Math.min(minMoves[j], currentMoves);
            }
        }
        return minMoves[nums.length - 1];
    }

    public static int jump2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int jumps = 0;
        for (int i = 0; i < nums.length; i++) {
            int endIndex = Math.min(nums.length, i + 1 + nums[i]);
            int max = 0;
            int indexDiff = 0;
            int nextJumpPosition = 0;
            for (int j = i + 1; j < endIndex; j++, indexDiff++) {
                if (nums[j] >= max - indexDiff) {
                    max = nums[j];
                    indexDiff = 0;
                    nextJumpPosition = j;
                }
                if (j == nums.length - 1) {
                    return jumps + 1;
                }
            }
            i = nextJumpPosition - 1;
            jumps++;
        }
        return jumps;
    }
}
