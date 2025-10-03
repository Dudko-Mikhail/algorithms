package by.dudko.education.algorithm.yandex.dynamic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Вы играете в игру "Камни": игру для двух игроков с двумя наборами камней по
 * n и m штук. С каждым ходом один игрок может взять один камень (из любого набора) или два камня (по одному из обоих).
 * Когда камень забрали, он выходит из игры. Побеждает игрок, который заберет последний камень. Первый ход за вами.
 * <p>
 * Вы и ваш оппонент играете оптимально.
 */
public class Stones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]) + 1;
        int m = Integer.parseInt(input[1]) + 1;
        boolean[][] gameResultMatrix = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gameResultMatrix[i][j] = defineGameResult(gameResultMatrix, i, j);
            }
        }
        printResultMatrix(gameResultMatrix);
        System.out.println(gameResultMatrix[n - 1][m - 1] ? "Win" : "Lose");
    }

    private static void printResultMatrix(boolean[][] gameResultMatrix) {
        Arrays.stream(gameResultMatrix)
                .forEach(arr -> {
                    for (Boolean aBoolean : arr) {
                        System.out.print(aBoolean ? "W " : "L ");
                    }
                    System.out.println();
                });
    }

    private static Boolean defineGameResult(boolean[][] gameResultMatrix, int i, int j) {
        return Arrays.stream(Moves.values())
                .anyMatch(move -> checkMove(gameResultMatrix, move, i, j));
    }

    private static boolean checkMove(boolean[][] gameResultMatrix, Moves move, int i, int j) {
        int afterMoveI = move.modifyI(i);
        int afterMoveJ = move.modifyJ(j);
        return afterMoveI >= 0 && afterMoveJ >= 0
                && !gameResultMatrix[afterMoveI][afterMoveJ];
    }

    private enum Moves {
        FIRST {
            @Override
            public int modifyI(int i) {
                return i - 1;
            }

            @Override
            public int modifyJ(int j) {
                return j - 1;
            }
        },
        SECOND {
            @Override
            public int modifyI(int i) {
                return i - 1;
            }

            @Override
            public int modifyJ(int j) {
                return j;
            }
        },
        THIRD {
            @Override
            public int modifyI(int i) {
                return i;
            }

            @Override
            public int modifyJ(int j) {
                return j - 1;
            }
        };

        abstract int modifyI(int i);

        abstract int modifyJ(int j);
    }
}
