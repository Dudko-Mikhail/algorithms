package by.dudko.education.algorithm.leetcode.interview150.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 54. Spiral Matrix
 * <p>
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        int i = 0;
        int j = 0;
        int counter = 0;
        int cycleCounter = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int end = n * m;
        State currentState = State.RIGHT;
        int[] move = currentState.move();
        List<Integer> result = new ArrayList<>();

        while (counter < end) {
            if (currentState == State.UP) {
                cycleCounter++;
            }

            while (currentState.shouldContinue(i, j, n, m, cycleCounter)) {
                counter++;
                result.add(matrix[i][j]);
                i += move[0];
                j += move[1];
            }
            i -= move[0];
            j -= move[1];
            currentState = currentState.next();
            move = currentState.move();
            i += move[0];
            j += move[1];
        }

        return result;
    }

    private enum State {
        RIGHT {
            @Override
            boolean shouldContinue(int i, int j, int n, int m, int cycleCount) {
                return j < m - cycleCount;
            }
        },
        BOTTOM {
            @Override
            boolean shouldContinue(int i, int j, int n, int m, int cycleCount) {
                return i < n - cycleCount;
            }
        },
        LEFT {
            @Override
            boolean shouldContinue(int i, int j, int n, int m, int cycleCount) {
                return j >= cycleCount;
            }
        },
        UP {
            @Override
            boolean shouldContinue(int i, int j, int n, int m, int cycleCount) {
                return i >= cycleCount;
            }
        };

        static final int[][] MOVES = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int[] move() {
            return MOVES[ordinal()];
        }

        abstract boolean shouldContinue(int i, int j, int n, int m, int cycleCount);

        State next() {
            return State.values()[(ordinal() + 1) % 4];
        }
    }
}
