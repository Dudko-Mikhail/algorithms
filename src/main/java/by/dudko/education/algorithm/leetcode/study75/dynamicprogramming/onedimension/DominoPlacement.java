package by.dudko.education.algorithm.leetcode.study75.dynamicprogramming.onedimension;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/domino-and-tromino-tiling/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 790. Domino and Tromino Tiling
 * <p>
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 10^9 + 7.
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two
 * 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= n <= 1000
 */
public class DominoPlacement {
    private static final int MODULO = 1_000_000_007;

    /**
     * Таблица значений для некоторых n
     * n = 1 -> 1
     * n = 2 -> 2
     * n = 3 -> 5
     * n = 4 -> 11
     * n = 5 -> 24
     * n = 6 -> 53
     * n = 7 -> 117
     * <p>
     * Рекуррентная формула f(n) = 2 * f(n - 1) + f(n - 3)
     * f0 = 1;
     * f3 = 2 * f2 + f0 = 2 + 2 + 1 = 5;
     * f4 = 2 *f3 + f1 = 5*2 + 1 = 11
     * f5 = 2 * f4 + f2 = 2 * 11 + 2 = 24
     * f6 = 2 * f5+ f3 = 2 * 24 + 5 = 48 + 5 = 53
     */
    public int numTilings(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        Deque<Integer> lastThreeElements = new LinkedList<>();
        lastThreeElements.offer(1);
        lastThreeElements.offer(1);
        lastThreeElements.offer(2);

        for (int i = 3; i <= n; i++) {
            long current = lastThreeElements.getLast() * 2L + lastThreeElements.remove();
            lastThreeElements.offer((int) (current % MODULO));
        }

        return lastThreeElements.getLast();
    }


    public int numTilingsArr(int n) {
        int[] results = new int[Math.max(3, n + 1)];
        results[0] = 1;
        results[1] = 1;
        results[2] = 2;

        for (int i = 3; i <= n; i++) {
            long current = results[i - 1] * 2L + results[i - 3];
            results[i] = (int) (current % MODULO);
        }
        return results[n];
    }

    public static void main(String[] args) {
        DominoPlacement placement = new DominoPlacement();
        System.out.println(placement.numTilingsArr(2));
    }
}

//                       При поворачивании тройных плиточек кол-во вариантов не изменяется
// n = 4
// result = 5 +       2 +       2 +       2 = 11
//    1 0 0 0   1 1 0 0   1 1 0 0   1 0 0 0
//    1 0 0 0   2 2 0 0   1 0 0 0   1 1 0 0

//                     v1 1 1 3 3  v1* 1 2 2 3            0 0 = y(4) = 2 размещения
//                        1 2 2 3      1 1 3 3          0 0 0

//                     v2 1 1 2 3  v2* 1 1 2 3
//                        1 2 2 3      1 2 2 3
//
// n = 5
// result = (n-1)  11 + (n-2)   5 +     2 * (2 + 2) = 16 + 8 = 24
//          1 0 0 0 0   1 1 0 0 0   1 1 0 0 0             0 0 0 = y(5) = 4 размещения
//          1 0 0 0 0   2 2 0 0 0   1 0 0 0 0           0 0 0 0
//                                  варианты y
//                                  1 1 0 0 0 = 2
//                                  1 2 2 0 0

//                                  1 1 2 0 0 = 2
//                                  1 2 2 0 0

// n = 6
// result = (n-1)    24 + (n-2)    11 + 2 * (4 + 5) = 24 + 11 + 18 = 29 + 24 = 53           y(n) = y(n-1) + f(n - 3) - 2
//          1 0 0 0 0 0   1 1 0 0 0 0   1 1 0 0 0 0         0 0 0 0 = y(6) = 9 размещений
//          1 0 0 0 0 0   2 2 0 0 0 0   1 0 0 0 0 0       0 0 0 0 0
//                                      варианты y
//
//                                      1 1 0 0 0 0 = y(5) = 4
//                                      1 2 2 0 0 0
//
//                                      1 1 2 0 0 0 = f(3) = 5
//                                      1 2 2 0 0 0
//                                                       Проверка n = 7
//                                                       f(7) = f(6) + f(5) + 2 * y(6) = 53 + 24 + 2 * (20) = 93 + 24 = 117
//
// n = 7
//                                                          0 0 0 0 0 = y(7) = y(6) + n(4) = 9 + 11 = 20
//                                                        0 0 0 0 0 0
//
//                                                          1 0 0 0 0 = f(4)
//                                                        1 1 0 0 0 0
//
//                                                          0 0 0 0 0 = y(6)
//                                                        1 1 0 0 0 0