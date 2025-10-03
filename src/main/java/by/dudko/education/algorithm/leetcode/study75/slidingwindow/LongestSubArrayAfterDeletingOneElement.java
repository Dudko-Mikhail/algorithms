package by.dudko.education.algorithm.leetcode.study75.slidingwindow;

/**
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1493. Longest Subarray of 1's After Deleting One Element
 * <p>
 * Given a binary array nums, you should delete one element from it.
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 * <p>
 * Example 1:
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * <p>
 * Example 2:
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * <p>
 * Example 3:
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class LongestSubArrayAfterDeletingOneElement {
    public int longestSubArray(int[] nums) {
        int left = 0;
        int start = 0;
        int maxLength = 0;
        boolean hasZero = false;

        while (left < nums.length) {
            while (left < nums.length && nums[left] != 0) {
                left++;
            }

            if (left != nums.length) {
                hasZero = true;
            }

            int right = left + 1;
            while (right < nums.length && nums[right] != 0) {
                right++;
            }
            int length = right - start;
            maxLength = Math.max(maxLength, length - 1);

            start = left + 1;
            left++;
        }
        return hasZero ? maxLength : maxLength - 1;
    }
}
