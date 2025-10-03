package by.dudko.education.algorithm.leetcode.study75.binarytree.dfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/description/
 * <p>
 * 113. Path Sum II
 * <p>
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 * <p>
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * <p>
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * <p>
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> output = new ArrayList<>();
        doSearch(root, targetSum, 0, new ArrayDeque<>(), output);
        return output;
    }

    private void doSearch(TreeNode root, int targetSum, int currentSum, Deque<Integer> path, List<List<Integer>> output) {
        if (root == null) {
            return;
        }

        int nextSum = root.val + currentSum;
        path.addLast(root.val);
        if (nextSum == targetSum && isLeaf(root)) {
            output.add(new ArrayList<>(path));
        }

        doSearch(root.left, targetSum, nextSum, path, output);
        doSearch(root.right, targetSum, nextSum, path, output);
        path.removeLast();
    }

    private static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
