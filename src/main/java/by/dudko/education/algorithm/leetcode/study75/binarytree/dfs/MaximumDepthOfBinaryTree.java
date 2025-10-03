package by.dudko.education.algorithm.leetcode.study75.binarytree.dfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 104. Maximum Depth of Binary Tree
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * <p>
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = 0;
        List<TreeNode> toVisit = new ArrayList<>();
        toVisit.add(root);
        while (!toVisit.isEmpty()) {
            height++;
            List<TreeNode> nextLayer = new ArrayList<>();
            for (TreeNode node : toVisit) {
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            toVisit = nextLayer;
        }
        return height;
    }


    public int maxDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return findHeight(root, 0);
    }

    private static int findHeight(TreeNode current, int depth) {
        if (current == null) {
            return depth;
        }
        int left = findHeight(current.left, depth + 1);
        int right = findHeight(current.right, depth + 1);
        return Math.max(left, right);
    }
}
