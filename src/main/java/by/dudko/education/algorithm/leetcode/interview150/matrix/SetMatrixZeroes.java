package by.dudko.education.algorithm.leetcode.interview150.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 73. Set Matrix Zeroes
 * <p>
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 * <p>
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 * <p>
 * <p>
 * Follow up:
 * <p>
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    public static void setZeroes(int[][] matrix) {
        Set<Integer> zeroColumns = new HashSet<>();
        int columns = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            boolean hasZero = false;
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    hasZero = true;
                    zeroColumns.add(j);
                }
            }
            if (hasZero) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        zeroColumns.forEach(column -> setColumnZeroes(column, matrix));
    }

    public static void setZeroes2(int[][] matrix) {
        int n = matrix[0].length;
        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || columns[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static void setColumnZeroes(int column, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = 0;
        }
    }
}
