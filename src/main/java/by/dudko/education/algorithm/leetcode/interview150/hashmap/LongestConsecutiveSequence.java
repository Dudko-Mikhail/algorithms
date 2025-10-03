package by.dudko.education.algorithm.leetcode.interview150.hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 128. Longest Consecutive Sequence
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * <p>
 * Example 3:
 * Input: nums = [1,0,1,2]
 * Output: 3
 * <p>
 * Constraints:
 * 0 <= nums.length <= 10^5
 * -109 <= nums[i] <= 10^9
 */
public class LongestConsecutiveSequence {
    public static int longestConsecutiveWithSingleSet(int[] nums) {
        Set<Integer> numbers = new HashSet<>();

        for (int num : nums) {
            numbers.add(num);
        }

        int maxLength = 0;
        for (int num : nums) {
            if (numbers.contains(num)) {
                int length = findConsecutiveSequenceLength(num, numbers);
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }
        return maxLength;
    }

    private static int findConsecutiveSequenceLength(int element, Set<Integer> numbers) {
        int min = element;
        int max = element;
        numbers.remove(element);

        int next = min - 1;
        while (numbers.remove(next)) {
            min = next;
            next--;
        }

        next = max + 1;
        while (numbers.remove(next)) {
            max = next;
            next++;
        }
        return max - min + 1;
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> numbers = new HashSet<>();

        for (int num : nums) {
            numbers.add(num);
        }

        int maxLength = 0;
        for (int num : numbers) {
            if (!visited.contains(num)) {
                int length = findConsecutiveSequenceLength(num, numbers, visited);
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }
        return maxLength;
    }

    private static int findConsecutiveSequenceLength(int element, Set<Integer> numbers, Set<Integer> visited) {
        int min = element;
        int max = element;
        visited.add(min);

        int next = min - 1;
        while (numbers.contains(next)) {
            visited.add(next);
            min = next;
            next--;
        }

        next = max + 1;
        while (numbers.contains(next)) {
            visited.add(next);
            max = next;
            next++;
        }
        return max - min + 1;
    }
}
