package by.dudko.education.algorithm.leetcode.interview150.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;

/**
 * https://leetcode.com/problems/simplify-path/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 71. Simplify Path
 * <p>
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
 * <p>
 * The rules of a Unix-style file system are as follows:
 * <p>
 * A single period '.' represents the current directory.
 * A double period '..' represents the previous/parent directory.
 * Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
 * Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
 * The simplified canonical path should follow these rules:
 * <p>
 * The path must start with a single slash '/'.
 * Directories within the path must be separated by exactly one slash '/'.
 * The path must not end with a slash '/', unless it is the root directory.
 * The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
 * Return the simplified canonical path.
 * <p>
 * Example 1:
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation:
 * The trailing slash should be removed.
 * <p>
 * Example 2:
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation:
 * Multiple consecutive slashes are replaced by a single one.
 * <p>
 * Example 3:
 * Input: path = "/home/user/Documents/../Pictures"
 * Output: "/home/user/Pictures"
 * Explanation:
 * A double period ".." refers to the directory up a level (the parent directory).
 * <p>
 * Example 4:
 * Input: path = "/../"
 * Output: "/"
 * Explanation:
 * Going one level up from the root directory is not possible.
 * <p>
 * Example 5:
 * Input: path = "/.../a/../b/c/../d/./"
 * Output: "/.../b/d"
 * Explanation:
 * "..." is a valid name for a directory in this problem.
 * <p>
 * Constraints:
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class SimplifyPath {
    public static String simplifyPath(String path) {
        Deque<String> directories = new ArrayDeque<>();
        char[] chars = path.toCharArray();
        for (int i = 1, len = chars.length; i < len; i++) {
            if (chars[i] == '/') {
                continue;
            }

            if (chars[i] == '.') {
                boolean onlyDots = true;
                StringBuilder word = new StringBuilder();
                while (i < len && chars[i] != '/') {
                    if (chars[i] != '.') {
                        onlyDots = false;
                    }
                    word.append(chars[i]);
                    i++;
                }
                if (onlyDots) {
                    if (word.length() > 2) {
                        directories.push(word.toString());
                    } else if (word.length() == 2 && !directories.isEmpty()) {
                        directories.pop();
                    }
                } else {
                    directories.push(word.toString());
                }
            } else {
                String word = parseWord(i, chars);
                directories.push(word);
                i += word.length();
            }
        }
        return buildPath(directories);
    }

    private static String buildPath(Deque<String> directories) {
        if (directories.isEmpty()) {
            return "/";
        }
        StringJoiner joiner = new StringJoiner("/", "/", "");
        while (!directories.isEmpty()) {
            joiner.add(directories.removeLast());
        }
        return joiner.toString();
    }

    private static String parseWord(int startIndex, char[] chars) {
        StringBuilder word = new StringBuilder();
        for (int i = startIndex; i < chars.length && chars[i] != '/'; i++) {
            word.append(chars[i]);
        }
        return word.toString();
    }
}
