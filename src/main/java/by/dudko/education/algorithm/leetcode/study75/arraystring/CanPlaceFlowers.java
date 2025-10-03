package by.dudko.education.algorithm.leetcode.study75.arraystring;

/**
 * https://leetcode.com/problems/can-place-flowers/description/?envType=study-plan-v2&envId=leetcode-75
 * 605. Can Place Flowers
 * <p>
 * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
 * <p>
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
 * return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (canPlant(i, flowerbed)) {
                count++;
                i++;
                if (count >= n) {
                    break;
                }
            }
        }
        return count >= n;
    }

    private boolean canPlant(int i, int[] flowers) {
        if (flowers[i] == 1) {
            return false;
        }
        if (i > 0 && flowers[i - 1] == 1) {
            return false;
        }
        return i >= flowers.length - 1 || flowers[i + 1] != 1;
    }
}
