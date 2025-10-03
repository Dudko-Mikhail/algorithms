package by.dudko.education.algorithm.leetcode.interview150.hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 202. Happy Number
 * <p>
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * Example 1:
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * <p>
 * Example 2:
 * Input: n = 2
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2^31 - 1
 */
public class HappyNumber {
    private static final int[] SQUIRES = new int[]{0, 1, 4, 9, 16, 25, 36, 49, 64, 81};

    public static boolean isHappy(int n) {
        Set<Integer> checked = new HashSet<>();
        int start = n;
        while (start != 1) {
            if (checked.contains(start)) {
                return false;
            }
            checked.add(start);
            start = computeSum(start);
        }
        return true;
    }

    public static boolean isHappyFastAndSlowPointers(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = computeSum(slow);
            fast = computeSum(fast);
            fast = computeSum(fast);
            if (slow == 1) {
                return true;
            }
        } while (slow != fast);
        return false;
    }

    private static int computeSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += SQUIRES[number % 10];
            number /= 10;
        }
        return sum;
    }
}
