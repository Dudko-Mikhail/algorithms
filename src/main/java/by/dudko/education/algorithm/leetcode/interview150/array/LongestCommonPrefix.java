package by.dudko.education.algorithm.leetcode.interview150.array;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 14. Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * <p>
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * <p>
 * Constraints:
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters if it is non-empty.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        int prefixLength = 0;
        for (char symbol : strs[0].toCharArray()) {
            for (int i = 1; i < strs.length; i++) {
                if (prefixLength >= strs[i].length() || strs[i].charAt(prefixLength) != symbol) {
                    return strs[0].substring(0, prefixLength);
                }
            }
            prefixLength++;
        }
        return strs[0].substring(0, prefixLength);
    }
}
