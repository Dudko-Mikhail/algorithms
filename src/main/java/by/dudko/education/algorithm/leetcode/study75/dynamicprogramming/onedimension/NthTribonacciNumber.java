package by.dudko.education.algorithm.leetcode.study75.dynamicprogramming.onedimension;

/**
 * https://leetcode.com/problems/n-th-tribonacci-number/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 1137. N-th Tribonacci Number
 * <p>
 * The Tribonacci sequence Tn is defined as follows:
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * Given n, return the value of Tn.
 * <p>
 * Example 1:
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * Example 2:
 * Input: n = 25
 * Output: 1389537
 * <p>
 * Constraints:
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class NthTribonacciNumber {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }

        int t0 = 0;
        int t1 = 1;
        int t2 = 1;
        for (int i = 2; i < n; i++) {
            int nextT = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = nextT;
        }
        return t2;
    }
}
