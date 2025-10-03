package by.dudko.education.algorithm.leetcode.study75.arraystring;

/**
 * https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 238. Product of Array Except Self
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * Constraints:
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductOfArrayExceptItself {
    public static int[] productExceptSelf(int[] input) {
        int zeroCount = 0;
        int zeroPosition = 0;
        long globalProduct = 1;
        for (int i = 0; i < input.length; i++) {
            int current = input[i];
            if (current == 0) {
                zeroCount++;
                zeroPosition = i;
            }
            globalProduct *= current;
        }

        int[] products = new int[input.length];
        if (zeroCount == 1) {
            int product = 1;
            for (int i = 0; i < input.length; i++) {
                if (i != zeroPosition) {
                    product *= input[i];
                }
            }
            products[zeroPosition] = product;
            return products;
        } else if (zeroCount == 0) {
            for (int i = 0; i < input.length; i++) {
                products[i] = (int) (globalProduct / input[i]);
            }
        }
        return products;
    }
}
