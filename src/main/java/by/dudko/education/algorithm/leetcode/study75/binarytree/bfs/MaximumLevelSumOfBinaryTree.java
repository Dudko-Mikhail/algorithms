package by.dudko.education.algorithm.leetcode.study75.binarytree.bfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1161. Maximum Level Sum of a Binary Tree
 * <p>
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 * <p>
 * Example 1:
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 * <p>
 * Example 2:
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 */
public class MaximumLevelSumOfBinaryTree {
    public int maxLevelSum(TreeNode root) {
        int level = 1;
        int maxSumLevel = 1;
        int maxSum = -100001;

        List<TreeNode> currentLayer = new LinkedList<>();
        currentLayer.add(root);
        while (!currentLayer.isEmpty()) {
            int sum = 0;
            List<TreeNode> nextLayer = new LinkedList<>();
            for (TreeNode element : currentLayer) {
                if (element.left != null) {
                    nextLayer.add(element.left);
                }
                if (element.right != null) {
                    nextLayer.add(element.right);
                }
                sum += element.val;
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxSumLevel = level;
            }
            currentLayer = nextLayer;
            level++;
        }
        return maxSumLevel;
    }
}
