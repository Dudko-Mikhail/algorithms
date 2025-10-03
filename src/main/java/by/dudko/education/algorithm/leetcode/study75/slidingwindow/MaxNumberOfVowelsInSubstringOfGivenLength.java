package by.dudko.education.algorithm.leetcode.study75.slidingwindow;

/**
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 * <p>
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 * <p>
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * <p>
 * Example 2:
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * <p>
 * Example 3:
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 * <p>
 * Constraints:
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 */
public class MaxNumberOfVowelsInSubstringOfGivenLength {
    private static final boolean[] VOWELS = new boolean['z' - 'a' + 1];

    static {
        VOWELS[0] = true;
        VOWELS['e' - 'a'] = true;
        VOWELS['i' - 'a'] = true;
        VOWELS['o' - 'a'] = true;
        VOWELS['u' - 'a'] = true;
    }

    public int maxVowels(String s, int k) {
        int vowelsCount = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < k; i++) {
            if (VOWELS[chars[i] - 'a']) {
                vowelsCount++;
            }
        }
        if (vowelsCount == k) {
            return k;
        }

        int maxCount = vowelsCount;
        int start = 0;
        for (int i = k; i < chars.length; i++) {
            if (VOWELS[chars[i] - 'a']) {
                vowelsCount++;
            }
            if (VOWELS[chars[start] - 'a']) {
                vowelsCount--;
            }
            if (vowelsCount > maxCount) {
                maxCount = vowelsCount;
                if (maxCount == k) {
                    return k;
                }
            }
            start++;
        }
        return maxCount;
    }
}
