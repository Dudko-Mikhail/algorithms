package by.dudko.education.algorithm.leetcode.study75.dynamicprogramming.onedimension;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 746. Min Cost Climbing Stairs
 * <p>
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor.
 * <p>
 * Example 1:
 * Input: cost = [10,15,20]       [0 10 10 0] co = 0 cp = 10   0 < 2
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * <p>
 * Example 2:
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 * <p>
 * Constraints:
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */


public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] optimalCosts = new int[cost.length + 1];
        optimalCosts[0] = 0;
        optimalCosts[1] = 0;
        optimalCosts[2] = Math.min(cost[0], cost[1]);

        for (int i = 1; i < cost.length; i++) {
            optimalCosts[i + 1] = Math.min(optimalCosts[i] + cost[i], optimalCosts[i + 1]);
            if (i < cost.length - 1) {
                optimalCosts[i + 2] = optimalCosts[i] + cost[i];
            }
        }
        return optimalCosts[cost.length];
    }
}
