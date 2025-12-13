package by.dudko.education.algorithm.leetcode.interview150.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/course-schedule/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 207. Course Schedule
 * <p>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites
 * where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {
    public static boolean canFinishTopologicalSort(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjacencyList = IntStream.range(0, numCourses)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int[] edge : prerequisites) {
            adjacencyList[edge[0]].add(edge[1]);
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < numCourses && visited.size() != numCourses; i++) {
            if (!visited.contains(i) && hasLoopDfs(i, adjacencyList, visited, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasLoopDfs(int current, List<Integer>[] adjacencyList, Set<Integer> visited, Set<Integer> path) {
        if (path.contains(current)) {
            return true;
        }
        if (visited.contains(current)) {
            return false;
        }
        visited.add(current);
        path.add(current);
        for (int edge : adjacencyList[current]) {
            if (hasLoopDfs(edge, adjacencyList, visited, path)) {
                return true;
            }
            path.remove(edge);
        }
        return false;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Queue<Integer>[] adjacencyList = Stream.iterate(0, i -> i + 1).
                limit(numCourses)
                .map(i -> new ArrayDeque<Integer>())
                .toArray(Queue[]::new);

        LinkedHashSet<Integer> hasEdges = new LinkedHashSet<>();
        for (int[] edge : prerequisites) {
            adjacencyList[edge[0]].add(edge[1]);
            hasEdges.add(edge[0]);
        }

        while (!hasEdges.isEmpty()) {
            Integer current = hasEdges.getFirst();
            if (hasLoop(adjacencyList, hasEdges, current)) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasLoop(Queue<Integer>[] adjacencyList, Set<Integer> hasEdges, int node) {
        Queue<Integer> edges = adjacencyList[node];
        if (edges.isEmpty()) {
            hasEdges.remove(node);
            return false;
        }
        int slow = node;
        int fast = edges.element();
        while (!adjacencyList[fast].isEmpty() && fast != slow) {
            slow = adjacencyList[slow].element();
            fast = adjacencyList[fast].element();
            if (adjacencyList[fast].isEmpty()) {
                break;
            }
            fast = adjacencyList[fast].element();
        }

        if (slow == fast) {
            return true;
        }

        slow = node;
        while (!adjacencyList[slow].isEmpty()) {
            int temp = adjacencyList[slow].remove();
            if (adjacencyList[slow].isEmpty()) {
                hasEdges.remove(slow);
            }
            slow = temp;
        }
        return false;
    }
}
