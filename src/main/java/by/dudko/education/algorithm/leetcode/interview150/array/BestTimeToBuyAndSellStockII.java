package by.dudko.education.algorithm.leetcode.interview150.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 122. Best Time to Buy and Sell Stock II
 * <p>
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.
 * Find and return the maximum profit you can achieve.
 * <p>
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * <p>
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * <p>
 * Example 3:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 * <p>
 * Constraints:
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 */
public class BestTimeToBuyAndSellStockII {
    public static int maxProfit(int[] prices) {
        int profit = 0;
        int i = 0;
        while (i < prices.length) {
            i = findBuyPrice(i, prices);
            if (i >= prices.length) {
                break;
            }
            int buyPrice = prices[i - 1];
            i = findSellPrice(i, prices);
            int sellPrice = prices[i - 1];
            profit += sellPrice - buyPrice;
        }
        return profit;
    }

    private static int findBuyPrice(int start, int[] prices) {
        int i = start + 1;
        int min = prices[start];
        while (i < prices.length && prices[i] <= min) {
            min = prices[i++];
        }
        return i;
    }

    private static int findSellPrice(int start, int[] prices) {
        int i = start + 1;
        int max = prices[start];
        while (i < prices.length && prices[i] >= max) {
            max = prices[i++];
        }
        return i;
    }
}
