package by.dudko.education.algorithm.leetcode.study75.twopointers;

/**
 * https://leetcode.com/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 283. Move Zeroes
 * <p>
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * <p>
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int firstPointer = 0;
        while (firstPointer < nums.length && nums[firstPointer] != 0) {
            firstPointer++;
        }

        int secondPointer = firstPointer + 1;
        while (firstPointer < nums.length) {
            while (secondPointer < nums.length && nums[secondPointer] == 0) {
                secondPointer++;
            }
            if (secondPointer == nums.length) {
                return;
            }

            nums[firstPointer] = nums[secondPointer];
            nums[secondPointer] = 0;
            secondPointer++;
            firstPointer++;
        }
    }

    public void moveZeroes2(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else if (zeroCount != 0) {
                nums[i - zeroCount] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
