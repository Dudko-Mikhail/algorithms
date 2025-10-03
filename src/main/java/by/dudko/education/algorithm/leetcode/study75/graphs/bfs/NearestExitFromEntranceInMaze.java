package by.dudko.education.algorithm.leetcode.study75.graphs.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1926. Nearest Exit from Entrance in Maze
 * <p>
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 * <p>
 * Example 1:
 * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * Output: 1
 * Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
 * Initially, you are at the entrance cell [1,2].
 * - You can reach [1,0] by moving 2 steps left.
 * - You can reach [0,2] by moving 1 step up.
 * It is impossible to reach [2,3] from the entrance.
 * Thus, the nearest exit is [0,2], which is 1 step away.
 * <p>
 * Example 2:
 * Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * Output: 2
 * Explanation: There is 1 exit in this maze at [1,2].
 * [1,0] does not count as an exit since it is the entrance cell.
 * Initially, you are at the entrance cell [1,0].
 * - You can reach [1,2] by moving 2 steps right.
 * Thus, the nearest exit is [1,2], which is 2 steps away.
 * <p>
 * Example 3:
 * Input: maze = [[".","+"]], entrance = [0,0]
 * Output: -1
 * Explanation: There are no exits in this maze.
 * <p>
 * Constraints:
 * maze.length == m
 * maze[i].length == n
 * 1 <= m, n <= 100
 * maze[i][j] is either '.' or '+'.
 * entrance.length == 2
 * 0 <= entrancerow < m
 * 0 <= entrancecol < n
 * entrance will always be an empty cell.
 */
public class NearestExitFromEntranceInMaze {
    public int nearestExit(char[][] maze, int[] entrance) {
        List<int[]> currentLayer = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int length = 0;
        currentLayer.add(entrance);

        while (!currentLayer.isEmpty()) {
            List<int[]> nextLayer = new ArrayList<>();
            for (int[] vertex : currentLayer) {
                int row = vertex[0];
                int column = vertex[1];
                if (visited[row][column]) {
                    continue;
                }
                visited[row][column] = true;
                if (!Arrays.equals(vertex, entrance) && isBorder(maze, row, column)) {
                    return length;
                }

                if (column - 1 >= 0 && !visited[row][column - 1] && maze[row][column - 1] == '.') {
                    nextLayer.add(new int[]{row, column - 1});
                }

                if (column + 1 < maze[0].length && !visited[row][column + 1] && maze[row][column + 1] == '.') {
                    nextLayer.add(new int[]{row, column + 1});
                }

                if (row - 1 >= 0 && !visited[row - 1][column] && maze[row - 1][column] == '.') {
                    nextLayer.add(new int[]{row - 1, column});
                }

                if (row + 1 < maze.length && !visited[row + 1][column] && maze[row + 1][column] == '.') {
                    nextLayer.add(new int[]{row + 1, column});
                }
            }
            length++;
            currentLayer = nextLayer;
        }
        return -1;
    }


    public int nearestExitQueue(char[][] maze, int[] entrance) {
        int length = 0;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(entrance);

        while (!queue.isEmpty()) {
            int layerSize = queue.size();
            for (int i = 0; i < layerSize; i++) {
                int[] vertex = queue.remove();
                int row = vertex[0];
                int column = vertex[1];
                if (visited[row][column]) {
                    continue;
                }
                visited[row][column] = true;
                if (!Arrays.equals(vertex, entrance) && isBorder(maze, row, column)) {
                    return length;
                }

                if (column - 1 >= 0 && !visited[row][column - 1] && maze[row][column - 1] == '.') {
                    queue.offer(new int[]{row, column - 1});
                }

                if (column + 1 < maze[0].length && !visited[row][column + 1] && maze[row][column + 1] == '.') {
                    queue.offer(new int[]{row, column + 1});
                }

                if (row - 1 >= 0 && !visited[row - 1][column] && maze[row - 1][column] == '.') {
                    queue.offer(new int[]{row - 1, column});
                }

                if (row + 1 < maze.length && !visited[row + 1][column] && maze[row + 1][column] == '.') {
                    queue.offer(new int[]{row + 1, column});
                }
            }
            length++;
        }
        return -1;
    }


    private boolean isBorder(char[][] maze, int row, int column) {
        if (row == 0 || row == maze.length - 1) {
            return true;
        }
        return column == 0 || column == maze[0].length - 1;
    }
}
