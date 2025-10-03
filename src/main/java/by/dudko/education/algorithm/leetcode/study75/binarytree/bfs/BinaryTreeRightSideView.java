package by.dudko.education.algorithm.leetcode.study75.binarytree.bfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 199. Binary Tree Right Side View
 * <p>
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * <p>
 * Example 2:
 * Input: root = [1,2,3,4,null,null,null,5]
 * Output: [1,3,4,5]
 * <p>
 * Example 3:
 * Input: root = [1,null,3]
 * Output: [1,3]
 * <p>
 * Example 4:
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<TreeNode> currentLayer = new ArrayList<>();
        if (root != null) {
            currentLayer.add(root);
        }
        while (!currentLayer.isEmpty()) {
            TreeNode last = currentLayer.get(currentLayer.size() - 1);
            result.add(last.val);
            List<TreeNode> nextLayer = new ArrayList<>();
            for (TreeNode current : currentLayer) {
                if (current.left != null) {
                    nextLayer.add(current.left);
                }
                if (current.right != null) {
                    nextLayer.add(current.right);
                }
            }
            currentLayer = nextLayer;
        }
        return result;
    }

    public List<Integer> rightSideViewDfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doSearch(root, 0, result);
        return result;
    }

    private void doSearch(TreeNode root, int depth, List<Integer> result) {
        if (root == null) {
            return;
        }

        if (result.size() == depth) {
            result.add(root.val);
        }

        doSearch(root.right, depth + 1, result);
        doSearch(root.left, depth + 1, result);
    }
}
