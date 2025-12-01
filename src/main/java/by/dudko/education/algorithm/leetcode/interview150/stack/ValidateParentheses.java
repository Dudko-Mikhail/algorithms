package by.dudko.education.algorithm.leetcode.interview150.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-parentheses/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 20. Valid Parentheses
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Example 1:
 * Input: s = "()"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * <p>
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * <p>
 * Example 4:
 * Input: s = "([])"
 * Output: true
 * <p>
 * Example 5:
 * Input: s = "([)]"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class ValidateParentheses {
    private static final Map<Character, Character> PARENTHESES = Map.of('(', ')', '[', ']', '{', '}');

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char symbol : s.toCharArray()) {
            if (PARENTHESES.containsKey(symbol)) {
                stack.push(symbol);
                continue;
            }
            if (stack.isEmpty() || !PARENTHESES.get(stack.pop()).equals(symbol)) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
