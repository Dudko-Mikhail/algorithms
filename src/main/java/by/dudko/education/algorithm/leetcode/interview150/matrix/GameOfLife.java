package by.dudko.education.algorithm.leetcode.interview150.matrix;

/**
 * https://leetcode.com/problems/game-of-life/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 289. Game of Life
 * <p>
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1)
 * or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.
 * <p>
 * Given the current state of the board, update the board to reflect its next state.
 * Note that you do not need to return anything.
 * <p>
 * Example 1:
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * <p>
 * Example 2:
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 * <p>
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 * <p>
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 */
public class GameOfLife {
    public static void gameOfLife(int[][] board) {
        int n = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < n; j++) {
                processCell(i, j, board);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < n; j++) {
                int current = board[i][j];
                if (current == -3 || current >= 3 && current <= 4) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    private static void processCell(int row, int column, int[][] board) {
        int adder = board[row][column] == 0 ? -1 : 1;
        int endI = Math.min(board.length - 1, row + 1);
        int endJ = Math.min(board[0].length - 1, column + 1);

        for (int i = Math.max(0, row - 1); i <= endI; i++) {
            for (int j = Math.max(0, column - 1); j <= endJ; j++) {
                if (i == row && j == column) {
                    continue;
                }
                if (board[i][j] > 0) {
                    board[row][column] += adder;
                }
            }
        }
    }
}
