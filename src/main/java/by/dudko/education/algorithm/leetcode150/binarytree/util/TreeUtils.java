package by.dudko.education.algorithm.leetcode150.binarytree.util;

import by.dudko.education.algorithm.leetcode150.binarytree.TreeNode;

import java.util.*;

public final class TreeUtils {
    private TreeUtils() {
    }

    public static TreeNode buildBinaryTree(int... values) {
        if (values == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        for (int i = 1; i < values.length; i++) {
            insertElementIntoBinaryTree(root, values[i]);
        }
        return root;
    }

    private static boolean insertElementIntoBinaryTree(TreeNode node, int value) {
        if (node == null) {
            return true;
        }

        if (value > node.val) {
            if (insertElementIntoBinaryTree(node.right, value)) {
                node.right = new TreeNode(value);
            }
        } else {
            if (insertElementIntoBinaryTree(node.left, value)) {
                node.left = new TreeNode(value);
            }
        }
        return false;
    }

    public static TreeNode buildBinaryTree(Collection<Integer> collection) {
        if (collection == null) {
            return null;
        }
        int[] values = new int[collection.size()];
        int index = 0;
        for (Integer elem : collection) {
            values[index++] = elem;
        }
        return buildBinaryTree(values);
    }

    public static String stringifyTree(TreeNode treeNode, Traversal traversal, boolean direct) {
        return stringifyTree(treeNode, traversal, direct, ", ", "[", "]");
    }

    public static String stringifyTree(TreeNode treeNode, Traversal traversal, boolean direct,
                                       String separator, String prefix, String suffix) {
        return traversal.stringifyTree(treeNode, direct, new StringJoiner(separator, prefix, suffix));
    }

    public enum Traversal {
        INORDER {
            @Override
            String stringifyTree(TreeNode root, boolean direct, StringJoiner joiner) {
                Extractors extractor = getExtractorByOrder(direct);
                helper(root, joiner, extractor);
                return joiner.toString();
            }

            private void helper(TreeNode root, StringJoiner joiner, Extractors extractor) {
                if (root == null) {
                    return;
                }

                helper(extractor.first(root), joiner, extractor);
                joiner.add(Integer.toString(root.val));
                helper(extractor.second(root), joiner, extractor);
            }
        },
        PRE_ORDER {
            @Override
            String stringifyTree(TreeNode root, boolean direct, StringJoiner joiner) {
                Extractors extractor = getExtractorByOrder(direct);
                helper(root, joiner, extractor);
                return joiner.toString();
            }

            private void helper(TreeNode root, StringJoiner joiner, Extractors extractor) {
                if (root == null) {
                    return;
                }

                joiner.add(Integer.toString(root.val));
                helper(extractor.first(root), joiner, extractor);
                helper(extractor.second(root), joiner, extractor);
            }
        },
        LAYERED {
            @Override
            String stringifyTree(TreeNode root, boolean direct, StringJoiner joiner) {
                if (root == null) {
                    return joiner.toString();
                }
                Extractors extractor = getExtractorByOrder(direct);
                Queue<TreeNode> queue = new ArrayDeque<>();
                queue.offer(root);

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        TreeNode node = queue.remove();
                        joiner.add(node.toString());
                        if (extractor.first(node) != null) {
                            queue.offer(extractor.first(node));
                        }
                        if (extractor.second(node) != null) {
                            queue.offer(extractor.second(node));
                        }
                    }
                    joiner.add("\n");
                }
                return joiner.toString();
            }
        };

        abstract String stringifyTree(TreeNode root, boolean direct, StringJoiner joiner);

        Extractors getExtractorByOrder(boolean direct) {
            return direct ? Extractors.DIRECT : Extractors.REVERSED;
        }

        interface NodeExtractor {
            TreeNode first(TreeNode root);

            TreeNode second(TreeNode root);
        }

        enum Extractors implements NodeExtractor {
            DIRECT {
                @Override
                public TreeNode first(TreeNode root) {
                    return root.left;
                }

                @Override
                public TreeNode second(TreeNode root) {
                    return root.right;
                }
            },
            REVERSED {
                @Override
                public TreeNode first(TreeNode root) {
                    return root.right;
                }

                @Override
                public TreeNode second(TreeNode root) {
                    return root.left;
                }
            }
        }
    }
}
