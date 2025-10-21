package by.dudko.education.algorithm.leetcode.interview150.graph;

import java.util.function.BiFunction;

/**
 * https://leetcode.com/problems/surrounded-regions/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 130. Surrounded Regions
 * <p>
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
 * <p>
 * Example 1:
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation:
 * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
 * <p>
 * Example 2:
 * Input: board = [["X"]]
 * Output: [["X"]]
 * <p>
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {
    private static final char ZERO = 'O';
    private static final char TEMP_CHAR = '*';

    public static void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == ZERO && isSurrounded(i, j, board)) {
                    replaceToX(i, j, board);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == TEMP_CHAR) {
                    board[i][j] = ZERO;
                }
            }
        }
    }

    private static boolean isSurrounded(int row, int column, char[][] board) {
        if (board[row][column] != ZERO) {
            return true;
        }
        board[row][column] = TEMP_CHAR;
        boolean isSurrounded = !isEdgeVertex(row, column, board);

        if (row - 1 >= 0) {
            isSurrounded &= isSurrounded(row - 1, column, board);
        }
        if (row + 1 < board.length) {
            isSurrounded &= isSurrounded(row + 1, column, board);
        }
        if (column + 1 < board[0].length) {
            isSurrounded &= isSurrounded(row, column + 1, board);
        }
        if (column - 1 >= 0) {
            isSurrounded &= isSurrounded(row, column - 1, board);
        }
        return isSurrounded;
    }

    private static void replaceToX(int row, int column, char[][] board) {
        if (board[row][column] != TEMP_CHAR) {
            return;
        }
        board[row][column] = 'X';
        if (row - 1 >= 0) {
            replaceToX(row - 1, column, board);
        }
        if (row + 1 < board.length) {
            replaceToX(row + 1, column, board);
        }
        if (column - 1 >= 0) {
            replaceToX(row, column - 1, board);
        }
        if (column + 1 < board[0].length) {
            replaceToX(row, column + 1, board);
        }
    }

    private static boolean isEdgeVertex(int row, int column, char[][] board) {
        return row == 0 || row == board.length - 1 || column == 0 || column == board[0].length - 1;
    }

    interface DirectionCheck {
        boolean proceed(int value, int end);

        <R> R recall(int row, int column, BiFunction<Integer, Integer, R> recallFunction);
    }

    enum Directions implements DirectionCheck {
        DOWN(1, 0),
        RIGHT(0, 1) {
            @Override
            public boolean proceed(int value, int end) {
                return value >= 0;
            }
        },
        UP(-1, 0) {
            @Override
            public boolean proceed(int value, int end) {
                return value >= 0;
            }
        },
        LEFT(0, -1);

        private final int rowDiff;
        private final int columnDiff;

        Directions(int rowDiff, int columnDiff) {
            this.rowDiff = rowDiff;
            this.columnDiff = columnDiff;
        }


        @Override
        public boolean proceed(int value, int end) {
            return value < end;
        }

        @Override
        public <R> R recall(int row, int column, BiFunction<Integer, Integer, R> recallFunction) {
            return recallFunction.apply(row + rowDiff, column + columnDiff);
        }
    }
}
