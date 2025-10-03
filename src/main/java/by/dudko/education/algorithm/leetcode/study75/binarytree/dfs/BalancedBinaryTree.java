package by.dudko.education.algorithm.leetcode.study75.binarytree.dfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/description/
 * <p>
 * 110. Balanced Binary Tree
 * <p>
 * Given a binary tree, determine if it is
 * height-balanced
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * <p>
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * <p>
 * Example 3:
 * Input: root = []
 * Output: true
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean[] balanced = new boolean[1];
        balanced[0] = true;
        findHeight(root, 0, balanced);
        return balanced[0];
    }

    private static int findHeight(TreeNode current, int depth, boolean[] balanced) {
        if (current == null || depth == -1) {
            return depth;
        }
        int left = findHeight(current.left, depth + 1, balanced);
        int right = findHeight(current.right, depth + 1, balanced);
        if (Math.abs(left - right) > 1) {
            balanced[0] = false;
            return -1;
        }
        return Math.max(left, right);
    }


    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return findHeight(root, 0) != -1;
    }

    private static int findHeight(TreeNode current, int depth) {
        if (current == null || depth == -1) {
            return depth;
        }
        int left = findHeight(current.left, depth + 1);
        if (left == -1) {
            return -1;
        }
        int right = findHeight(current.right, depth + 1);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right);
    }
}
