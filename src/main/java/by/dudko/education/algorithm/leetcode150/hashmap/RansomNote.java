package by.dudko.education.algorithm.leetcode150.hashmap;

/**
 * https://leetcode.com/problems/ransom-note/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 383. Ransom Note
 * <p>
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 * <p>
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * <p>
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * <p>
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * <p>
 * Constraints:
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote and magazine consist of lowercase English letters.
 */
public class RansomNote {
    private static final int LENGTH = 'z' - 'a' + 1;

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        int[] ransomNoteCountArray = buildCountArray(ransomNote);
        int[] magazineCountArray = buildCountArray(magazine);
        for (int i = 0; i < LENGTH; i++) {
            if (magazineCountArray[i] < ransomNoteCountArray[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] buildCountArray(String s) {
        int[] countArray = new int[LENGTH];
        for (char symbol : s.toCharArray()) {
            countArray[symbol - 'a']++;
        }
        return countArray;
    }
}
