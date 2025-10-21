package by.dudko.education.algorithm.leetcode.interview150.binarytree;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 637. Average of Levels in Binary Tree
 * <p>
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * <p>
 * Example 2:
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class AverageOfLevelsInBinaryTree {
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> layerAverage = new ArrayList<>();
        Queue<TreeNode> toVisit = new ArrayDeque<>();
        toVisit.offer(root);
        while (!toVisit.isEmpty()) {
            int layerSize = toVisit.size();
            long sum = 0;

            for (int i = 0; i < layerSize; i++) {
                TreeNode current = toVisit.remove();
                sum += current.val;
                if (current.left != null) {
                    toVisit.offer(current.left);
                }
                if (current.right != null) {
                    toVisit.offer(current.right);
                }
            }
            layerAverage.add((double) sum / layerSize);
        }
        return layerAverage;
    }
}
