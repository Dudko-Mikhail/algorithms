package by.dudko.education.algorithm.leetcode.study75.monotinicstack;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/daily-temperatures/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 739. Daily Temperatures
 * <p>
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such
 * that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * <p>
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * <p>
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * Constraints:
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Queue<Integer> temperatureQueue = new PriorityQueue<>(Comparator.comparingInt(f -> temperatures[f]));
        for (int i = 0; i < n; i++) {
            int currentTemperature = temperatures[i];
            while (!temperatureQueue.isEmpty()) {
                int coldest = temperatureQueue.peek();
                if (currentTemperature <= temperatures[coldest]) {
                    break;
                }
                temperatureQueue.remove();
                answer[coldest] = i - coldest;
            }
            temperatureQueue.add(i);
        }

        return answer;
    }

    public static int[] dailyTemperaturesWithStack(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int currentTemperature = temperatures[i];
            while (!stack.isEmpty()) {
                int index = stack.peek();
                if (currentTemperature <= temperatures[index]) {
                    break;
                }
                stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }

        return answer;
    }
}
