package by.dudko.education.algorithm.leetcode.study75.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/evaluate-division/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 399. Evaluate Division
 * <p>
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 * <p>
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * note: x is undefined => -1.0
 * <p>
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * <p>
 * Example 3:
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * Constraints:
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Edge>> adjacencyList = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String firstVertex = equation.get(0);
            String secondVertex = equation.get(1);
            double value = values[i];
            adjacencyList.computeIfAbsent(firstVertex, vertex -> new ArrayList<>())
                    .add(new Edge(secondVertex, value));
            adjacencyList.computeIfAbsent(secondVertex, vertex -> new ArrayList<>())
                    .add(new Edge(firstVertex, 1 / value));

        }

        double[] result = new double[queries.size()];
        Set<String> vertices = adjacencyList.keySet();
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String firstVertex = query.get(0);
            String secondVertex = query.get(1);
            if (vertices.contains(firstVertex) && vertices.contains(secondVertex)) {
                result[i] = performDfs(adjacencyList, firstVertex, secondVertex);
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    private double performDfs(Map<String, List<Edge>> adjacencyList, String start, String target) {
        Double result = doDfs(adjacencyList, start, target, new HashSet<>(), 1.0);
        return result != null ? result : -1.0;
    }


    private Double doDfs(Map<String, List<Edge>> adjacencyList, String start, String target, Set<String> visited,
            double startProduct) {
        if (visited.contains(start)) {
            return null;
        }
        if (start.equals(target)) {
            return startProduct;
        }
        visited.add(start);
        for (Edge edge : adjacencyList.get(start)) {
            Double searchResult = doDfs(adjacencyList, edge.vertex, target, visited, edge.weight * startProduct);
            if (searchResult != null) {
                return searchResult;
            }
        }
        return null;
    }

    private static class Edge {
        String vertex;
        double weight;

        Edge(String vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
