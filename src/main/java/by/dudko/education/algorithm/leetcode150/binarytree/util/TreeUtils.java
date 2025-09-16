package by.dudko.education.algorithm.leetcode150.binarytree.util;

import by.dudko.education.algorithm.leetcode150.binarytree.TreeNode;

import java.util.*;

public final class TreeUtils {
    private TreeUtils() {
    }

    public static TreeNode buildBinaryTree(Collection<Integer> collection) {
        if (collection == null) {
            return null;
        }
        int[] values = convertCollectionToArray(collection);
        return buildBinaryTree(values);
    }

    public static TreeNode buildBinaryTree(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        for (int i = 1; i < values.length; i++) {
            insertElementIntoBinaryTree(root, values[i]);
        }
        return root;
    }

    private static int[] convertCollectionToArray(Collection<Integer> collection) {
        int[] values = new int[collection.size()];
        int index = 0;
        for (Integer elem : collection) {
            if (elem != null) {
                values[index++] = elem;
            }
        }
        return values;
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

    public static TreeNode buildTreeFromLayeredTraversal(Collection<Integer> collection) {
        if (collection == null) {
            return null;
        }
        return buildTreeFromLayeredTraversal(collection.toArray(Integer[]::new));
    }

    public static TreeNode buildTreeFromLayeredTraversal(Integer... values) {
        if (values == null || values.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> toVisit = new ArrayDeque<>();
        toVisit.offer(root);

        for (int i = 1; i < values.length; ) {
            int len = toVisit.size();
            for (int j = 0; j < len; j++) {
                TreeNode node = toVisit.remove();
                int switcher = 0;
                for (int k = 0; k < 2; k++) {
                    if (values[i] != null) {
                        if (switcher == 0) {
                            node.left = new TreeNode(values[i]);
                            toVisit.offer(node.left);
                        } else {
                            node.right = new TreeNode(values[i]);
                            toVisit.offer(node.right);
                        }
                    }
                    switcher++;
                    i++;
                }
            }
        }
        return root;
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
                Extractors extractor = getExtractorByOrder(direct);
                Queue<TreeNode> queue = new LinkedList<>();
                queue.offer(root);

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        TreeNode node = queue.remove();
                        if (node != null) {
                            joiner.add(node.toString());
                            queue.offer(extractor.first(node));
                            queue.offer(extractor.second(node));
                        } else {
                            joiner.add("null");
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
