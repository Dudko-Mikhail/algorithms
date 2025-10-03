package by.dudko.education.algorithm.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 138. Copy List with Random Pointer
 * <p>
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent
 * the same list state. None of the pointers in the new list should point to nodes in the original list.
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * <p>
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * <p>
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * <p>
 * Constraints:
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 */
public class CopyListWithRandomPointer {
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node newHead = new Node(head.val);
        Node pointer = head;
        Node newListPointer = newHead;
        Map<Node, Node> nodeToNewNode = new HashMap<>();
        nodeToNewNode.put(head, newHead);
        while (pointer.next != null) {
            pointer = pointer.next;
            newListPointer.next = new Node(pointer.val);
            newListPointer = newListPointer.next;
            nodeToNewNode.put(pointer, newListPointer);
        }
        pointer = head;
        newListPointer = newHead;
        while (pointer != null) {
            Node random = pointer.random;
            if (random != null) {
                newListPointer.random = nodeToNewNode.get(random);
            }
            pointer = pointer.next;
            newListPointer = newListPointer.next;
        }
        return newHead;
    }

    public static Node copyRandomListOnePassSolution(Node head) {
        if (head == null) {
            return null;
        }

        Node newHead = new Node(head.val);
        Node pointer = head;
        Node newListPointer = newHead;
        Map<Node, Node> oldToNew = new HashMap<>();
        oldToNew.put(head, newHead);
        processNode(oldToNew, head, newHead);

        while (pointer.next != null) {
            pointer = pointer.next;
            Node newNode = oldToNew.get(pointer);
            if (newNode == null) {
                newListPointer.next = new Node(pointer.val);
            } else {
                newListPointer.next = newNode;
            }

            newListPointer = newListPointer.next;
            oldToNew.put(pointer, newListPointer);
            processNode(oldToNew, pointer, newListPointer);
        }
        return newHead;
    }

    private static void processNode(Map<Node, Node> oldToNew, Node oldNode, Node newNode) {
        Node random = oldNode.random;
        if (random != null) {
            Node newNodeForRandomConnection = oldToNew.get(random);
            if (newNodeForRandomConnection == null) {
                Node node = new Node(random.val);
                oldToNew.put(random, node);
                newNodeForRandomConnection = node;
            }
            newNode.random = newNodeForRandomConnection;
        }
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
