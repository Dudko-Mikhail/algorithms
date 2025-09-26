package by.dudko.education.algorithm.leetcode150.hashmap;

import java.util.*;

/**
 * https://leetcode.com/problems/isomorphic-strings/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 205. Isomorphic Strings
 * <p>
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 * <p>
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * Explanation:
 * The strings s and t can be made identical by:
 * Mapping 'e' to 'a'.
 * Mapping 'g' to 'd'.
 * <p>
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * Explanation:
 * The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.
 * <p>
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 * <p>
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {
    private static final char NOT_ASCII = (char) 256;

    public static boolean isIsomorphic(String s, String t) {
        Set<Character> values = new HashSet<>();
        Map<Character, Character> tReplacement = new HashMap<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            char sChar = sChars[i];
            char tChar = tChars[i];
            if (tReplacement.containsKey(sChar)) {
                if (!tReplacement.get(sChar).equals(tChar)) {
                    return false;
                }
            } else {
                if (values.contains(tChar)) {
                    return false;
                }
                values.add(tChar);
                tReplacement.put(sChar, tChar);
            }
        }
        return true;
    }

    public static boolean isIsomorphicArray(String s, String t) {
        int[] tReplacements = new int[256];
        Arrays.fill(tReplacements, -1);

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            int sCharIndex = sChars[i];
            int tCharIndex = tChars[i];
            if (tReplacements[sCharIndex] != -1) {
                if (tReplacements[sCharIndex] != tCharIndex) {
                    return false;
                }
            } else {
                for (int symbolIndex : tReplacements) {
                    if (symbolIndex == tCharIndex) {
                        return false;
                    }
                }
                tReplacements[sCharIndex] = tCharIndex;
            }
        }
        return true;
    }

    public static boolean isIsomorphicCharArray(String s, String t) {
        char[] tReplacements = new char[256];
        Arrays.fill(tReplacements, NOT_ASCII);
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            char sChar = sChars[i];
            char tChar = tChars[i];
            if (tReplacements[sChar] != NOT_ASCII) {
                if (tReplacements[sChar] != tChar) {
                    return false;
                }
            } else {
                for (char replacement : tReplacements) {
                    if (replacement == tChar) {
                        return false;
                    }
                }
                tReplacements[sChar] = tChar;
            }
        }
        return true;
    }
}
