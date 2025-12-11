package by.dudko.education.algorithm.leetcode.interview150.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 210. Course Schedule II
 * <p>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * <p>
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * <p>
 * Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 * <p>
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> reverseAdjacencyList = new HashMap<>();
        Map<Integer, Integer> edgeCount = new HashMap<>();
        for (int[] edge : prerequisites) {
            edgeCount.merge(edge[0], 1, Integer::sum);
            reverseAdjacencyList.computeIfAbsent(edge[1], k -> new ArrayList<>())
                    .add(edge[0]);
        }
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (!edgeCount.containsKey(i)) {
                toVisit.offer(i);
            }
        }

        int[] takeOrder = new int[numCourses];
        int i = 0;
        while (!toVisit.isEmpty()) {
            int current = toVisit.remove();
            takeOrder[i++] = current;
            List<Integer> edges = reverseAdjacencyList.remove(current);
            if (edges == null) {
                continue;
            }
            for (int edge : edges) {
                edgeCount.computeIfPresent(edge, (key, value) -> {
                    if (value == 1) {
                        toVisit.offer(edge);
                        return null;
                    } else {
                        return value - 1;
                    }
                });
            }
        }

        return edgeCount.isEmpty() ? takeOrder : new int[0];
    }


    public static int[] findOrder2(int numCourses, int[][] prerequisites) {
        boolean[][] adjacencyMatrix = new boolean[numCourses][numCourses];
        Map<Integer, Integer> edgeCount = new HashMap<>();
        for (int[] edge : prerequisites) {
            edgeCount.merge(edge[0], 1, Integer::sum);
            adjacencyMatrix[edge[0]][edge[1]] = true;
        }
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (!edgeCount.containsKey(i)) {
                toVisit.offer(i);
            }
        }

        int[] takeOrder = new int[numCourses];
        int i = 0;
        while (!toVisit.isEmpty()) {
            int current = toVisit.poll();
            takeOrder[i++] = current;
            for (int j = 0; j < numCourses; j++) {
                if (adjacencyMatrix[j][current]) {
                    adjacencyMatrix[j][current] = false;
                    edgeCount.computeIfPresent(j, (key, value) -> {
                        if (value == 1) {
                            toVisit.add(key);
                            return null;
                        }
                        return value - 1;
                    });
                }
            }
        }

        return edgeCount.isEmpty() ? takeOrder : new int[0];
    }
}
