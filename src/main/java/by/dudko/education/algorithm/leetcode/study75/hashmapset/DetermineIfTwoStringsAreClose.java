package by.dudko.education.algorithm.leetcode.study75.hashmapset;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/determine-if-two-strings-are-close/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1657. Determine if Two Strings Are Close
 * <p>
 * Two strings are considered close if you can attain one from the other using the following operations:
 * <p>
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 * <p>
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 * <p>
 * Example 1:
 * Input: word1 = "abc", word2 = "bca"
 * Output: true
 * Explanation: You can attain word2 from word1 in 2 operations.
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 * <p>
 * Example 2:
 * Input: word1 = "a", word2 = "aa"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 * <p>
 * Example 3:
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 * <p>
 * Constraints:
 * 1 <= word1.length, word2.length <= 105
 * word1 and word2 contain only lowercase English letters.
 */
public class DetermineIfTwoStringsAreClose {

    private static final int COUNT_ARRAY_SIZE = 'z' - 'a' + 1;

    public boolean closeStrings(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int[] firstCountArray = buildCountArray(first);
        int[] secondCountArray = buildCountArray(second);

        Map<Integer, Integer> firstCountMap = new HashMap<>();
        Map<Integer, Integer> secondCountMap = new HashMap<>();
        for (int i = 0; i < COUNT_ARRAY_SIZE; i++) {
            int firstCount = firstCountArray[i];
            int secondCount = secondCountArray[i];
            if ((firstCount != 0 && secondCount == 0) || (firstCount == 0 && secondCount != 0)) {
                return false;
            }
            firstCountMap.merge(firstCount, 1, Integer::sum);
            secondCountMap.merge(secondCount, 1, Integer::sum);
        }
        return firstCountMap.equals(secondCountMap);
    }

    private int[] buildCountArray(String str) {
        int[] countArray = new int[COUNT_ARRAY_SIZE];
        for (char symbol : str.toCharArray()) {
            countArray[symbol - 'a']++;
        }
        return countArray;
    }

    public static void main(String[] args) {
        DetermineIfTwoStringsAreClose alg = new DetermineIfTwoStringsAreClose();
        System.out.println(alg.closeStrings("abbzzca", "babzzcz"));
    }
}
