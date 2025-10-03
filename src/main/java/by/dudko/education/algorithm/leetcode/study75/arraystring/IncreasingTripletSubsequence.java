package by.dudko.education.algorithm.leetcode.study75.arraystring;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 334. Increasing Triplet Subsequence
 * <p>
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * <p>
 * Example 2:
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] input) {
        Queue<Call> chain = new LinkedList<>();
        chain.add(new Call(1, input[0]));
        while (!chain.isEmpty()) {
            if (check(chain.poll(), input, chain)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(Call call, int[] input, Queue<Call> chain) {
        int min = call.min;
        Integer second = null;
        for (int i = call.start; i < input.length; i++) {
            int current = input[i];
            if (current == min) {
                continue;
            }
            if (current < min) {
                min = current;
                chain.offer(new Call(i + 1, current));
            } else if (second == null) {
                second = current;
            } else if (current > second) {
                return true;
            } else {
                second = current;
            }
        }
        return false;
    }

    private static class Call {
        int start;
        int min;

        Call(int start, int min) {
            this.start = start;
            this.min = min;
        }
    }


    public boolean increasingTriplet2(int[] input) {
        Integer minMemory = null;
        int min = input[0];
        Integer second = null;
        for (int i = 1; i < input.length; i++) {
            int current = input[i];

            if (minMemory != null) {
                if (current == minMemory) {
                    continue;
                }
                if (current < minMemory) {
                    minMemory = current;
                } else {
                    if (current < second) {
                        min = minMemory;
                        second = current;
                        minMemory = null;
                    }
                }
            }

            if (second == null) {
                if (min > current) {
                    min = current;
                } else if (min < current) {
                    second = current;
                }
            } else {
                if (current == min) {
                    continue;
                }
                if (current < min) {
                    minMemory = current;
                } else if (current > second) {
                    return true;
                } else {
                    second = current;
                }
            }
        }
        return false;
    }
}
