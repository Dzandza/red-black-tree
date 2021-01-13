package algorithms.red_black_tree;

public class Utility {
    public static void preorderPrint(Node node) {
        if (node != null && node.getValue() != null) {
            System.out.print(node.getValue() + " ");
            preorderPrint(node.getLeftChild());
            preorderPrint(node.getRightChild());
        }
    }

    public static void inorderPrint(Node node) {
        if (node != null && node.getValue() != null) {
            inorderPrint(node.getLeftChild());
            System.out.print(node.getValue() + " ");
            inorderPrint(node.getRightChild());
        }
    }

    public static void postorderPrint(Node node) {
        if (node != null && node.getValue() != null) {
            postorderPrint(node.getLeftChild());
            postorderPrint(node.getRightChild());
            System.out.print(node.getValue() + " ");
        }
    }
}
