package by.dudko.education.algorithm.leetcode150.linkedlists;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 82. Remove Duplicates from Sorted List II
 * <p>
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * <p>
 * Example 2:
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedListII {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode resultHead = null;
        ListNode previous = null;
        ListNode pointer = head;
        while (pointer != null) {
            if (isUnique(pointer)) {
                if (previous != null) {
                    previous.next = pointer;
                    previous = pointer;
                } else {
                    previous = pointer;
                    resultHead = pointer;
                }
                pointer = pointer.next;
            } else {
                int value = pointer.val;
                do {
                    pointer = pointer.next;
                } while (pointer != null && pointer.val == value);

                if (previous != null) {
                    previous.next = pointer;
                }
            }
        }
        return resultHead;
    }

    private static boolean isUnique(ListNode node) {
        if (node == null || node.next == null) {
            return true;
        }
        return node.val != node.next.val;
    }
}
