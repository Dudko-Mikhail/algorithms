package by.dudko.education.algorithm.leetcode.interview150.slidingwindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 30. Substring with Concatenation of All Words
 * <p>
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings.
 * "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
 * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation:
 * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 * <p>
 * Example 2:
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * Explanation:
 * There is no concatenated substring.
 * <p>
 * Example 3:
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 * Explanation:
 * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
 * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
 * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * s and words[i] consist of lowercase English letters.
 */
public class SubstringWithConcatenationOfAllWords {
    public static List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordsCountMap = new HashMap<>();
        for (String word : words) {
            wordsCountMap.merge(word, 1, Integer::sum);
        }

        int wordLength = words[0].length();
        int concatenatedLength = wordLength * words.length;
        int stopIndex = s.length() - concatenatedLength;
        int wordsCount = words.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= stopIndex; i++) {
            if (checkSubstring(i, wordLength, wordsCount, s, wordsCountMap)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean checkSubstring(int i, int wordLength, int wordsCount,
            String input, Map<String, Integer> wordsCountMap) {
        int words = 0;
        Map<String, Integer> countMap = new HashMap<>();
        while (words < wordsCount) {
            String current = input.substring(i, i + wordLength);
            int count = countMap.merge(current, 1, Integer::sum);
            if (count > wordsCountMap.getOrDefault(current, 0)) {
                return false;
            }
            i += wordLength;
            words++;
        }
        return true;
    }


    public static List<Integer> findSubstringOptimized(String s, String[] words) {
        Map<String, Integer> wordsCountMap = new HashMap<>();
        for (String word : words) {
            wordsCountMap.merge(word, 1, Integer::sum);
        }

        int sLen = s.length();
        int wordLength = words[0].length();
        int wordsCount = words.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            if (i + wordsCount * wordLength > sLen) {
                break;
            }
            Queue<String> wordsQueue = new ArrayDeque<>(wordsCount);
            Map<String, Integer> countMap = new HashMap<>();
            int j = i;
            for (int k = 0; k < wordsCount; k++) {
                String word = s.substring(j, j + wordLength);
                wordsQueue.offer(word);
                countMap.merge(word, 1, Integer::sum);
                j += wordLength;
            }
            if (wordsCountMap.equals(countMap)) {
                result.add(i);
            }

            int readIndex = i + wordLength;
            while (j + wordLength <= sLen) {
                String previous = wordsQueue.remove();
                countMap.computeIfPresent(previous, (k, v) -> v - 1 != 0 ? v - 1 : null);
                String current = s.substring(j, j + wordLength);
                wordsQueue.offer(current);
                countMap.merge(current, 1, Integer::sum);
                if (wordsCountMap.equals(countMap)) {
                    result.add(readIndex);
                }
                readIndex += wordLength;
                j += wordLength;
            }
        }
        return result;
    }

    static void main() {
        System.out.println(findSubstringOptimized("abcdefghijklm", new String[]{"abc", "def"}));
    }
}
