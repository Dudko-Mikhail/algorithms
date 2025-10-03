package by.dudko.education.algorithm.leetcode.study75.graphs.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 994. Rotting Oranges
 * <p>
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * <p>
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * <p>
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * <p>
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class RottingOranges {
    private static final int FRESH = 1;
    private static final int ROTTEN = 2;

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int minutes = 0;
        int freshOrangeCount = 0;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> toVisit = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == ROTTEN) {
                    toVisit.offer(new int[]{i, j});
                } else if (grid[i][j] == FRESH) {
                    freshOrangeCount++;
                }
            }
        }

        while (!toVisit.isEmpty()) {
            int layerSize = toVisit.size();
            for (int i = 0; i < layerSize; i++) {
                int[] vertex = toVisit.remove();
                int row = vertex[0];
                int column = vertex[1];

                if (visited[row][column]) {
                    continue;
                }
                visited[row][column] = true;
                if (grid[row][column] == FRESH) {
                    freshOrangeCount--;
                }

                if (row - 1 >= 0 && !visited[row - 1][column] && grid[row - 1][column] == FRESH) {
                    toVisit.offer(new int[]{row - 1, column});
                }
                if (row + 1 < n && !visited[row + 1][column] && grid[row + 1][column] == FRESH) {
                    toVisit.offer(new int[]{row + 1, column});
                }
                if (column - 1 >= 0 && !visited[row][column - 1] && grid[row][column - 1] == FRESH) {
                    toVisit.offer(new int[]{row, column - 1});
                }
                if (column + 1 < m && !visited[row][column + 1] && grid[row][column + 1] == FRESH) {
                    toVisit.offer(new int[]{row, column + 1});
                }
            }
            if (freshOrangeCount == 0) {
                break;
            }
            minutes++;
        }

        return freshOrangeCount == 0 ? minutes : -1;
    }
}
