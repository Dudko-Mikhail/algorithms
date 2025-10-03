package by.dudko.education.algorithm.leetcode.study75.arraystring;

/**
 * https://leetcode.com/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 443. String Compression
 * <p>
 * Given an array of characters chars, compress it using the following algorithm:
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * After you are done modifying the input array, return the new length of the array.
 * You must write an algorithm that uses only constant extra space.
 * <p>
 * Example 1:
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 * <p>
 * Example 2:
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * Explanation: The only group is "a", which remains uncompressed since it's a single character.
 * <p>
 * Example 3:
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 * <p>
 * Constraints:
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 */
public class StringCompression {
    public int compress(char[] chars) {
        int replaceIndex = 0;
        char current = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            char symbol = chars[i];
            if (symbol == current) {
                count++;
            } else {
                replaceIndex = updateInputArray(chars, replaceIndex, current, count);
                count = 1;
                current = symbol;
            }
        }

        replaceIndex = updateInputArray(chars, replaceIndex, current, count);

        return replaceIndex;
    }

    private static int updateInputArray(char[] chars, int replaceIndex, char current, int count) {
        chars[replaceIndex++] = current;
        if (count != 1) {
            char[] countChars = Integer.toString(count).toCharArray();
            for (char countChar : countChars) {
                chars[replaceIndex++] = countChar;
            }
        }
        return replaceIndex;
    }
}
