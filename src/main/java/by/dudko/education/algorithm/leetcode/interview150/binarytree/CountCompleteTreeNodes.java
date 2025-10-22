package by.dudko.education.algorithm.leetcode.interview150.binarytree;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 222. Count Complete Tree Nodes
 * <p>
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * Design an algorithm that runs in less than O(n) time complexity.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * <p>
 * Example 2:
 * Input: root = []
 * Output: 0
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: 1
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5 * 10^4].
 * 0 <= Node.val <= 5 * 10^4
 * The tree is guaranteed to be complete.
 */
public class CountCompleteTreeNodes {
    private boolean stop;
    private int height;

    public int countNodes(TreeNode root) {
        int leafNodes = traverse(root, 0);
        int nodeCount = (int) Math.pow(2, height) - 1;
        return nodeCount + leafNodes;
    }

    private int traverse(TreeNode root, int height) {
        if (root == null || stop) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            if (height >= this.height) {
                this.height = height;
                return 1;
            } else {
                stop = true;
                return 0;
            }
        }
        int leftLeafs = traverse(root.left, height + 1);
        int rightLeafs = stop ? 0 : traverse(root.right, height + 1);
        return leftLeafs + rightLeafs;
    }

    public static int countNodesBinarySearch(TreeNode root) {
        int height = 0;
        TreeNode cur = root;
        while (cur != null) {
            cur = cur.left;
            height++;
        }

        int left = (int) Math.pow(2, height - 1);
        int right = left * 2 - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPresent(root, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static boolean isPresent(TreeNode root, int val) { // todo Нужно написать поиск за log(n). Но дерево не бинарное
        return true;
    }
}
