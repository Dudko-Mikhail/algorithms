package by.dudko.education.algorithm.leetcode150.binarytree;

import by.dudko.education.algorithm.leetcode150.binarytree.util.TreeUtils;

import java.util.List;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * Constraints:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // inorder = LMR, preorder = MLR
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0, preorder.length);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder, int startPreorder, int startInorder,
                                      int length) {
        if (length <= 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[startPreorder]);

        int i = startInorder;
        int end = i + length;
        while (i < end && inorder[i] != root.val) {
            i++;
        }

        int leftPartLength = i - startInorder;
        root.left = buildTree(preorder, inorder, startPreorder + 1, startInorder, leftPartLength);
        root.right = buildTree(preorder, inorder, startPreorder + 1 + (i - startInorder), i + 1, length - leftPartLength - 1);
        return root;
    }


    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        return buildTree(IntStream.of(preorder).boxed().toList(), IntStream.of(inorder).boxed().toList());
    }

    private static TreeNode buildTree(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.isEmpty()) {
            return null;
        }

        TreeNode root = new TreeNode(preorder.getFirst());

        int i = 0;
        while (i < inorder.size() && inorder.get(i) != root.val) {
            i++;
        }

        root.left = buildTree(preorder.subList(1, i + 1), inorder.subList(0, i));
        root.right = buildTree(preorder.subList(i + 1, preorder.size()), inorder.subList(i + 1, inorder.size()));
        return root;
    }

    public static void main(String[] args) {
        TreeNode result = buildTree(new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});
        System.out.println(TreeUtils.stringifyTree(result, TreeUtils.Traversal.INORDER, true));
        System.out.println(TreeUtils.stringifyTree(result, TreeUtils.Traversal.PRE_ORDER, true));
        System.out.println(TreeUtils.stringifyTree(result, TreeUtils.Traversal.LAYERED, true));
    }
}
