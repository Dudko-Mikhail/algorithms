package by.dudko.education.algorithm.leetcode.linkedlist;

import by.dudko.education.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/rotate-list/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 61. Rotate List
 * <p>
 * Given the head of a linked list, rotate the list to the right by k places.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * <p>
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */
public class RotateList {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        List<ListNode> elements = new ArrayList<>();
        do {
            elements.add(cur);
            cur = cur.next;
        } while (cur != null);

        int size = elements.size();
        int shift = k % size;
        ListNode[] result = new ListNode[size];
        for (int i = 0; i < size; i++) {
            result[(i + shift) % size] = elements.get(i);
        }
        for (int i = 0; i < size - 1; i++) {
            result[i].next = result[i + 1];
        }
        result[size - 1].next = null;

        return result[0];
    }

    public static ListNode rotateRightOptimized(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int size = 1;
        ListNode cur = head;
        while (cur.next != null) {
            size++;
            cur = cur.next;
        }

        int shift = k % size;
        if (shift == 0) {
            return head;
        }

        ListNode tail = cur;
        cur = head;
        int newHeadIndex = size - shift - 1;
        for (int i = 0; i < newHeadIndex; i++) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        tail.next = head;

        return newHead;
    }
}
