package by.dudko.education.algorithm.leetcode.interview150.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Gatherers;

/**
 * https://leetcode.com/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 42. Trapping Rain Water
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * <p>
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 */
public class TrappingRainWater {
    public static int trap(int[] heights) {
        Deque<Integer> queue = new ArrayDeque<>();
        int n = heights.length;
        int leftMax = heights[0];
        int leftMaxPos = 0;
        int sum = 0;

        int i = 1;
        while (i < n) {
            while (i < n && heights[i] > heights[i - 1]) { // перемотка возрастания
                i++;
            }
            int curMax = heights[i - 1];
            if (curMax > leftMax) {
                if (!queue.isEmpty()) {
                    queue.clear();
                    sum += calculateSum(heights, leftMaxPos, i - 1);
                }
                leftMax = curMax;
                leftMaxPos = i - 1;
            } else {
                while (queue.size() > 1) {
                    int previousMax = heights[queue.peek()];
                    if (curMax <= previousMax) {
                        break;
                    }
                    queue.pop();
                }
            }
            queue.push(i - 1);

            while (i < n && heights[i] <= heights[i - 1]) { // перемотка убывания
                i++;
            }
        }

        if (queue.size() > 1) {
            return queue.stream()
                    .gather(Gatherers.windowSliding(2))
                    .map(list -> calculateSum(heights, list.get(1), list.get(0)))
                    .reduce(sum, Integer::sum);
        }
        return sum;
    }

    private static int calculateSum(int[] heights, int left, int right) {
        int height = Math.min(heights[left], heights[right]);
        int sum = 0;
        for (int i = left + 1; i < right; i++) {
            if (heights[i] >= height) {
                continue;
            }
            sum += height - heights[i];
        }
        return sum;
    }
}
