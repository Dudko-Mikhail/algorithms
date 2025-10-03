package by.dudko.education.algorithm.yandex.multiplication;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class MaxProductWithNegatives {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product size: ");
        int productSize = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter n: ");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println("Input n numbers: ");
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }

        Arrays.sort(input);
        int negativesSeparator = 0;
        if (input[n - 1] < 0) {
            negativesSeparator = n;
        } else {
            for (int i = 0; i < n; i++) {
                if (input[i] >= 0) {
                    negativesSeparator = i;
                    break;
                }
            }
        }

        BigInteger product;
        if (isPositiveProduct(productSize, negativesSeparator, n)) {
            System.out.println("PositiveBrunch");
            product = calculatePositiveProduct(input, negativesSeparator, productSize);
        } else {
            System.out.println("NegativeBrunch");
            product = calculateNegativeProduct(input, negativesSeparator, productSize);
        }

        System.out.println(Arrays.toString(input));
        System.out.println("Negative separator: " + negativesSeparator);
        System.out.println("Result: " + product);
    }

    private static BigInteger calculateNegativeProduct(int[] input, int negativesSeparator, int productSize) {
        BigInteger product = BigInteger.ONE;
        int negativesPointer = negativesSeparator - 1;
        int positivesPointer = negativesSeparator;

        int i = 0;
        for (; i < productSize && negativesPointer >= 0 && positivesPointer < input.length; i++) {
            int number;
            if (input[negativesPointer] * -1 <= input[positivesPointer]) {
                number = input[negativesPointer--];
            } else {
                number = input[positivesPointer++];
            }
            product = product.multiply(BigInteger.valueOf(number));
        }

        for (int j = negativesPointer; i < productSize && j >= 0; j--, i++) {
            product = product.multiply(BigInteger.valueOf(input[j]));
        }
        for (int j = positivesPointer; i < productSize && j < input.length; j++, i++) {
            product = product.multiply(BigInteger.valueOf(input[j]));
        }
        return product;
    }

    private static BigInteger calculatePositiveProduct(int[] input, int negativesSeparator, int productSize) {
        int maxNegativePairs = calculateMaxNegativePairs(productSize, negativesSeparator);
        int requiredPositives = Math.max(productSize - maxNegativePairs * 2, 0);
        System.out.println("Required Positives: " + requiredPositives);
        BigInteger product = BigInteger.ONE;

        int stopIndex = input.length - requiredPositives;
        for (int i = input.length - 1; i >= stopIndex; i--) {
            product = product.multiply(BigInteger.valueOf(input[i]));
        }

        int negativePairIndex = 0;
        int positivePairIndex = 0;
        for (int i = 0; i < maxNegativePairs; i++) {
            int firstNegative = input[2 * negativePairIndex];
            int secondNegative = input[2 * negativePairIndex + 1];
            int firstPositive = input[stopIndex - 2 * positivePairIndex - 1];
            int secondPositive = input[stopIndex - 2 * positivePairIndex - 2];
            long negativesProduct = (long) firstNegative * secondNegative;
            long positivesProduct = (long) firstPositive * secondPositive;
            if (negativesProduct > positivesProduct) {
                negativePairIndex++;
                product = product.multiply(BigInteger.valueOf(negativesProduct));
            } else {
                positivePairIndex++;
                product = product.multiply(BigInteger.valueOf(positivesProduct));
            }
        }
        return product;
    }


    private static boolean isPositiveProduct(int productSize, int negatives, int n) {
        int positives = n - negatives;
        int maxNegativePairs = calculateMaxNegativePairs(productSize, negatives);
        return (productSize - maxNegativePairs * 2 - positives) <= 0;
    }

    private static int calculateMaxNegativePairs(int productSize, int negativesCount) {
        return Math.min((negativesCount / 2), productSize / 2);
    }
}
