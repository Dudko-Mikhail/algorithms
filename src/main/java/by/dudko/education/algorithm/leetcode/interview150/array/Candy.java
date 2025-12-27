package by.dudko.education.algorithm.leetcode.interview150.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/candy/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 135. Candy
 * <p>
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * <p>
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * <p>
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * <p>
 * Constraints:
 * n == ratings.length
 * 1 <= n <= 2 * 10^4
 * 0 <= ratings[i] <= 2 * 10^4
 */
public class Candy {
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = 0; i < n - 1; i++) {
            int right = i + 1;
            if (ratings[right] > ratings[i]) {
                candies[right] = candies[i] + 1;
            }
        }

        int sum = 0;
        for (int i = n - 1; i > 0; i--) {
            sum += candies[i];
            int left = i - 1;
            if (ratings[left] > ratings[i] && candies[i] + 1 > candies[left]) {
                candies[left] = candies[i] + 1;
            }
        }
        sum += candies[0];

        return sum;
    }

    public static int candyFirstIdea(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Queue<Integer> visitingQueue = new PriorityQueue<>(Comparator.comparingInt(integer -> ratings[integer]));
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
            visitingQueue.offer(i);
        }

        Set<Integer> visited = new HashSet<>();
        while (!visitingQueue.isEmpty()) {
            int current = visitingQueue.remove();
            int rating = ratings[current];
            visited.add(current);

            int left = current - 1;
            if (left > -1 && ratings[left] > rating && !visited.contains(left)) {
                candies[left] = Math.max(candies[left], candies[current] + 1);
            }
            int right = current + 1;
            if (right < n && ratings[right] > rating && !visited.contains(right)) {
                candies[right] = Math.max(candies[right], candies[current] + 1);
            }
        }

        return IntStream.of(candies)
                .sum();
    }
}
