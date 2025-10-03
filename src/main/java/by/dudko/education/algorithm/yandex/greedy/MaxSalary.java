package by.dudko.education.algorithm.yandex.greedy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * В качестве последнего вопроса на собеседовании будущий начальник дает вам n бумажек с одним числом на каждой
 * и говорит составить из них самое большое число. Получившееся число — ваша зарплата,
 * поэтому вы очень замотивированы решить эту задачу!
 * <p>
 * Числа [1, 1000]
 */
public class MaxSalary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            numbers.add(scanner.nextInt());
        }
        System.out.println(findMaxSalary(numbers));

        stressTest();

    }

    private static void stressTest() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int inputSize = 100;
            int n = random.nextInt(inputSize);
            List<Integer> numbers = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                numbers.add(random.nextInt(1000) + 1);
            }
            List<String> stringNumbers = numbers.stream()
                    .map(Objects::toString)
                    .collect(Collectors.toList());
            String stringsResult = findMaxSalaryStrings(stringNumbers);
            String result = findMaxSalary(numbers);
            if (!result.equals(stringsResult)) {
                throw new IllegalStateException("""
                        Invalid algorithm.
                        Iteration = %s
                        N = %s
                        Input: %s
                        M = %s
                        O = %s
                        My > Other: %b
                        """.formatted(i, n, numbers, result, stringsResult,
                        new BigInteger(result).compareTo(new BigInteger(stringsResult)) > 0));
            }
        }
    }


    public static String findMaxSalary(List<Integer> numbers) {
        numbers.sort(MaxSalary::compare);
        return numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }


    public static String findMaxSalaryStrings(List<String> numbers) {
        numbers.sort(MaxSalary::compareWithStrings);
        return numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }


    private static int compare(Integer first, Integer second) {
        int firstLength = ((int) Math.log10(first)) + 1;
        int secondLength = ((int) Math.log10(second)) + 1;

        int firstNumber = first * ((int) Math.pow(10, secondLength)) + second;
        int secondNumber = second * ((int) Math.pow(10, firstLength)) + first;

        return Integer.compare(secondNumber, firstNumber);
    }


    private static int compareWithStrings(String first, String second) {
        String firstResult = first + second;
        String secondResult = second + first;
        int result = firstResult.compareTo(secondResult);
        if (result == 0) {
            return 0;
        }
        return result > 0 ? -1 : 1;
    }
}
