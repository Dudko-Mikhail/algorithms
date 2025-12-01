package by.dudko.education.algorithm.leetcode.study75.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 208. Implement Trie (Prefix Tree)
 * <p>
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * <p>
 * Example 1:
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * Constraints:
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */
public class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        doInsert(word, 0, root);
    }

    private void doInsert(String word, int charIndex, TrieNode currentLayer) {
        Character symbol = word.charAt(charIndex);
        if (charIndex != word.length() - 1) {
            TrieNode nextLayer = currentLayer.insertIfAbsent(symbol);
            doInsert(word, charIndex + 1, nextLayer);
        } else {
            currentLayer.insertEnd(symbol);
        }
    }

    public boolean search(String word) {
        return doSearch(word, 0, root, true);
    }

    public boolean startsWith(String prefix) {
        return doSearch(prefix, 0, root, false);
    }

    private boolean doSearch(String word, int charIndex, TrieNode currentLayer, boolean strict) {
        Character symbol = word.charAt(charIndex);
        if (charIndex != word.length() - 1) {
            TrieNode nextLayer = currentLayer.getNextLayer(symbol);
            if (nextLayer == null) {
                return false;
            }
            return doSearch(word, charIndex + 1, nextLayer, strict);
        }

        return strict ? currentLayer.isWordEnd(symbol)
                : currentLayer.getNextLayer(symbol) != null;
    }

    private static class TrieNode {
        final Map<Character, TrieNode> nodes = new HashMap<>();
        final Set<Character> wordEnds = new HashSet<>();

        TrieNode getNextLayer(Character symbol) {
            return nodes.get(symbol);
        }

        void insertEnd(Character symbol) {
            insertIfAbsent(symbol);
            wordEnds.add(symbol);
        }

        TrieNode insertIfAbsent(Character symbol) {
            return nodes.computeIfAbsent(symbol, _ -> new TrieNode());
        }

        boolean isWordEnd(Character symbol) {
            return wordEnds.contains(symbol);
        }
    }
}
