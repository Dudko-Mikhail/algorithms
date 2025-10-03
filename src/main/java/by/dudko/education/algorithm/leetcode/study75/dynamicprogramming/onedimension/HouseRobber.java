package by.dudko.education.algorithm.leetcode.study75.dynamicprogramming.onedimension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/house-robber/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 198. House Robber
 * <p>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        boolean previousTaken;
        int maxSum;
        if (nums[1] > nums[0]) {
            previousTaken = true;
            maxSum = nums[1];
        } else {
            previousTaken = false;
            maxSum = nums[0];
        }
        LinkedList<Integer> bestResults = new LinkedList<>();
        bestResults.add(nums[0]);
        bestResults.add(maxSum);


        for (int i = 2; i < nums.length; i++) {
            if (previousTaken) {
                int alternativeSum = nums[i] + bestResults.get(0);
                if (alternativeSum > maxSum) {
                    maxSum = alternativeSum;
                } else {
                    previousTaken = false;
                }
            } else {
                previousTaken = true;
                maxSum += nums[i];
            }
            bestResults.removeFirst();
            bestResults.add(maxSum);
        }

        return maxSum;
    }


    public int rob2(int[] nums) {
        int[] values = new int[nums.length];
        Arrays.fill(values, -1);
        return rec(nums, nums.length - 1, values);
    }

    public int rec(int[] nums, int i, int[] values) {
        if (i == 0) {
            return nums[0];
        }
        if (i < 0) {
            return 0;
        }
        if (values[i] != -1) {
            return values[i];
        }
        int take = nums[i] + rec(nums, i - 2, values);
        int not_take = rec(nums, i - 1, values);
        if (values[i] == -1) {
            values[i] = Math.max(take, not_take);
        }

        return Math.max(take, not_take);
    }


    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
//        for (int i = 0; i < 1000; i++) {
//            int[] input = generateInput();
//            int first = robber.rob(input);
//            int second = robber.rob2(input);
//            if (first != second) {
//                throw new IllegalStateException("""
//                        Invalid algorithm!!!
//                        Input: %s
//                        First: %s
//                        Second: %s
//                        """.formatted(Arrays.toString(input), first, second));
//            }
//            System.out.println("Input: " + Arrays.toString(input));
//            System.out.println("Result = " + robber.rob(input));
//        }
        int[] ints = generateInput(10000, 10001);
        long start = System.nanoTime();
        System.out.println(robber.rob2(ints));
        System.out.println("DurationRec: " + (System.nanoTime() - start));
        start = System.nanoTime();
        System.out.println(robber.rob(ints));
        System.out.println("DurationMy: " + (System.nanoTime() - start));

    }

    static Random random = new Random();

    static int[] generateInput(int minSize, int maxSize) {
        return IntStream.iterate(random.nextInt(400), i -> random.nextInt(400))
                .limit(random.nextInt(maxSize - minSize) + minSize)
                .toArray();
    }
}
