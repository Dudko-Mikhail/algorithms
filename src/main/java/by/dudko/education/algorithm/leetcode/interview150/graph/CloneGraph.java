package by.dudko.education.algorithm.leetcode.interview150.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/clone-graph/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 133. Clone Graph
 * <p>
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * Test case format:
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * <p>
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * <p>
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * <p>
 * Example 3:
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * <p>
 * Constraints:
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */
public class CloneGraph {
    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Integer, Node> valueToOldNode = new HashMap<>();
        Map<Integer, Node> valueToNewNode = new HashMap<>();
        traverseGraph(node, valueToOldNode, valueToNewNode);

        for (var key : valueToOldNode.keySet()) {
            copyNode(key, valueToOldNode, valueToNewNode);
        }
        return valueToNewNode.get(1);
    }

    private static void traverseGraph(Node node, Map<Integer, Node> valueToOldNode, Map<Integer, Node> valueToNewNode) {
        if (valueToOldNode.containsKey(node.val)) {
            return;
        }
        valueToNewNode.put(node.val, new Node(node.val));
        valueToOldNode.put(node.val, node);
        for (Node neighbour : node.neighbors) {
            traverseGraph(neighbour, valueToOldNode, valueToNewNode);
        }
    }


    private static void copyNode(int toCopy, Map<Integer, Node> oldNodes, Map<Integer, Node> newNodes) {
        Node oldNode = oldNodes.get(toCopy);
        Node newNode = newNodes.get(toCopy);
        for (Node neighbour : oldNode.neighbors) {
            newNode.neighbors.add(newNodes.get(neighbour.val));
        }
    }
}


class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}