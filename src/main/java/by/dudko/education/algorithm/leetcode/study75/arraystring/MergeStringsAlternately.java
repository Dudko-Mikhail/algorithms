package by.dudko.education.algorithm.leetcode.study75.arraystring;

/**
 * 1768. Merge Strings Alternately
 * <p>
 * https://leetcode.com/problems/merge-strings-alternately/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 * <p>
 * Return the merged string.
 */
public class MergeStringsAlternately {
    public String mergeAlternately(String word1, String word2) {
        int firstPointer = 0;
        int secondPointer = 0;
        int stopIndex = Math.min(word1.length(), word2.length());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stopIndex; i++) {
            builder.append(word1.charAt(firstPointer++));
            builder.append(word2.charAt(secondPointer++));
        }

        builder.append(word1.substring(firstPointer));
        builder.append(word2.substring(secondPointer));
        return builder.toString();
    }
}
