package by.dudko.education.algorithm.leetcode.interview150.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/evaluate-division/submissions/1852814712/?envType=study-plan-v2&envId=top-interview-150
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
    private static final double NOT_FOUND = -1.0;

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Edge>> adjacencyMatrix = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            String start = equation.get(0);
            String end = equation.get(1);
            adjacencyMatrix.computeIfAbsent(start, s -> new ArrayList<>())
                    .add(new Edge(end, values[i]));
            adjacencyMatrix.computeIfAbsent(end, s -> new ArrayList<>())
                    .add(new Edge(start, 1.0 / values[i]));
        }

        double[] queryAnswers = new double[queries.size()];
        int i = 0;
        for (List<String> query : queries) {
            queryAnswers[i] = findPath(adjacencyMatrix, query.get(0), query.get(1));
            i++;
        }
        return queryAnswers;
    }

    private static double findPath(Map<String, List<Edge>> adjacencyMatrix, String from, String to) {
        if (!adjacencyMatrix.containsKey(from) || !adjacencyMatrix.containsKey(to)) {
            return NOT_FOUND;
        }
        if (from.equals(to)) {
            return 1.0;
        }
        return findPathDfs(adjacencyMatrix, new HashSet<>(), from, to, 1.0);
    }

    private static double findPathDfs(Map<String, List<Edge>> adjacencyMatrix, Set<String> visited,
            String current, String to, double length) {
        if (current.equals(to)) {
            return length;
        }
        if (visited.contains(current)) {
            return NOT_FOUND;
        }
        List<Edge> edges = adjacencyMatrix.get(current);
        visited.add(current);
        for (Edge edge : edges) {
            if (visited.contains(edge.name)) {
                continue;
            }
            double result = findPathDfs(adjacencyMatrix, visited, edge.name, to, length * edge.length);
            if (result != NOT_FOUND) {
                return result;
            }
        }
        return NOT_FOUND;
    }

    private record Edge(String name, double length) {
    }
}
