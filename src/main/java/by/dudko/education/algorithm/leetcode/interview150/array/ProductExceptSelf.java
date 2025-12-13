package by.dudko.education.algorithm.leetcode.interview150.array;

/**
 * https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-interview-150
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
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductExceptSelf {
    public static int[] productExceptSelfFollowUp(int[] input) {
        int[] products = new int[input.length];
        int suffix = 1;
        for (int i = input.length - 1; i >= 0; i--) {
            suffix *= input[i];
            products[i] = suffix;
        }

        int prefix = 1;
        for (int i = 0; i < input.length; i++) {
            int product = prefix;
            prefix *= input[i];
            if (i + 1 < input.length) {
                product *= products[i + 1];
            }
            products[i] = product;
        }
        return products;
    }

    public static int[] productExceptSelf(int[] input) {
        int[] prefixes = new int[input.length];
        int[] suffixes = new int[input.length];
        int prefix = 1;
        int suffix = 1;
        for (int i = 0, j = input.length - 1; i < input.length; i++, j--) {
            prefix *= input[i];
            prefixes[i] = prefix;
            suffix *= input[j];
            suffixes[j] = suffix;
        }

        int[] products = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int product = 1;
            if (i - 1 >= 0) {
                product *= prefixes[i - 1];
            }
            if (i + 1 < input.length) {
                product *= suffixes[i + 1];
            }
            products[i] = product;
        }
        return products;
    }
}
