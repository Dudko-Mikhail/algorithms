package by.dudko.education.algorithm.leetcode150.linkedlists.util;

import by.dudko.education.algorithm.leetcode150.linkedlists.ListNode;

public final class ListUtils {
    private ListUtils() {
    }

    public static ListNode createList(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    public static String toString(ListNode head) {
        StringBuilder builder = new StringBuilder();
        while (head != null) {
            builder.append(head.val);
            head = head.next;
        }
        return builder.toString();
    }
}
