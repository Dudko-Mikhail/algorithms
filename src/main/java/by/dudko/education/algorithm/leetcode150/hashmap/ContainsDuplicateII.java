package by.dudko.education.algorithm.leetcode150.hashmap;


import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 219. Contains Duplicate II
 * <p>
 * Given an integer array nums and an integer k,
 * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * <p>
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * <p>
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 0 <= k <= 10^5
 */
public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicateOptimized(int[] nums, int k) {
        Map<Integer, Integer> numberToPreviousIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer old = numberToPreviousIndex.put(nums[i], i);
            if (old != null && i - old <= k) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, SortedSet<Integer>> numberToIndices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            numberToIndices.computeIfAbsent(number, value -> new TreeSet<>()).add(i);
        }

        return numberToIndices.values().stream()
                .filter(set -> set.size() > 1)
                .anyMatch(set -> checkCollection(set, k));
    }

    private static boolean checkCollection(SortedSet<Integer> indexes, int k) {
        boolean first = true;
        int previous = indexes.first();
        for (int index : indexes) {
            if (first) {
                first = false;
                continue;
            }
            if (index - previous <= k) {
                return true;
            }
            previous = index;
        }
        return false;
    }
}
