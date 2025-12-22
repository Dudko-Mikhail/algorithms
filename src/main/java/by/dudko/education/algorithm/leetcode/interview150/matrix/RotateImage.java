package by.dudko.education.algorithm.leetcode.interview150.matrix;

/**
 * https://leetcode.com/problems/rotate-image/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 48. Rotate Image
 * <p>
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * <p>
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {
    public static void rotate(int[][] matrix) {
        int iterations = matrix.length / 2;
        for (int i = 0; i < iterations; i++) {
            rotateLayer(matrix, i);
        }
    }

    private static void rotateLayer(int[][] matrix, int layer) {
        int end = matrix.length - layer;
        int[] memory = new int[end];
        int counter = 0;
        int endIndex = end - 1;
        for (int i = layer; i < endIndex; i++) { // top to right
            memory[counter++] = matrix[i][endIndex];
            matrix[i][endIndex] = matrix[layer][i];
        }
        memory[counter] = matrix[endIndex][endIndex];

        counter = 0;
        for (int i = endIndex; i > layer; i--) { // right to bottom
            int temp = matrix[endIndex][i];
            matrix[endIndex][i] = memory[counter];
            memory[counter++] = temp;
        }
        memory[counter] = matrix[endIndex][layer];

        counter = 0;
        for (int i = endIndex; i > layer; i--) { // bottom to left
            int temp = matrix[i][layer];
            matrix[i][layer] = memory[counter];
            memory[counter++] = temp;
        }
        memory[counter] = matrix[layer][layer];

        counter = 0;
        for (int i = layer; i < end; i++) { // left to top
            matrix[layer][i] = memory[counter++];
        }
    }
}
