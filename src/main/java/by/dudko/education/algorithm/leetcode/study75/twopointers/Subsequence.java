package by.dudko.education.algorithm.leetcode.study75.twopointers;

/**
 * https://leetcode.com/problems/is-subsequence/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 392. Is Subsequence
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 * <p>
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 * <p>
 * Constraints:
 * 0 <= s.length <= 100
 * 0 <= t.length <= 104
 * s and t consist only of lowercase English letters.
 * <p>
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 */
public class Subsequence {
    public boolean isSubsequence(String subsequence, String input) {
        if (subsequence.length() > input.length()) {
            return false;
        }
        if (subsequence.isEmpty()) {
            return true;
        }

        int start = 0;
        char current = subsequence.charAt(start);
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == current) {
                start++;
                if (start == subsequence.length()) {
                    return true;
                }
                current = subsequence.charAt(start);
            }
        }
        return false;
    }
}
