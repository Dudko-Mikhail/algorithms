package by.dudko.education.algorithm.leetcode.study75.linkedlist;

import by.dudko.education.algorithm.leetcode.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 206. Reverse Linked List
 * <p>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * <p>
 * Example 3:
 * Input: head = []
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

public class ReverseLinkedList {
    public ListNode reverseListIterative(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = previous;

            previous = current;
            current = next;
        }

        return previous;
    }

    public ListNode reverseListRecursion(ListNode head) {
        return doReverse(head, head);
    }

    private ListNode doReverse(ListNode current, ListNode head) {
        if (current == null) {
            return null;
        }
        ListNode next = doReverse(current.next, head);
        if (next == null) {
            head.next = current;
            current.next = null;
            return current;
        }
        if (current == head) {
            ListNode newHead = current.next;
            next.next = current;
            current.next = null;
            return newHead;
        }
        next.next = current;
        return current;
    }


    public ListNode reverseListRecursive(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = head;
        if (head.next != null) {
            newHead = reverseListRecursive(head.next);
            head.next.next = head;
        }
        head.next = null;

        return newHead;
    }

}
