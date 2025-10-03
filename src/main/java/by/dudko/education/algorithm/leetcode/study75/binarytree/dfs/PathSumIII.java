package by.dudko.education.algorithm.leetcode.study75.binarytree.dfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/path-sum-iii/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 437. Path Sum III
 * <p>
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * <p>
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        return doSearch(root, targetSum, 0);
    }

    private int doSearch(TreeNode root, int targetSum, long currentSum) {
        if (root == null) {
            return 0;
        }

        long nextSum = currentSum + root.val;
        int count = nextSum == targetSum ? 1 : 0;
        count += doSimpleSearch(root.left, targetSum, 0);
        count += doSimpleSearch(root.right, targetSum, 0);
        count += doSearch(root.left, targetSum, nextSum);
        count += doSearch(root.right, targetSum, nextSum);
        return count;
    }

    private int doSimpleSearch(TreeNode root, int targetSum, long currentSum) {
        if (root == null) {
            return 0;
        }

        long nextSum = currentSum + root.val;
        int count = nextSum == targetSum ? 1 : 0;
        count += doSimpleSearch(root.left, targetSum, nextSum);
        count += doSimpleSearch(root.right, targetSum, nextSum);
        return count;
    }
}
