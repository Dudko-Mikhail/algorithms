package by.dudko.education.algorithm.leetcode.interview150.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 68. Text Justification
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * Extra spaces between words should be distributed as evenly as possible.If the number of spaces on a line does not
 * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * <p>
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * <p>
 * Example 1:
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * <p>
 * Example 2:
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * <p>
 * Example 3:
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * <p>
 * Constraints:
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 */
public class TextJustification {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        int i = 0;
        int n = words.length;
        List<String> result = new ArrayList<>();
        while (i < n) {
            int wordsCount = countWords(words, maxWidth, i);
            result.add(wordsCount + i != n ? buildLine(words, maxWidth, i, wordsCount)
                    : buildLastLine(words, maxWidth, i));
            i += wordsCount;
        }
        return result;
    }

    private static int countWords(String[] words, int maxWidth, int start) {
        int length = words[start].length();
        int wordsCount = 1;
        int i = start + 1;

        while (i < words.length && length < maxWidth) {
            length += words[i++].length() + 1;
            wordsCount++;
        }
        if (length > maxWidth) {
            wordsCount--;
        }
        return wordsCount;
    }

    public static String buildLine(String[] words, int maxWidth, int start, int wordsCount) {
        StringBuilder builder = new StringBuilder(words[start]);
        int wordsLength = builder.length();
        if (wordsCount == 1) {
            return builder + " ".repeat(maxWidth - wordsLength);
        }

        for (int i = start + 1; i < start + wordsCount; i++) {
            wordsLength += words[i].length();
        }

        int remaining = maxWidth - wordsLength;
        int spaceCount = remaining / (wordsCount - 1);
        int extraSpaces = maxWidth - spaceCount * (wordsCount - 1) - wordsLength;

        for (int i = start + 1; i < start + wordsCount; i++) {
            builder.append(" ".repeat(spaceCount));
            if (extraSpaces > 0) {
                builder.append(" ");
                extraSpaces--;
            }
            builder.append(words[i]);
        }
        return builder.toString();
    }

    public static String buildLastLine(String[] words, int maxWidth, int start) {
        StringBuilder builder = new StringBuilder(words[start]);
        for (int i = start + 1; i < words.length; i++) {
            builder.append(" ")
                    .append(words[i]);
        }
        int diff = maxWidth - builder.length();
        return diff == 0 ? builder.toString() :
                builder + " ".repeat(diff);
    }
}
