package by.dudko.education.algorithm.leetcode.study75.binarytree.dfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/description/
 * <p>
 * 112. Path Sum
 * <p>
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 * <p>
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * <p>
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There are two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 * <p>
 * Example 3:
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSumI {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return doSearch(root, 0, targetSum);
    }

    private boolean doSearch(TreeNode root, int currentSum, int targetSum) {
        if (root == null) {
            return false;
        }

        int current = root.val;
        int nextStartSum = current + currentSum;
        if (nextStartSum == targetSum && isLeaf(root)) {
            return true;
        }

        boolean left = doSearch(root.left, nextStartSum, targetSum);
        if (left) {
            return true;
        }
        return doSearch(root.right, nextStartSum, targetSum);
    }

    private static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
