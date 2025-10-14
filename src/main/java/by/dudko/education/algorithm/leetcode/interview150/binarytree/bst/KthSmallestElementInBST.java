package by.dudko.education.algorithm.leetcode.interview150.binarytree.bst;

import by.dudko.education.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 230. Kth Smallest Element in a BST
 * <p>
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 * <p>
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * <p>
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * <p>
 * Constraints:
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 * <p>
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 */
public class KthSmallestElementInBST {
    public static int kthSmallest(TreeNode root, int k) {
        int[] counter = new int[1];
        counter[0] = -1;
        return doSearch(counter, root, k);
    }

    private static int doSearch(int[] counter, TreeNode current, int k) {
        if (current == null) {
            if (counter[0] == -1) {
                counter[0] = 0;
            }
            return -1;
        }

        int left = doSearch(counter, current.left, k);
        if (left != -1) {
            return left;
        }

        if (counter[0] != -1) {
            counter[0]++;
            if (counter[0] == k) {
                return current.val;
            }
        }
        return doSearch(counter, current.right, k);
    }
}
