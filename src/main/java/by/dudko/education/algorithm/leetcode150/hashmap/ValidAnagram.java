package by.dudko.education.algorithm.leetcode150.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-anagram/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 242. Valid Anagram
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "rat", t = "car"
 * <p>
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 * <p>
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class ValidAnagram {
    private static final int LENGTH = 'z' - 'a' + 1;

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counts = new int[LENGTH];
        for (char symbol : s.toCharArray()) {
            counts[symbol - 'a']++;
        }

        for (char symbol : t.toCharArray()) {
            counts[symbol - 'a']--;
            if (counts[symbol - 'a'] < 0) {
                return false;
            }
        }

        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagramMapSolution(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        for (char symbol : s.toCharArray()) {
            countMap.merge(symbol, 1, Integer::sum);
        }

        for (char symbol : t.toCharArray()) {
            Integer result = countMap.computeIfPresent(symbol, (key, value) -> value - 1);
            if (result == null || result < 0) {
                return false;
            }
        }
        return true;
    }
}
