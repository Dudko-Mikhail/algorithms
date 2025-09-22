package by.dudko.education.algorithm.leetcode150.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-pattern/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 290. Word Pattern
 * <p>
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:
 * Each letter in pattern maps to exactly one unique word in s.
 * Each unique word in s maps to exactly one letter in pattern.
 * No two letters map to the same word, and no two words map to the same letter.
 * <p>
 * Example 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * Explanation:
 * The bijection can be established as:
 * 'a' maps to "dog".
 * 'b' maps to "cat".
 * <p>
 * Example 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 * <p>
 * Example 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String input) {
        String[] words = input.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        Set<String> uniqueValues = new HashSet<>();
        Map<Character, String> symbolToWord = new HashMap<>();
        int i = 0;
        for (Character symbol : pattern.toCharArray()) {
            String word = words[i++];
            if (symbolToWord.containsKey(symbol)) {
                if (!symbolToWord.get(symbol).equals(word)) {
                    return false;
                }
            } else {
                if (uniqueValues.contains(word)) {
                    return false;
                }
                uniqueValues.add(word);
                symbolToWord.put(symbol, word);
            }
        }
        return true;
    }
}
