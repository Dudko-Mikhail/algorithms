package by.dudko.education.algorithm.leetcode.linkedlist;

import by.dudko.education.algorithm.leetcode.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 92. Reverse Linked List II
 * <p>
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * <p>
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * Follow up: Could you do it in one pass?
 */
public class ReverseLinkedListII {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        int index = 1;
        ListNode current = null;
        ListNode next = head;

        while (index < left) {
            current = next;
            next = next.next;
            index++;
        }

        ListNode preStart = current;
        ListNode headOfReversedList = next;
        current = next;
        next = next.next;

        while (index < right) {
            ListNode tempNext = next.next;
            next.next = current;
            current = next;
            next = tempNext;
            index++;
        }

        if (preStart != null) {
            preStart.next = current;
        }
        headOfReversedList.next = next;
        return left != 1 ? head : current;
    }
}
