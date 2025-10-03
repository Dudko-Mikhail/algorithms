package by.dudko.education.algorithm.yandex.recursive;

/**
 * 4 стержня, нужно вывести мин число ходов для перестановки пирамиды с 1 на 4
 */
public class HanoiTowers2 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        System.out.println(countMoves(n));

        //        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + ": " + countMoves(i) + " r: " + countSimpleHanoi(i));
        }
//        System.out.println(countMoves(n));
    }

    private static int countMoves(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n % 2 == 0) {
            return 2 * (countMoves(n / 2) + countSimpleHanoi(n / 2 - 1)) + 1;
        } else {
            return 2 * (countMoves(n / 2) + countSimpleHanoi(n / 2)) + 1;
        }
    }

    private static int countSimpleHanoi(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum * 2 + 1;
        }
        return sum;
    }
}
