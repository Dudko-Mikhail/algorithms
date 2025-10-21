package by.dudko.education.algorithm.leetcode.interview150.slidingwindow;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 209. Minimum Size Subarray Sum
 * <p>
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
 * whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * <p>
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class MinimumSizeSubarraySum { // todo Надо найти On решение
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int minWindow = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (checkWindow(mid + 1, target, nums)) {
                right = mid - 1;
                minWindow = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        return minWindow;
    }

    private static boolean checkWindow(int window, int target, int[] nums) {
        int sum = 0;
        int i = 0;
        while (i < window) {
            sum += nums[i++];
        }
        while (i < nums.length) {
            if (sum >= target) {
                return true;
            }
            sum += nums[i] - nums[i - window];
            i++;
        }
        return sum >= target;
    }
}
