package by.dudko.education.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 * <p>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted linked list:
 * 1->1->2->3->4->4->5->6
 * <p>
 * Example 2:
 * Input: lists = []
 * Output: []
 * <p>
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 * <p>
 * Constraints:
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 */
public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        return Arrays.stream(lists)
                .filter(Objects::nonNull)
                .parallel()
                .reduce(MergeKSortedLists::mergeLists)
                .orElse(null);
    }

    private static ListNode mergeLists(ListNode first, ListNode second) {
        ListNode resultHead;
        if (first.val < second.val) {
            resultHead = first;
            first = first.next;
        } else {
            resultHead = second;
            second = second.next;
        }
        ListNode resultPointer = resultHead;

        while (first != null && second != null) {
            if (first.val < second.val) {
                resultPointer.next = first;
                first = first.next;
            } else {
                resultPointer.next = second;
                second = second.next;
            }
            resultPointer = resultPointer.next;
        }

        if (first != null) {
            resultPointer.next = first;
        }

        if (second != null) {
            resultPointer.next = second;
        }
        return resultHead;
    }


    public static ListNode mergeKLists2(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        if (queue.isEmpty()) {
            return null;
        }
        ListNode resultHead = queue.remove();
        if (resultHead.next != null) {
            queue.offer(resultHead.next);
        }

        ListNode resultPointer = resultHead;
        while (!queue.isEmpty()) {
            resultPointer.next = queue.remove();
            resultPointer = resultPointer.next;
            if (resultPointer.next != null) {
                queue.offer(resultPointer.next);
            }
        }
        return resultHead;
    }
}
