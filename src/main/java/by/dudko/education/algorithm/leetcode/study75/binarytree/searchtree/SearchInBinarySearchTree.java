package by.dudko.education.algorithm.leetcode.study75.binarytree.searchtree;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/search-in-a-binary-search-tree/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 700. Search in a Binary Search Tree
 * <p>
 * You are given the root of a binary search tree (BST) and an integer val.
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
 * <p>
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 2
 * Output: [2,1,3]
 * <p>
 * Example 2:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 5000].
 * 1 <= Node.val <= 107
 * root is a binary search tree.
 * 1 <= val <= 107
 */
public class SearchInBinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode left = searchBST(root.left, val);
        if (left != null) {
            return left;
        }
        return searchBST(root.right, val);
    }
}
