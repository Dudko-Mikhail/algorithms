package by.dudko.education.algorithm.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/?envType=problem-list-v2&envId=array
 */
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{4, 4, -8, 4, 4, -8, 2, 3, -5, 2, 3, -5}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int current = nums[i];
            if (i != 0 && current == nums[i - 1]) {
                continue;
            }
            twoSum(i, nums, -current, result);
        }
        return result;
    }

    private void twoSum(int fixed, int[] nums, int targetSum, List<List<Integer>> answer) {
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

    public List<List<Integer>> threeSumBrute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int second = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int third = nums[k];
                    if (first + second + third == 0) {
                        result.add(List.of(first, second, third));
                    }
                }
            }
        }
        return result.stream()
                .map(list -> {
                    List<Integer> mutable = new ArrayList<>(list);
                    mutable.sort(Integer::compareTo);
                    return mutable;
                })
                .distinct()
                .collect(Collectors.toList());
    }
}
