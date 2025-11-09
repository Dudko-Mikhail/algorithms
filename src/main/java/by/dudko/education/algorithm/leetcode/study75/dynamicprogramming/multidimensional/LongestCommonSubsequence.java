package by.dudko.education.algorithm.leetcode.study75.dynamicprogramming.multidimensional;

/**
 * https://leetcode.com/problems/longest-common-subsequence/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1143. Longest Common Subsequence
 * <p>
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * <p>
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * <p>
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * <p>
 * Constraints:
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] first = text1.toCharArray();
        int[] matrix = new int[first.length + 1];
        for (char current : text2.toCharArray()) {
            int[] nextLayer = new int[first.length + 1];
            for (int j = 1; j <= first.length; j++) {
                if (first[j - 1] == current) {
                    nextLayer[j] = 1 + matrix[j - 1];
                } else {
                    nextLayer[j] = Math.max(matrix[j], nextLayer[j - 1]);
                }
            }
            matrix = nextLayer;
        }
        return matrix[first.length];
    }
}
