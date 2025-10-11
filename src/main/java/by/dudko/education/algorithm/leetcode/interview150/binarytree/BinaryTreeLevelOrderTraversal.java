package by.dudko.education.algorithm.leetcode.interview150.binarytree;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 102. Binary Tree Level Order Traversal
 * <p>
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * <p>
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 * <p>
 * Example 3:
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> layers = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int layerSize = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < layerSize; i++) {
                TreeNode current = queue.remove();
                layer.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            layers.add(layer);
        }
        return layers;
    }

    public static List<List<Integer>> levelOrderDfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> layers = new ArrayList<>();
        performSearch(layers, 0, root);
        return layers;
    }

    private static void performSearch(List<List<Integer>> layers, int level, TreeNode current) {
        if (current == null) {
            return;
        }
        if (layers.size() == level) {
            layers.add(new ArrayList<>());
        }
        layers.get(level).add(current.val);

        performSearch(layers, level + 1, current.left);
        performSearch(layers, level + 1, current.right);
    }

    public static List<List<Integer>> levelOrderDfs2(TreeNode root) {
        return performSearch2(new ArrayList<>(), 0, root);
    }

    private static List<List<Integer>> performSearch2(List<List<Integer>> layers, int level, TreeNode current) {
        if (current == null) {
            return layers;
        }
        if (layers.size() == level) {
            layers.add(new ArrayList<>());
        }
        layers.get(level).add(current.val);

        performSearch2(layers, level + 1, current.left);
        performSearch2(layers, level + 1, current.right);
        return layers;
    }
}
