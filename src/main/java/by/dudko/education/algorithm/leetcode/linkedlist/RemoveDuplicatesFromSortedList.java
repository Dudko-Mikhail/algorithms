package by.dudko.education.algorithm.leetcode.linkedlist;

import by.dudko.education.algorithm.leetcode.ListNode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 * <p>
 * 83. Remove Duplicates from Sorted List
 * <p>
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 * <p>
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 * <p>
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pointer = head.next;
        ListNode curValueStart = head;
        while (pointer != null) {
            if (pointer.val == curValueStart.val) {
                do {
                    pointer = pointer.next;
                } while (pointer != null && pointer.val == curValueStart.val);
                curValueStart.next = pointer;
            } else {
                curValueStart = pointer;
                pointer = pointer.next;
            }
        }

        return head;
    }
}
