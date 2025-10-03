package by.dudko.education.algorithm.yandex.sum;

import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/42492/problem?id=6789665%2F2023_04_06%2FGWcZqPTpiJ
 * <p>
 * Пример: abc + def = adbecf
 */
public class CurveSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        String first = scanner.nextLine();
        String second = scanner.nextLine();
        StringBuilder builder = new StringBuilder();
        int firstPointer = 0;
        int secondPointer = 0;
        for (int i = 0; i < 2 * length; i++) {
            if (i % 2 == 0) {
                builder.append(first.charAt(firstPointer++));
            } else {
                builder.append(second.charAt(secondPointer++));
            }
        }
        System.out.println(builder);
    }
}
