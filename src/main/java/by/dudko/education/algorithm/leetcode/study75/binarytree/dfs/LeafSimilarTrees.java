package by.dudko.education.algorithm.leetcode.study75.binarytree.dfs;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/leaf-similar-trees/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 872. Leaf-Similar Trees
 * <p>
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 * <p>
 * Example 1:
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * <p>
 * Example 2:
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 * <p>
 * Constraints:
 * The number of nodes in each tree will be in the range [1, 200].
 * Both of the given trees will have values in the range [0, 200].
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafs1 = extractLeafValues(root1);
        List<Integer> leafs2 = extractLeafValues(root2);
        return leafs1.equals(leafs2);
    }

    private static List<Integer> extractLeafValues(TreeNode root) {
        List<Integer> leafs = new ArrayList<>();
        processTree(root, leafs);
        return leafs;
    }

    private static void processTree(TreeNode root, List<Integer> leafs) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leafs.add(root.val);
            return;
        }
        processTree(root.left, leafs);
        processTree(root.right, leafs);
    }
}
