package by.dudko.education.algorithm.leetcode.interview150.graph;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 200. Number of Islands
 * <p>
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * <p>
 * Example 2:
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        int islandsCount = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    performBfs(i, j, grid, visited);
                    islandsCount++;
                }
            }
        }
        return islandsCount;
    }

    private static void performBfs(int i, int j, char[][] grid, boolean[][] visited) {
        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[]{i, j});
        while (!toVisit.isEmpty()) {
            int[] current = toVisit.remove();
            int row = current[0];
            int column = current[1];
            if (visited[row][column]) {
                continue;
            }
            visited[row][column] = true;

            if (row + 1 < grid.length && !visited[row + 1][column] && grid[row + 1][column] == '1') {
                toVisit.offer(new int[]{row + 1, column});
            }
            if (column + 1 < grid[0].length && !visited[row][column + 1] && grid[row][column + 1] == '1') {
                toVisit.offer(new int[]{row, column + 1});
            }
            if (column - 1 >= 0 && !visited[row][column - 1] && grid[row][column - 1] == '1') {
                toVisit.offer(new int[]{row, column - 1});
            }
            if (row - 1 >= 0 && !visited[row - 1][column] && grid[row - 1][column] == '1') {
                toVisit.offer(new int[]{row - 1, column});
            }
        }
    }

    public static int numIslandsWithArrayCorruption(char[][] grid) {
        int islandsCount = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    performBfs(i, j, grid);
                    islandsCount++;
                }
            }
        }
        return islandsCount;
    }

    private static void performBfs(int i, int j, char[][] grid) {
        Queue<Entry<Integer, Integer>> toVisit = new ArrayDeque<>();
        grid[i][j] = '0';
        toVisit.offer(Map.entry(i, j));
        while (!toVisit.isEmpty()) {
            Entry<Integer, Integer> current = toVisit.remove();
            int row = current.getKey();
            int column = current.getValue();

            if (row + 1 < grid.length && grid[row + 1][column] == '1') {
                toVisit.offer(Map.entry(row + 1, column));
                grid[row + 1][column] = '0';
            }
            if (column + 1 < grid[0].length && grid[row][column + 1] == '1') {
                toVisit.offer(Map.entry(row, column + 1));
                grid[row][column + 1] = '0';
            }
            if (column - 1 >= 0 && grid[row][column - 1] == '1') {
                toVisit.offer(Map.entry(row, column - 1));
                grid[row][column - 1] = '0';
            }
            if (row - 1 >= 0 && grid[row - 1][column] == '1') {
                toVisit.offer(Map.entry(row - 1, column));
                grid[row - 1][column] = '0';
            }
        }
    }

    public static int numIslandsWithArrayCorruptionDfs(char[][] grid) {
        int islandsCount = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    performDfs(i, j, grid);
                    islandsCount++;
                }
            }
        }
        return islandsCount;
    }

    private static void performDfs(int row, int column, char[][] grid) {
        grid[row][column] = '0';

        if (row + 1 < grid.length && grid[row + 1][column] == '1') {
            performDfs(row + 1, column, grid);
        }
        if (row - 1 >= 0 && grid[row - 1][column] == '1') {
            performDfs(row - 1, column, grid);
        }
        if (column + 1 < grid[0].length && grid[row][column + 1] == '1') {
            performDfs(row, column + 1, grid);
        }
        if (column - 1 >= 0 && grid[row][column - 1] == '1') {
            performDfs(row, column - 1, grid);
        }
    }
}
