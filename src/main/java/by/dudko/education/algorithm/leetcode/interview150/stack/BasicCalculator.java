package by.dudko.education.algorithm.leetcode.interview150.stack;

/**
 * https://leetcode.com/problems/basic-calculator/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 224. Basic Calculator
 * <p>
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * Example 1:
 * Input: s = "1 + 1"
 * Output: 2
 * <p>
 * Example 2:
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * <p>
 * Example 3:
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * <p>
 * Constraints:
 * 1 <= s.length <= 3 * 10^5
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {
    public static int calculate(String input) {
        input = input.replace(" ", "") + ")";
        return doCalculations(0, input).value;
    }

    private static OperationResult doCalculations(int startIndex, String input) {
        int result = 0;
        int i = startIndex;
        while (input.charAt(i) != ')') {
            OperationResult operationResult = switch (input.charAt(i)) {
                case '-' -> minus(i + 1, input);
                case '+' -> plus(i + 1, input);
                case '(' -> doCalculations(i + 1, input);
                default -> parseNumber(i, input);
            };
            i = operationResult.endIndex;
            result += operationResult.value;
        }
        return new OperationResult(result, i + 1);
    }

    private static OperationResult plus(int startIndex, String input) {
        char symbol = input.charAt(startIndex);
        OperationResult result = symbol == '(' ? doCalculations(startIndex + 1, input)
                : parseNumber(startIndex, input);
        return new OperationResult(result.value, result.endIndex);
    }

    private static OperationResult minus(int startIndex, String input) {
        char symbol = input.charAt(startIndex);
        if (symbol == '(') {
            OperationResult result = doCalculations(startIndex + 1, input);
            return new OperationResult(-result.value, result.endIndex);
        } else {
            return parseNumber(startIndex - 1, input);
        }
    }

    private static OperationResult parseNumber(int startIndex, String input) {
        int end = startIndex + 1;
        while (end < input.length() && Character.isDigit(input.charAt(end))) {
            end++;
        }
        return new OperationResult(Integer.parseInt(input.substring(startIndex, end)), end);
    }

    record OperationResult(int value, int endIndex) {
    }
}
