package by.dudko.education.algorithm.yandex;

public class CFromNToK {
    private static int calculateCFromNToKWithRepeats(int n, int k) {
        return calculateCFromNToK(n + k - 1, k);
    }

    private static int calculateCFromNToK(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private static int factorial(int n) {
        int sum = 1;
        for (int i = 2; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }
}
