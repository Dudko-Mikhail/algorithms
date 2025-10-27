package by.dudko.education.algorithm.leetcode.interview150.array;

/**
 * https://leetcode.com/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 55. Jump Game
 * <p>
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class JumpGame {
    public static boolean canJump(int[] nums) {
        int maxJump = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && maxJump <= 1) {
                return i == nums.length - 1;
            }
            maxJump = Math.max(nums[i], maxJump - 1);
        }
        return true;
    }
}
