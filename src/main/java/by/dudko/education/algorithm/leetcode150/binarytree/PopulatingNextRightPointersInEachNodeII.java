package by.dudko.education.algorithm.leetcode150.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 117. Populating Next Right Pointers in Each Node II
 * <p>
 * Given a binary tree
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node,
 * just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 * <p>
 * Follow-up:
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersInEachNodeII {
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int end = queue.size() - 1;
            Node current = queue.remove();
            processNode(current, queue);
            for (int i = 0; i < end; i++) {
                Node next = queue.remove();
                processNode(next, queue);
                current.next = next;
                current = next;
            }
        }

        return root;
    }

    private static void processNode(Node node, Queue<Node> queue) {
        if (node.left != null) {
            queue.offer(node.left);
        }
        if (node.right != null) {
            queue.offer(node.right);
        }
    }

    public static Node connectWithoutUsingQueue(Node root) {
        Node head = root;
        Node nextLayerStart = null;
        Node linkPointer = null;

        while (head != null) {
            if (head.left != null) {
                linkPointer = processNode(head.left, linkPointer);
                if (nextLayerStart == null) {
                    nextLayerStart = linkPointer;
                }
            }
            if (head.right != null) {
                linkPointer = processNode(head.right, linkPointer);
                if (nextLayerStart == null) {
                    nextLayerStart = linkPointer;
                }
            }
            head = head.next;
            if (head == null) { // jump to next layer
                head = nextLayerStart;
                nextLayerStart = null;
                linkPointer = null;
            }
        }
        return root;
    }

    private static Node processNode(Node current, Node linkPointer) {
        if (linkPointer == null) {
            return current;
        }

        linkPointer.next = current;
        return linkPointer.next;
    }
}

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
