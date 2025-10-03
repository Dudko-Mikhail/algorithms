package by.dudko.education.algorithm.leetcode.study75.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 216. Combination Sum III
 * <p>
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * <p>
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 * <p>
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * <p>
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * <p>
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 * <p>
 * Constraints:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        process(1, 1, k, 0, n, new ArrayList<>(), combinations);
        return combinations;
    }

    private void process(int startNumber, int count, int requiredCount, int currentSum, int requiredSum,
            List<Integer> currentCombination, List<List<Integer>> combinations) {
        for (int i = startNumber; i < 10 - requiredCount + count; i++) {
            int sum = currentSum + i;
            if (sum > requiredSum) {
                return;
            }

            if (count != requiredCount) {
                currentCombination.add(i);
                process(i + 1, count + 1, requiredCount, sum, requiredSum, currentCombination,
                        combinations);
                currentCombination.remove((Integer) i);
            } else if (sum == requiredSum) {
                List<Integer> newCombination = new ArrayList<>(currentCombination);
                newCombination.add(i);
                combinations.add(newCombination);
                return;
            }
        }
    }


    public List<List<Integer>> combinationSum3ArrayVersion(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        process(1, 0, k - 1, 0, n, new Integer[k], combinations);
        return combinations;
    }

    private void process(int startNumber, int currentIndex, int stopIndex, int currentSum, int requiredSum,
            Integer[] currentCombination, List<List<Integer>> combinations) {
        for (int i = startNumber; i < 10 - stopIndex + currentIndex; i++) {
            int sum = currentSum + i;
            if (sum > requiredSum) {
                return;
            }

            currentCombination[currentIndex] = i;
            if (currentIndex != stopIndex) {
                process(i + 1, currentIndex + 1, stopIndex, sum, requiredSum, currentCombination,
                        combinations);
            } else if (sum == requiredSum) {
                combinations.add(List.of(currentCombination));
                return;
            }
        }
    }
}
