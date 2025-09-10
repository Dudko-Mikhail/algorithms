package by.dudko.education.algorithm.leetcode150.binarytree;

/**
 * https://leetcode.com/problems/same-tree/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 100. Same Tree
 * <p>
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * <p>
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * <p>
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * <p>
 * Constraints:
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
 */
public class SameTree {
    public static boolean isSameTree(TreeNode first, TreeNode second) {
        if (first == null || second == null) {
            return first == second;
        }

        return first.val == second.val
                && isSameTree(first.left, second.left)
                && isSameTree(first.right, second.right);
    }
}
