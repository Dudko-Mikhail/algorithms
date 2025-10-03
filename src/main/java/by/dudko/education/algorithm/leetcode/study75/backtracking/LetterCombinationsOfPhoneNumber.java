package by.dudko.education.algorithm.leetcode.study75.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 17. Letter Combinations of a Phone Number
 * <p>
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * Example 2:
 * Input: digits = ""
 * Output: []
 * <p>
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOfPhoneNumber {
    private static final char[][] COMBINATIONS;

    static {
        COMBINATIONS = new char[10][];
        COMBINATIONS[2] = new char[]{'a', 'b', 'c'};
        COMBINATIONS[3] = new char[]{'d', 'e', 'f'};
        COMBINATIONS[4] = new char[]{'g', 'h', 'i'};
        COMBINATIONS[5] = new char[]{'j', 'k', 'l'};
        COMBINATIONS[6] = new char[]{'m', 'n', 'o'};
        COMBINATIONS[7] = new char[]{'p', 'q', 'r', 's'};
        COMBINATIONS[8] = new char[]{'t', 'u', 'v'};
        COMBINATIONS[9] = new char[]{'w', 'x', 'y', 'z'};
    }

    public List<String> letterCombinations(String digitsStr) {
        int[] digits = new int[digitsStr.length()];
        char[] charArray = digitsStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            digits[i] = Integer.parseInt(Character.toString(charArray[i]));
        }
        return getCombination(0, digits);
    }

    private List<String> getCombination(int currentIndex, int[] digits) {
        if (currentIndex == digits.length) {
            return new ArrayList<>();
        }

        int currentDigit = digits[currentIndex];
        List<String> strings = new ArrayList<>();

        for (char symbol : COMBINATIONS[currentDigit]) {
            List<String> nextSymbolCombinations = getCombination(currentIndex + 1, digits);
            if (!nextSymbolCombinations.isEmpty()) {
                for (String combination : nextSymbolCombinations) {
                    strings.add(symbol + combination);
                }
            } else {
                strings.add(String.valueOf(symbol));
            }
        }
        return strings;
    }
}
