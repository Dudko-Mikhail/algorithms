package by.dudko.education.algorithm.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18 4 sum
 * <p>
 * https://leetcode.com/problems/4sum/?envType=problem-list-v2&envId=array
 */
public class FourSum {
    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        System.out.println(fourSum.fourSum(new int[]{12, 12, 12, 12}, 49));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            int current = nums[i];
            if (i != 0 && current == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> threeSum = threeSum(i + 1, nums, (long) target - current);
            threeSum.forEach(list -> {
                List<Integer> arr = new ArrayList<>();
                arr.add(current);
                arr.addAll(list);
                result.add(arr);
            });
        }
        return result;
    }


    public List<List<Integer>> threeSum(int start, int[] nums, long targetSum) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = start; i < nums.length - 2; i++) {
            int current = nums[i];
            if (i != start && current == nums[i - 1]) {
                continue;
            }
            twoSum(i, nums, targetSum - current, result);
        }
        return result;
    }

    private void twoSum(int fixed, int[] nums, long targetSum, List<List<Integer>> answer) {
        int fixedNumber = nums[fixed];

        int right = nums.length - 1;
        int left = fixed + 1;
        while (left < right) {
            int leftNumber = nums[left];
            int rightNumber = nums[right];
            int sum = leftNumber + rightNumber;
            if (sum == targetSum) {
                answer.add(List.of(fixedNumber, leftNumber, rightNumber));
                while (left < nums.length && leftNumber == nums[left]) {
                    left++;
                }
            } else if (sum > targetSum) {
                right--;
            } else {
                left++;
            }
        }
    }
}
