package by.dudko.education.algorithm.leetcode.study75.heappriorityqueue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 215. Kth Largest Element in an Array
 * <p>
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Can you solve it without sorting?
 * <p>
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * <p>
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class KthLargestElementInArray {
    private final Queue<Integer> restoredElements = new PriorityQueue<>(Integer::compare);
    private final Set<Integer> numbers = new HashSet<>();
    private int currentNumber = 1;

    public int popSmallest() {
        if (restoredElements.isEmpty()) {
            return currentNumber++;
        }
        Integer smallest = restoredElements.remove();
        numbers.remove(smallest);
        return smallest;
    }

    public void addBack(int num) {
        if (num >= currentNumber) {
            return;
        }
        if (!numbers.contains(num)) {
            numbers.add(num);
            restoredElements.offer(num);
        }
    }
}
