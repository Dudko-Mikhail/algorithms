package by.dudko.education.algorithm.leetcode150.linkedlists;

/**
 * https://leetcode.com/problems/partition-list/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 86. Partition List
 * <p>
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Example 1:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * <p>
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode lessHead = null;
        ListNode moreHead = null;
        if (head.val >= x) {
            moreHead = head;
        } else {
            lessHead = head;
        }

        ListNode lessCur = lessHead;
        ListNode moreCur = moreHead;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val >= x) {
                if (moreCur != null) {
                    moreCur.next = cur;
                    moreCur = cur;
                } else {
                    moreCur = cur;
                    moreHead = cur;
                }
            } else {
                if (lessCur != null) {
                    lessCur.next = cur;
                    lessCur = cur;
                } else {
                    lessCur = cur;
                    lessHead = cur;
                }
            }
            cur = cur.next;
        }

        if (lessHead == null || moreHead == null) {
            return head;
        }
        moreCur.next = null;
        lessCur.next = moreHead;
        return lessHead;
    }
}
