package by.dudko.education.algorithm.yandex.greedy;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48630/problem?id=215%2F2023_04_08%2FHq9cWYIQxa
 */
public class TennisTournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] participants = new int[n];
        for (int i = 0; i < n; i++) {
            participants[i] = scanner.nextInt();
        }
        System.out.println(calculateMaxWins(k, participants));
    }

    public static int calculateMaxWins(int k, int[] participants) {
        int targetParticipantPower = participants[k - 1];
        System.out.println("Target: " + targetParticipantPower);
        Arrays.sort(participants);
        int[] tournament = new int[participants.length - 1];
        for (int i = 0, j = 0; i < participants.length; i++) {
            if (i != k - 1) {
                tournament[j++] = participants[i];
            }
        }

        int winCount = 0;
        int[] currentRound = tournament;
        while (currentRound.length > 0) {
            System.out.println("Current: " + Arrays.toString(currentRound));
            int[] nextRound = new int[currentRound.length / 2];
            if (currentRound[0] > targetParticipantPower && currentRound.length % 2 != 0) {
                break;
            }
            for (int j = 0; j < nextRound.length; j++) {
                nextRound[nextRound.length - 1 - j] = currentRound[(currentRound.length - 1) - j * 2];
            }
            currentRound = nextRound;
            winCount++;
        }
        return winCount;
    }
}


class Solution {
/*
    input: [344, 3464, 1604, 6926, 1891, 2848, 6052, 5288, 5417, 8706, 344]
    first: 3440
    second: 41556
 */

    public static void main(String[] args) {
        Random random = new Random();
        Solution solution = new Solution();
        for (int i = 0; i < 100000; i++) {
            int size = random.nextInt(1000) + 1;
            int[] input = random.ints(size, 0, 10000)
                    .toArray();
            int first = solution.maxArea(input);
            int second = solution.maxAreaSquired(input);
            if (first != second) {
                throw new IllegalStateException("""
                        Invalid algorithm
                        input: %s
                        first: %s
                        second: %s
                        """.formatted(Arrays.toString(input), first, second));
            }
        }
//        System.out.println(solution.maxArea(new int[]{344, 3464, 1604, 6926, 1891, 2848, 6052, 5288, 5417, 8706, 344}));
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;

        while (leftPointer < rightPointer) {
            int leftHeight = height[leftPointer];
            int rightHeight = height[rightPointer];
            int area = calculateArea(leftHeight, rightHeight, rightPointer, leftPointer);
            if (area > maxArea) {
                maxArea = area;
            }

            if (leftHeight >= rightHeight) {
                rightPointer = findHigherPositionReversed(rightPointer, leftPointer, rightHeight, height);
            } else {
                leftPointer = findHigherPositionDirect(leftPointer, rightPointer, leftHeight, height);
            }
        }
        return maxArea;
    }

    private static int calculateArea(int leftHeight, int rightHeight, int rightPointer, int leftPointer) {
        return Math.min(leftHeight, rightHeight) * (rightPointer - leftPointer);
    }


    private int findHigherPositionDirect(int start, int stop, int current, int[] height) {
        for (int i = start; i < stop; i++) {
            if (height[i] > current) {
                return i;
            }
        }
        return height.length;
    }

    private int findHigherPositionReversed(int start, int stop, int current, int[] height) {
        for (int i = start; i > stop; i--) {
            if (height[i] > current) {
                return i;
            }
        }
        return 0;
    }


    public int maxAreaSquired(int[] height) {
        int maxArea = 0;
        int left = height[0];
        int highestLeft = left;
        for (int j = 1; j < height.length; j++) {
            int right = height[j];
            int area = Math.min(left, right) * j;
            if (area > maxArea) {
                maxArea = area;
            }
        }

        for (int i = 1; i < height.length; i++) {
            left = height[i];
            if (left > highestLeft) {
                highestLeft = left;
                for (int j = i + 1; j < height.length; j++) {
                    int right = height[j];
                    int area = Math.min(left, right) * (j - i);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }
        return maxArea;
    }
}