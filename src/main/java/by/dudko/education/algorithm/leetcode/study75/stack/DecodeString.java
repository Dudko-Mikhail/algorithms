package by.dudko.education.algorithm.leetcode.study75.stack;

/**
 * https://leetcode.com/problems/decode-string/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 394. Decode String
 * <p>
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 105.
 * <p>
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * <p>
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * <p>
 * Example 3:
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * <p>
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {
    public static String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        char[] symbols = s.toCharArray();
        for (int i = 0; i < symbols.length; i++) {
            char current = symbols[i];
            if (!Character.isDigit(current)) {
                result.append(current);
            } else {
                StringBuilder digitStr = new StringBuilder();
                while (Character.isDigit(symbols[i])) {
                    digitStr.append(symbols[i++]);
                }
                int digit = Integer.parseInt(digitStr.toString());
                i = doDecode(symbols, i + 1, digit, result);
            }
        }
        return result.toString();
    }

    public static String decodeString2(String s) {
        StringBuilder result = new StringBuilder();
        doDecode((s + "]").toCharArray(), 0, 1, result);
        return result.toString();
    }

    private static int doDecode(char[] symbols, int start, int digit, StringBuilder answer) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < symbols.length; i++) {
            char current = symbols[i];
            if (current == ']') {
                String str = result.toString().repeat(digit);
                answer.append(str);
                return i;
            } else if (!Character.isDigit(current)) {
                result.append(current);
            } else {
                StringBuilder digitStr = new StringBuilder();
                while (Character.isDigit(symbols[i])) {
                    digitStr.append(symbols[i++]);
                }
                int curDigit = Integer.parseInt(digitStr.toString());
                i = doDecode(symbols, i + 1, curDigit, result);
            }
        }
        throw new IllegalStateException("Invalid input!");
    }
}
