package by.dudko.education.algorithm.leetcode.study75.binarytree.searchtree;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 450. Delete Node in a BST
 * <p>
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 * <p>
 * Example 1:
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 * <p>
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * <p>
 * Example 3:
 * Input: root = [], key = 0
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -105 <= Node.val <= 105
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -105 <= key <= 105
 * <p>
 * Follow up: Could you solve it with time complexity O(height of tree)?
 */
public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            return deleteCurrentNode(root);
        }
        TreeNode left = performDelete(root.left, root, key);
        if (left != null) {
            return root;
        }
        performDelete(root.right, root, key);
        return root;
    }

    private TreeNode performDelete(TreeNode root, TreeNode previous, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            boolean isRight = root.val > previous.val;
            TreeNode newRoot = deleteCurrentNode(root);
            if (isRight) {
                previous.right = newRoot;
            } else {
                previous.left = newRoot;
            }
            return previous;
        }

        TreeNode left = performDelete(root.left, root, key);
        if (left != null) {
            return root;
        }
        TreeNode right = performDelete(root.right, root, key);
        if (right != null) {
            return root;
        }
        return null;
    }


    private TreeNode deleteCurrentNode(TreeNode current) {
        if (current.left == null) {
            return current.right;
        }
        if (current.right == null) {
            return current.left;
        }
        TreeNode newRoot = current.left;
        TreeNode oldRight = newRoot.right;
        newRoot.right = current.right;
        safeAddNode(oldRight, current.right);
        return newRoot;
    }


    private TreeNode safeAddNode(TreeNode toAdd, TreeNode target) {
        if (toAdd == null) {
            return null;
        }
        return addNodeInternal(toAdd, target);
    }


    private TreeNode addNodeInternal(TreeNode toAdd, TreeNode target) {
        if (target == null) {
            return null;
        }

        int newValue = toAdd.val;
        int currentValue = target.val;

        TreeNode result;
        if (newValue > currentValue) {
            result = addNodeInternal(toAdd, target.right);
            if (result == null) {
                target.right = toAdd;
                return toAdd;
            }
        } else {
            result = addNodeInternal(toAdd, target.left);
            if (result == null) {
                target.left = toAdd;
                return toAdd;
            }
        }
        return result;
    }
}
