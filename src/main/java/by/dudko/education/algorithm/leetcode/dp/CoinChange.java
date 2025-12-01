package by.dudko.education.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/coin-change/description/
 * <p>
 * 322. Coin Change
 * <p>
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * <p>
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) { // аналог задачи о кузнечике
        boolean[] possible = new boolean[amount + 1];
        possible[0] = true;
        int[] coinCounts = new int[amount + 1];
        for (int i = 0; i < amount; i++) {
            if (!possible[i]) {
                continue;
            }

            for (int coin : coins) {
                int nextPosition = i + coin;
                if (nextPosition > 0 && nextPosition <= amount) {
                    possible[nextPosition] = true;
                    if (coinCounts[nextPosition] == 0) {
                        coinCounts[nextPosition] = coinCounts[i] + 1;
                    } else {
                        coinCounts[nextPosition] = Math.min(coinCounts[i] + 1, coinCounts[nextPosition]);
                    }
                }
            }
        }

        return possible[amount] ? coinCounts[amount] : -1;
    }

    private static final int MAX_AMOUNT = 10001;

    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int[] previousDp = new int[amount + 1];
        for (int i = 1; i < previousDp.length; i++) {
            previousDp[i] = MAX_AMOUNT;
        }

        for (int coin : coins) {
            int[] dp = new int[amount + 1];
            for (int i = 1; i <= amount; i++) {
                dp[i] = previousDp[i];
                if (i >= coin && dp[i - coin] != MAX_AMOUNT) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            previousDp = dp;
        }
        return previousDp[amount] != MAX_AMOUNT ? previousDp[amount] : -1;
    }
}
