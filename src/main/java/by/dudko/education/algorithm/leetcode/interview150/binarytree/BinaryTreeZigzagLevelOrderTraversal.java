package by.dudko.education.algorithm.leetcode.interview150.binarytree;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 103. Binary Tree Zigzag Level Order Traversal
 * <p>
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
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
 * -100 <= Node.val <= 100
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigZagTraversal = new ArrayList<>();
        if (root == null) {
            return zigZagTraversal;
        }

        Queue<TreeNode> toVisit = new ArrayDeque<>();
        toVisit.offer(root);
        boolean isDirect = true;

        while (!toVisit.isEmpty()) {
            int layerSize = toVisit.size();
            List<Integer> layer = new LinkedList<>();
            for (int i = 0; i < layerSize; i++) {
                TreeNode current = toVisit.remove();
                if (current.left != null) {
                    toVisit.offer(current.left);
                }
                if (current.right != null) {
                    toVisit.offer(current.right);
                }

                if (isDirect) {
                    layer.add(current.val);
                } else {
                    layer.addFirst(current.val);
                }
            }
            isDirect = !isDirect;
            zigZagTraversal.add(layer);
        }
        return zigZagTraversal;
    }

    public static List<List<Integer>> zigzagLevelOrderWithRecursion(TreeNode root) {
        List<List<Integer>> zigZagTraversal = new ArrayList<>();
        if (root == null) {
            return zigZagTraversal;
        }

        performTraversal(zigZagTraversal, 0, root);
        return zigZagTraversal;
    }

    private static void performTraversal(List<List<Integer>> result, int level, TreeNode current) {
        if (current == null) {
            return;
        }

        if (result.size() == level) {
            result.add(new LinkedList<>());
        }

        if (level % 2 == 0) {
            result.get(level).add(current.val);
        } else {
            result.get(level).addFirst(current.val);
        }
        performTraversal(result, level + 1, current.left);
        performTraversal(result, level + 1, current.right);
    }
}
