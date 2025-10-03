package by.dudko.education.algorithm.leetcode.interview150.binarytree;

import by.dudko.education.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 173. Binary Search Tree Iterator
 * <p>
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 * The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * <p>
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 * <p>
 * Example 1:
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * <p>
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^5].
 * 0 <= Node.val <= 10^6
 * At most 10^5 calls will be made to hasNext, and next.
 * <p>
 * Follow up:
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 */
public class BinarySearchTreeIterator {
    static class BSTIterator {
        private int i = 0;
        private final int size;
        private final List<TreeNode> inOrderTraversal = new ArrayList<>();

        public BSTIterator(TreeNode root) {
            traverseTree(root);
            size = inOrderTraversal.size();
        }

        public int next() {
            return inOrderTraversal.get(i++).val;
        }

        public boolean hasNext() {
            return i < size;
        }

        private void traverseTree(TreeNode root) {
            if (root == null) {
                return;
            }
            traverseTree(root.left);
            inOrderTraversal.add(root);
            traverseTree(root.right);
        }
    }

    static class BSTIteratorWithStack {
        private final Deque<TreeNode> stack = new ArrayDeque<>();

        public BSTIteratorWithStack(TreeNode root) {
            traverseLeft(root);
        }

        public int next() {
            TreeNode cur = stack.pop();
            traverseLeft(cur.right);
            return cur.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void traverseLeft(TreeNode root) {
            if (root == null) {
                return;
            }
            stack.push(root);
            traverseLeft(root.left);
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
