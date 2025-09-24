package by.dudko.education.algorithm.leetcode150.hashmap;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 49. Group Anagrams
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Explanation:
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {
    private static final int LENGTH = 'z' - 'a' + 1;

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<int[], List<String>> answer = new HashMap<>();
        for (String word : strs) {
            int[] counts = toCountArray(word);
            boolean isAbsent = true;
            for (int[] key : answer.keySet()) {
                if (Arrays.equals(key, counts)) {
                    answer.get(key).add(word);
                    isAbsent = false;
                    break;
                }
            }
            if (isAbsent) {
                answer.computeIfAbsent(counts, key -> new ArrayList<>()).add(word);
            }
        }
        return new ArrayList<>(answer.values());
    }

    private static int[] toCountArray(String word) {
        int[] counts = new int[LENGTH];
        for (char symbol : word.toCharArray()) {
            counts[symbol - 'a']++;
        }
        return counts;
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<Map<Character, Integer>, List<String>> answer = new HashMap<>();
        for (String word : strs) {
            answer.computeIfAbsent(toCountMap(word), key -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(answer.values());
    }

    private static Map<Character, Integer> toCountMap(String word) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char symbol : word.toCharArray()) {
            countMap.merge(symbol, 1, Integer::sum);
        }
        return countMap;
    }

    public static List<List<String>> groupAnagramsWithSorting(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            groups.computeIfAbsent(sorted, key -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }
}
