package by.dudko.education.algorithm.leetcode.linkedlist;

import by.dudko.education.algorithm.leetcode.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 * <p>
 * 445. Add Two Numbers II
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * <p>
 * Example 2:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 * <p>
 * Example 3:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * <p>
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 * <p>
 * Follow up: Could you solve it without reversing the input lists?
 */
public class AddTwoNumbersII {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int s1 = findSize(l1);
        int s2 = findSize(l2);
        return s1 > s2 ? helper(l1, l2, null, s1, s2) : helper(l2, l1, null, s2, s1);
    }

    private static int findSize(ListNode node) {
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    private static ListNode helper(ListNode longest, ListNode shortest, ListNode parentPointer, int size, int shortestSize) {
        ListNode current = new ListNode();
        if (size != 1) {
            helper(longest.next, size > shortestSize ? shortest : shortest.next, current, size - 1, shortestSize);
        }

        int sum = current.val + longest.val;
        if (size <= shortestSize) {
            sum += shortest.val;
        }

        if (sum >= 10) {
            sum %= 10;
            if (parentPointer == null) {
                parentPointer = new ListNode(1);
            } else {
                parentPointer.val++;
            }
        }
        current.val = sum;
        if (parentPointer != null) {
            parentPointer.next = current;
        }

        return parentPointer != null ? parentPointer : current;
    }
}
