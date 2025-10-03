package by.dudko.education.algorithm.leetcode.study75.heappriorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-performance-of-a-team/description/
 * <p>
 * 1383. Maximum Performance of a Team
 * <p>
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 * The performance of a team is the sum of its engineers' speeds multiplied by the minimum efficiency among its engineers.
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 * <p>
 * Example 1:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * <p>
 * Example 2:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * <p>
 * Example 3:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 * <p>
 * Constraints:
 * 1 <= k <= n <= 105
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 105
 * 1 <= efficiency[i] <= 108
 */
public class MaximumPerformanceOfTeam {
    private static final int MODULO = 1_000_000_007;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Employee[] employees = new Employee[n];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(speed[i], efficiency[i]);
        }
        Arrays.sort(employees);

        int minEfficiency = employees[0].efficiency;
        long speedSum = employees[0].speed;
        long maxScore = speedSum * minEfficiency;
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(employees[0].speed);

        for (int i = 1; i < k; i++) {
            int currentSpeed = employees[i].speed;
            int currentEfficiency = employees[i].efficiency;
            if (currentEfficiency != minEfficiency) {
                maxScore = Math.max(speedSum * minEfficiency, maxScore);
                minEfficiency = currentEfficiency;
            }

            speedSum += currentSpeed;
            queue.offer(currentSpeed);
        }
        maxScore = Math.max(speedSum * minEfficiency, maxScore);

        for (int i = k; i < n; i++) {
            int slowestEmployee = queue.element();
            int currentSpeed = employees[i].speed;

            int currentEfficiency = employees[i].efficiency;
            if (currentEfficiency != minEfficiency) {
                maxScore = Math.max(speedSum * minEfficiency, maxScore);
                minEfficiency = currentEfficiency;
            }

            if (slowestEmployee < currentSpeed) {
                queue.remove();
                queue.add(currentSpeed);
                speedSum = speedSum - slowestEmployee + currentSpeed;
                maxScore = Math.max(speedSum * minEfficiency, maxScore);
            }

        }
        return (int) (maxScore % MODULO);
    }


    public int maxPerformanceWithSingleLoop(int n, int[] speed, int[] efficiency, int k) {
        Employee[] employees = new Employee[n];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(speed[i], efficiency[i]);
        }
        Arrays.sort(employees);

        long speedSum = 0;
        long maxScore = 0;
        int minEfficiency = employees[0].efficiency;
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int newEfficiency = employees[i].efficiency;
            int currentSpeed = employees[i].speed;

            if (newEfficiency != minEfficiency) {
                maxScore = Math.max(maxScore, speedSum * minEfficiency);
                minEfficiency = newEfficiency;
            }

            if (queue.size() == k) {
                int minSpeed = queue.element();
                if (currentSpeed > minSpeed) {
                    queue.remove();
                    queue.offer(currentSpeed);
                    speedSum = speedSum + currentSpeed - minSpeed;
                    maxScore = Math.max(maxScore, speedSum * minEfficiency);
                }
            } else {
                queue.offer(currentSpeed);
                speedSum += currentSpeed;
            }
        }

        maxScore = Math.max(maxScore, speedSum * minEfficiency);
        return (int) (maxScore % MODULO);
    }

    private static class Employee implements Comparable<Employee> {
        int speed;
        int efficiency;

        public Employee(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }

        @Override
        public int compareTo(Employee o) {
            return o.efficiency - efficiency;
        }
    }
}


class MaximumPerformanceOfTeamV2 {
    private static final int MODULO = 1_000_000_000 + 7;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] employees = new int[n][2];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new int[]{speed[i], efficiency[i]};
        }
        Arrays.sort(employees, (f, s) -> s[1] - f[1]);

        int minEfficiency = employees[0][1];
        long speedSum = employees[0][0];
        long maxScore = speedSum * minEfficiency;
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(employees[0][0]);

        for (int i = 1; i < k; i++) {
            int currentSpeed = employees[i][0];
            int currentEfficiency = employees[i][1];
            if (currentEfficiency != minEfficiency) {
                maxScore = Math.max(speedSum * minEfficiency, maxScore);
                minEfficiency = currentEfficiency;
            }

            speedSum += currentSpeed;
            queue.offer(currentSpeed);
        }
        maxScore = Math.max(speedSum * minEfficiency, maxScore);

        for (int i = k; i < n; i++) {
            int slowestEmployee = queue.element();
            int currentSpeed = employees[i][0];

            int currentEfficiency = employees[i][1];
            if (currentEfficiency != minEfficiency) {
                maxScore = Math.max(speedSum * minEfficiency, maxScore);
                minEfficiency = currentEfficiency;
            }

            if (slowestEmployee < currentSpeed) {
                queue.remove();
                queue.add(currentSpeed);
                speedSum = speedSum - slowestEmployee + currentSpeed;
                maxScore = Math.max(speedSum * minEfficiency, maxScore);
            }

        }
        return (int) (maxScore % MODULO);
    }
}