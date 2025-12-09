package by.dudko.education.algorithm.leetcode.interview150.binarytree.bst;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 98. Validate Binary Search Tree
 * <p>
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys strictly less than the node's key.
 * The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 * <p>
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class ValidateBinarySearchTree {
    public static boolean isValidBST(TreeNode root) {
        return validateBst(root.left, null, root.val)
                && validateBst(root.right, root.val, null);
    }

    private static boolean validateBst(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if (min != null && node.val <= min
                || max != null && node.val >= max) {
            return false;
        }

        boolean isValid = validateBst(node.left, min, node.val);
        return isValid && validateBst(node.right, node.val, max);
    }
}
