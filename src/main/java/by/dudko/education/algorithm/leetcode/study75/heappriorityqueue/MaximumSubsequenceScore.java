package by.dudko.education.algorithm.leetcode.study75.heappriorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-subsequence-score/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 2542. Maximum Subsequence Score
 * <p>
 * You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.
 * For chosen indices i0, i1, ..., ik - 1, your score is defined as:
 * The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
 * It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
 * Return the maximum possible score.
 * A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.
 * <p>
 * Example 1:
 * Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
 * Output: 12
 * Explanation:
 * The four possible subsequence scores are:
 * - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
 * - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
 * - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
 * - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
 * Therefore, we return the max score, which is 12.
 * <p>
 * Example 2:
 * Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
 * Output: 30
 * Explanation:
 * Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.
 * <p>
 * Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 0 <= nums1[i], nums2[j] <= 105
 * 1 <= k <= n
 */
public class MaximumSubsequenceScore {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Element[] elements = new Element[n];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(nums1[i], nums2[i]);
        }
        Arrays.sort(elements);

        int min = elements[k - 1].num2;
        long sum = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            queue.offer(elements[i].num1);
            sum += elements[i].num1;
        }

        long maxScore = sum * min;
        for (int i = k; i < n; i++) {
            Integer minElement = queue.element();
            int currentNum1 = elements[i].num1;
            if (minElement < currentNum1) {
                queue.remove();
                queue.add(currentNum1);
                sum = sum - minElement + currentNum1;

                min = elements[i].num2;
                long score = sum * min;
                if (score > maxScore) {
                    maxScore = score;
                }
            }

        }
        return maxScore;
    }

    static class Element implements Comparable<Element> {
        int num1;
        int num2;

        public Element(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(o.num2, num2);
        }
    }
}
