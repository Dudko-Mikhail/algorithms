package by.dudko.education.algorithm.leetcode.study75.graphs.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/number-of-provinces/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 547. Number of Provinces
 * <p>
 * There are n cities. Some of them are connected, while some are not. If a city is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 * <p>
 * Example 1:
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int size = isConnected.length;
        Set<Integer> visited = new HashSet<>();

        int counter = 0;
        for (int i = 0; i < size && visited.size() != size; i++) {
            if (visited.contains(i)) {
                continue;
            }
            visited.add(i);
            performBfs(i, isConnected, visited);
            counter++;
        }
        return counter;
    }

    private void performBfs(int current, int[][] isConnected, Set<Integer> visited) {
        List<Integer> currentLayer = new ArrayList<>();
        currentLayer.add(current);

        while (!currentLayer.isEmpty()) {
            List<Integer> nextLayer = new ArrayList<>();

            for (int vertex : currentLayer) {
                processVertex(vertex, isConnected, visited, nextLayer);
                currentLayer = nextLayer;
            }
        }
    }

    private void processVertex(int vertex, int[][] isConnected, Set<Integer> visited, List<Integer> nextLayer) {
        int[] vertexConnections = isConnected[vertex];
        for (int i = 0; i < isConnected.length; i++) {
            if (i != vertex && vertexConnections[i] == 1 && !visited.contains(i)) {
                visited.add(i);
                nextLayer.add(i);
            }
        }
    }
}
