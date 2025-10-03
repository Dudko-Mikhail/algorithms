package by.dudko.education.algorithm.leetcode.study75.dynamicprogramming.multidimensional;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/unique-paths/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 62. Unique Paths
 * <p>
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 * <p>
 * Example 1:
 * Input: m = 3, n = 7
 * Output: 28
 * <p>
 * Example 2:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * <p>
 * Constraints:
 * 1 <= m, n <= 100
 */
public class UniquePath {
    public int uniquePaths(int rows, int cols) {
        Vertex[][] vertices = new Vertex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                vertices[i][j] = new Vertex(i, j);
            }
        }
        vertices[0][0].sum = 1;

        Queue<Vertex> toVisit = new LinkedList<>();
        toVisit.offer(vertices[0][0]);

        while (!toVisit.isEmpty()) {
            int currentLayer = toVisit.size();
            for (int i = 0; i < currentLayer; i++) {
                Vertex vertex = toVisit.remove();
                processVertex(vertex, vertices, toVisit);
            }
        }

        return vertices[rows - 1][cols - 1].sum;
    }

    private void processVertex(Vertex vertex, Vertex[][] vertices, Queue<Vertex> toVisit) {
        int row = vertex.row;
        int column = vertex.col;
        if (column + 1 < vertices[0].length) {
            Vertex right = vertices[row][column + 1];
            if (!right.visited) {
                toVisit.offer(right);
                right.visited = true;
            }
            vertices[row][column + 1].sum += vertex.sum;
        }

        if (row + 1 < vertices.length) {
            Vertex bottom = vertices[row + 1][column];
            if (!bottom.visited) {
                toVisit.offer(bottom);
                bottom.visited = true;
            }
            vertices[row + 1][column].sum += vertex.sum;
        }
    }


    private static class Vertex {
        int row;
        int col;
        int sum;
        boolean visited;

        Vertex(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    public static void main(String[] args) {
        UniquePath path = new UniquePath();
        System.out.println(path.uniquePaths(3, 7));
    }
}
