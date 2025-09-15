package by.dudko.education.algorithm.leetcode150.binarytree;

/**
 * https://leetcode.com/problems/symmetric-tree/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 101. Symmetric Tree
 * <p>
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * <p>
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * <p>
 * Example 2:
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 * <p>
 * Follow up: Could you solve it both recursively and iteratively?
 */
public class SymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        return checkSymmetry(root, root);
    }

    private static boolean checkSymmetry(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }

        return left.val == right.val
                && checkSymmetry(left.left, right.right)
                && checkSymmetry(left.right, right.left);
    }
}
