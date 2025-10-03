package by.dudko.education.algorithm.util;

import by.dudko.education.algorithm.leetcode.ListNode;

import java.util.StringJoiner;

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

    public static void print(ListNode head) {
        print(head, ",", "[", "]");
    }

    public static void print(ListNode head, String separator, String prefix, String suffix) {
        StringJoiner joiner = new StringJoiner(separator, prefix, suffix);
        ListNode current = head;
        while (current != null) {
            joiner.add(Integer.toString(current.val));
            current = current.next;
        }
        System.out.println(joiner);
    }
}
