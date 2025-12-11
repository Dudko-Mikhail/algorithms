package by.dudko.education.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/contiguous-array/description/
 * <p>
 * 525. Contiguous Array
 * <p>
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 * <p>
 * Example 1:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * <p>
 * Example 2:
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * Example 3:
 * Input: nums = [0,1,1,1,1,1,0,0,0]
 * Output: 6
 * Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 */
public class ContiguousArray {
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> positionMap = new HashMap<>();
        int[] prefixArray = new int[nums.length];
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                current++;
            } else {
                current--;
            }
            positionMap.putIfAbsent(current, i);
            prefixArray[i] = current;
        }

        int maxLength = 0;
        for (int i = prefixArray.length - 1; i >= 0 && i > maxLength; i--) {
            if (prefixArray[i] == 0) {
                maxLength = Math.max(i + 1, maxLength);
            } else {
                Integer firstOccurrence = positionMap.get(prefixArray[i]);
                if (firstOccurrence != null) {
                    maxLength = Math.max(maxLength, i - firstOccurrence);
                }
            }
        }

        return maxLength;
    }
}
