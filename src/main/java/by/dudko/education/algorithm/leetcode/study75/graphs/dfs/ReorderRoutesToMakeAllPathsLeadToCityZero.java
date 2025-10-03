package by.dudko.education.algorithm.leetcode.study75.graphs.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1466. Reorder Routes to Make All Paths Lead to the City Zero
 * <p>
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 * <p>
 * It's guaranteed that each city can reach city 0 after reorder.
 * <p>
 * Example 1:
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * <p>
 * Example 2:
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * <p>
 * Example 3:
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 * <p>
 * Constraints:
 * 2 <= n <= 5 * 104
 * connections.length == n - 1
 * connections[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 */
public class ReorderRoutesToMakeAllPathsLeadToCityZero {
    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> direct = new ArrayList<>(n);
        List<List<Integer>> reversed = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            direct.add(i, new ArrayList<>());
            reversed.add(i, new ArrayList<>());
        }
        for (int[] connection : connections) {
            int v1 = connection[0];
            int v2 = connection[1];
            direct.get(v1).add(v2);
            reversed.get(v2).add(v1);

        }
        List<Integer> toProcess = new ArrayList<>();
        toProcess.add(0);

        int reorderCount = 0;
        while (!toProcess.isEmpty()) {
            List<Integer> nextLayer = new ArrayList<>();
            for (Integer vertex : toProcess) {
                List<Integer> directConnections = direct.get(vertex);
                for (Integer connection : directConnections) {
                    nextLayer.add(connection);
                    reorderCount++;
                    reversed.get(connection).remove(vertex);
                }
                directConnections.clear();

                List<Integer> reversedConnections = reversed.get(vertex);
                for (Integer connection : reversedConnections) {
                    direct.get(connection).remove(vertex);
                }
                nextLayer.addAll(reversedConnections);
                reversedConnections.clear();
            }
            toProcess = nextLayer;
        }
        return reorderCount;
    }
}
