package by.dudko.education.algorithm.leetcode;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * <p>
 * 24. Swap Nodes in Pairs
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the value in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * <p>
 * Example 2:
 * Input: head = []
 * Output: []
 * <p>
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 * <p>
 * Example 4:
 * Input: head = [1,2,3]
 * Output: [2,1,3]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */
public class SwapNodesInPairs {
    public static ListNode swapPairsRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode nextPair = next.next;
        next.next = head;
        head.next = swapPairsRecursion(nextPair);
        return next;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode resultHead = head.next;

        ListNode previous = null;
        ListNode current = head;
        while (current != null && current.next != null) {
            ListNode next = current.next;
            if (previous != null) {
                previous.next = next;
            }
            ListNode nextPairPointer = next.next;
            next.next = current;
            current.next = nextPairPointer;
            previous = current;
            current = nextPairPointer;
        }

        return resultHead;
    }

    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode resultHead = head.next;

        ListNode current = head;
        while (current != null && current.next != null) {
            ListNode next = current.next;
            ListNode nextPair = next.next;
            next.next = current;
            if (nextPair != null) {
                current.next = nextPair.next != null ? nextPair.next : nextPair;
            } else {
                current.next = null;
            }
            current = nextPair;
        }

        return resultHead;
    }
}
