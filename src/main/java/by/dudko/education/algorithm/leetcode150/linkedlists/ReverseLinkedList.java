package by.dudko.education.algorithm.leetcode150.linkedlists;

/**
 * https://leetcode.com/problems/reverse-linked-list/description/
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
    public static ListNode reverseList(ListNode head) {
        ListNode current = null;
        ListNode next = head;

        while (next != null) {
            ListNode tempNext = next.next;
            next.next = current;
            current = next;
            next = tempNext;
        }
        return current;
    }
}
