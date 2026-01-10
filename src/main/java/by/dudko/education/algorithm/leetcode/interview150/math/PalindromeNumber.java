package by.dudko.education.algorithm.leetcode.interview150.math;

/**
 * https://leetcode.com/problems/palindrome-number/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 9. Palindrome Number
 * <p>
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * <p>
 * Example 1:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * <p>
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * <p>
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * <p>
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 * <p>
 * Follow up: Could you solve it without converting the integer to a string?
 */
public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        int reverse = 0;
        int copy = x;
        while (copy > 0) {
            int digit = copy % 10;
            copy /= 10;
            reverse = reverse * 10 + digit;
        }
        return reverse == x;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        int length = (int) Math.log10(x) + 1;
        int copy = x;
        int pow = 1;
        for (int i = 1; i < length; i++) {
            pow *= 10;
        }
        for (int i = 0; i < length / 2; i++) {
            int right = x % 10;
            int left = copy / pow;
            if (left != right) {
                return false;
            }
            x /= 10;
            copy -= pow * left;
            pow /= 10;
        }
        return true;
    }
}
