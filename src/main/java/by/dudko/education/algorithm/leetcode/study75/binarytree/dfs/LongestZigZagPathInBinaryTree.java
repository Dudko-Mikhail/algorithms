package by.dudko.education.algorithm.leetcode.study75.binarytree.dfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1372. Longest ZigZag Path in a Binary Tree
 * <p>
 * You are given the root of a binary tree.
 * A ZigZag path for a binary tree is defined as follow:
 * <p>
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 * Return the longest ZigZag path contained in that tree.
 * <p>
 * Example 1:
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 * <p>
 * Example 2:
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: 0
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 5 * 104].
 * 1 <= Node.val <= 100
 */
public class LongestZigZagPathInBinaryTree {
    public int longestZigZag(TreeNode root) {
        return Math.max(doSearch(root.left, false, 1), doSearch(root.right, true, 1));
    }

    private int doSearch(TreeNode root, boolean mustBeLeft, int length) {
        if (root == null) {
            return length - 1;
        }

        int oppositeSideLength;
        int currentLength = length;
        if (mustBeLeft) {
            oppositeSideLength = doSearch(root.right, true, 1);
        } else {
            oppositeSideLength = doSearch(root.left, false, 1);
        }

        if (mustBeLeft && root.left != null) {
            currentLength = doSearch(root.left, false, length + 1);
        } else if (!mustBeLeft && root.right != null) {
            currentLength = doSearch(root.right, true, length + 1);
        }
        return Math.max(oppositeSideLength, currentLength);
    }
}
