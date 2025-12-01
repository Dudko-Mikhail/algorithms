package by.dudko.education.algorithm.leetcode.interview150.array;

/**
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * Find the Index of the First Occurrence in a String
 * <p>
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * <p>
 * Example 2:
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 * <p>
 * Constraints:
 * 1 <= haystack.length, needle.length <= 104
 * haystack and needle consist of only lowercase English characters.
 */
public class FindFirstOccurrenceInString {
    public static int strStr(String haystack, String needle) {
        int hl = haystack.length();
        int nl = needle.length();
        if (hl < nl) {
            return -1;
        }

        char[] needleChars = needle.toCharArray();
        char[] haystackChars = haystack.toCharArray();
        int i = 0;
        int startPosition;
        while (hl - i >= nl) {
            while (hl - i >= nl && haystackChars[i] != needleChars[0]) {
                i++;
            }
            if (hl - i < nl) {
                break;
            }

            startPosition = i;
            int j = 0;
            while (j < nl && needleChars[j] == haystackChars[i]) {
                i++;
                j++;
            }
            if (j == nl) {
                return startPosition;
            }

            i = startPosition + 1;
        }

        return -1;
    }
}
