package by.dudko.education.algorithm.leetcode.study75.arraystring;

import java.util.Set;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 345. Reverse Vowels of a String
 * <p>
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 * <p>
 * Example 1:
 * Input: s = "IceCreAm"
 * Output: "AceCreIm"
 * <p>
 * Explanation:
 * The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".
 * <p>
 * Example 2:
 * Input: s = "leetcode"
 * Output: "leotcede"
 */
public class ReverseVowelsOfAString {
    private static final Set<Character> VOWELS = Set.of('a', 'A', 'q', 'Q', 'e', 'E', 'y', 'Y', 'u', 'U', 'i', 'I', 'o', 'O');

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char leftChar = chars[left];
            if (VOWELS.contains(leftChar)) {
                while (right > left) {
                    char rightChar = chars[right];
                    if (VOWELS.contains(rightChar)) {
                        chars[left] = rightChar;
                        chars[right--] = leftChar;
                        break;
                    }
                    right--;
                }
            }
            left++;
        }
        return new String(chars);
    }
}
