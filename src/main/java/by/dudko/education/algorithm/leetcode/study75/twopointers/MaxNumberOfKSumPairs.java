package by.dudko.education.algorithm.leetcode.study75.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1679. Max Number of K-Sum Pairs
 * <p>
 * You are given an integer array nums and an integer k.
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * Return the maximum number of operations you can perform on the array.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * <p>
 * Example 2:
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 */
public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.merge(num, 1, Integer::sum);
        }

        int count = 0;
        for (Integer key : countMap.keySet()) {
            Integer keyCount = countMap.get(key);
            int targetNumber = k - key;
            if (targetNumber != key) {
                Integer targetNumberCount = countMap.get(targetNumber);
                if (targetNumberCount == null) {
                    continue;
                }
                count += Math.min(keyCount, targetNumberCount);
            } else {
                count += keyCount / 2;
            }
            countMap.replace(key, 0);
        }
        return count;
    }
}
