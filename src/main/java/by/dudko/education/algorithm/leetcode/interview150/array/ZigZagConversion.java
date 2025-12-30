package by.dudko.education.algorithm.leetcode.interview150.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/zigzag-conversion/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 6. Zigzag Conversion
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 * <p>
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * <p>
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * Example 3:
 * Input: s = "A", numRows = 1
 * Output: "A"
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 */
public class ZigZagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        boolean isDown = true;
        StringBuilder[] builders = IntStream.range(0, numRows)
                .mapToObj(StringBuilder::new)
                .toArray(StringBuilder[]::new);
        int position = 0;
        for (char symbol : s.toCharArray()) {
            builders[position].append(symbol);
            if (isDown) {
                position++;
            } else {
                position--;
            }
            if (position == numRows) {
                isDown = false;
                position = Math.max(0, numRows - 2);
            }
            if (position == -1) {
                isDown = true;
                position = 1;
            }
        }
        return Arrays.stream(builders)
                .reduce(StringBuilder::append)
                .orElseThrow()
                .toString();
    }
}
