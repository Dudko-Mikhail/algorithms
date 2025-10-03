package by.dudko.education.algorithm.leetcode.study75.linkedlist;

import by.dudko.education.algorithm.leetcode.ListNode;

/**
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 2095. Delete the Middle Node of a Linked List
 * <p>
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 * <p>
 * Example 1:
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * Explanation:
 * The above figure represents the given linked list. The indices of the nodes are written below.
 * Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
 * We return the new list after removing this node.
 * <p>
 * Example 2:
 * Input: head = [1,2,3,4]
 * Output: [1,2,4]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 4, node 2 with value 3 is the middle node, which is marked in red.
 * <p>
 * Example 3:
 * Input: head = [2,1]
 * Output: [2]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 2, node 1 with value 1 is the middle node, which is marked in red.
 * Node 0 with value 2 is the only node remaining after removing node 1.
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [1, 105].
 * 1 <= Node.val <= 105
 */

public class DeleteMiddleNodeOfLinkedList {
    public ListNode deleteMiddle(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        if (count == 1) {
            return null;
        }

        if (count == 2) {
            head.next = null;
            return head;
        }

        int middle = (count / 2) - 1;
        current = head;
        for (int i = 0; i < middle; i++) {
            current = current.next;
        }
        ListNode next = current.next;
        current.next = next.next;
        return head;
    }


    public ListNode deleteMiddleTwoPointersApproach(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next.next != null && fast.next.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
