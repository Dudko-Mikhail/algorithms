package by.dudko.education.algorithm.leetcode.study75.slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1004. Max Consecutive Ones III
 * <p>
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * <p>
 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 */
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        if (k == nums.length) {
            return nums.length;
        }
        if (k == 0) {
            return countMaxConsecutiveOnes(nums);
        }

        Queue<Integer> bounds = new LinkedList<>();

        int lastZero = -1;
        int i = 0;
        for (; i < nums.length && bounds.size() < k; i++) {
            if (nums[i] == 0) {
                bounds.offer(i - lastZero);
                lastZero = i;
            }
        }

        int onesCount = i;
        int maxOnesCount = i;

        while (i < nums.length) {
            int cur = nums[i];
            if (cur == 0) {
                if (onesCount > maxOnesCount) {
                    maxOnesCount = onesCount;

                }

                int firstZeroOnesCount = bounds.remove();
                onesCount -= firstZeroOnesCount;

                bounds.offer(i - lastZero);
                lastZero = i;
            }
            i++;
            onesCount++;
        }
        return Math.max(maxOnesCount, onesCount);
    }


    private static int countMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for (int num : nums) {
            if (num == 0) {
                if (count > maxCount) {
                    maxCount = count;
                }
                count = 0;
            } else {
                count++;
            }
        }
        return Math.max(maxCount, count);
    }

    public static int longestOnesBest(int[] nums, int k) {
        int maxOnesCount = 0;
        int zeroCount = 0;
        int left = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }

            if (zeroCount > k) {
                int startLeft = left;
                while (left < nums.length && nums[left] != 0) {
                    left++;
                }
                zeroCount--;

                int count = i - startLeft;
                if (count > maxOnesCount) {
                    maxOnesCount = count;
                }
                left++;
            }
        }


        return Math.max(maxOnesCount, nums.length - left);
    }
}
