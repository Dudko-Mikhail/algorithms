package by.dudko.education.algorithm.leetcode.study75.arraystring;

/**
 * 1071. Greatest Common Divisor of Strings
 * <p>
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 * <p>
 * Example 1:
 * <p>
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 * <p>
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 * <p>
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 */
public class GreatestCommonDivisorOfStrings {
    public String gcdOfStrings(String first, String second) {
        int gcdMaxLength = gcd(first.length(), second.length());

        for (int i = gcdMaxLength; i > 0; i--) {
            String potentialGcd = first.substring(0, i);
            if (isDivisor(first, potentialGcd) && isDivisor(second, potentialGcd)) {
                return potentialGcd;
            }
        }
        return "";
    }

    private boolean isDivisor(String target, String gcd) {
        int gcdLength = gcd.length();
        if (target.length() % gcdLength != 0) {
            return false;
        }
        int count = target.length() / gcdLength;
        for (int i = 0; i < count; i++) {
            int start = i * gcdLength;
            if (!target.substring(start, start + gcdLength).equals(gcd)) {
                return false;
            }
        }
        return true;
    }

    private static int gcd(int first, int second) {
        if (second == 0) {
            return first;
        }
        return gcd(second, first % second);
    }
}
