package by.dudko.education.algorithm.leetcode.study75.binarysearch;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 374. Guess Number Higher or Lower
 * <p>
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * You call a pre-defined API int guess(int num), which returns three possible results:
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 * 1: Your guess is lower than the number I picked (i.e. num < pick).
 * 0: your guess is equal to the number I picked (i.e. num == pick).
 * Return the number that I picked.
 * <p>
 * Example 1:
 * Input: n = 10, pick = 6
 * Output: 6
 * <p>
 * Example 2:
 * Input: n = 1, pick = 1
 * Output: 1
 * <p>
 * Example 3:
 * Input: n = 2, pick = 1
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= n <= 231 - 1
 * 1 <= pick <= n
 */
public class GuessNumberHigherOrLower {
    int pick = 7;

    public int guessNumber(int n) {
        long left = 0;
        long right = n;
        int guess;
        while (true) {
            long temp = (left + right) / 2;
            guess = (int) temp;
            int answer = guess(guess);
            if (answer == 0) {
                return guess;
            }

            if (answer == 1) {
                left = temp + 1;
            } else {
                right = temp - 1;
            }
        }
    }


    int guess(int num) {
        if (num == pick) {
            return 0;
        }
        return num > pick ? -1 : 1;
    }


    public static void main(String[] args) {
        GuessNumberHigherOrLower guessNumberHigherOrLower = new GuessNumberHigherOrLower();
        System.out.println(guessNumberHigherOrLower.guessNumber(7));
    }
}
