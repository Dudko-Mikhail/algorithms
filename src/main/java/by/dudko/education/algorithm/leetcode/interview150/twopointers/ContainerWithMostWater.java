package by.dudko.education.algorithm.leetcode.interview150.twopointers;

/**
 * https://leetcode.com/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 11. Container With Most Water
 * <p>
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * <p>
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * <p>
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 * <p>
 * Constraints:
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 */
public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int[] leftMax = new int[] {0, height[0]};
        int[] rightMax = new int[] {right, height[right]};
        int maxArea = calculateArea(leftMax, rightMax);
        while (left < right) {
            if (height[left] >= height[right]) {
                right--;
                if (height[right] > rightMax[1]) {
                    rightMax[0] = right;
                    rightMax[1] = height[right];
                    maxArea = Math.max(maxArea, calculateArea(leftMax, rightMax));
                }
            } else {
                left++;
                if (height[left] > leftMax[1]) {
                    leftMax[0] = left;
                    leftMax[1] = height[left];
                    maxArea = Math.max(maxArea, calculateArea(leftMax, rightMax));
                }
            }
        }
        return maxArea;
    }

    private static int calculateArea(int[] leftMax, int[] rightMax) {
        return Math.min(leftMax[1], rightMax[1]) * (rightMax[0] - leftMax[0]);
    }
}
