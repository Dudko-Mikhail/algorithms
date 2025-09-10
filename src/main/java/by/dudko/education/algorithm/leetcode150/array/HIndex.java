package by.dudko.education.algorithm.leetcode150.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/h-index/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 274. H-Index
 * <p>
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
 * return the researcher's h-index.
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h
 * such that the given researcher has published at least h papers that have each been cited at least h times.
 * <p>
 * Example 1:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 * <p>
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 * <p>
 * Constraints:
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class HIndex {
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);

        int hIndex = 0;
        for (int i = 0; i < citations.length && hIndex < citations.length - i; i++) {
            int potentialIndex = citations[i];
            if (potentialIndex > hIndex) {
                hIndex = Math.min(citations.length - i, potentialIndex);
            }
        }
        return hIndex;
    }

    public static void main(String[] args) {
        System.out.println(hIndexThroughBinarySearch(new int[]{3, 0, 6, 1, 5}));
        System.out.println(hIndexThroughBinarySearch(new int[]{0, 0, 2}));
    }


    public static int hIndexThroughBinarySearch(int[] citations) {
        int min = citations[0];
        int max = citations[0];

        for (int value : citations) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }

        int left = Math.min(min, citations.length);
        int right = Math.min(max, citations.length);

        int hIndex = min;
        while (left <= right) {
            int candidate = (left + right) / 2;
            if (needHigherIndex(citations, candidate)) {
                hIndex = candidate;
                left = candidate + 1;
            } else {
                right = candidate - 1;
            }
        }
        return hIndex;
    }

    private static boolean needHigherIndex(int[] citations, int candidate) {
        int moreCounter = 0;
        for (int i = 0; i < citations.length; i++) {
            int citation = citations[i];
            if (citation >= candidate) {
                moreCounter++;
            }

            if (moreCounter >= candidate) {
                return true;
            }

            if (citations.length - i + moreCounter < candidate) {
                break;
            }
        }
        return false;
    }
}
