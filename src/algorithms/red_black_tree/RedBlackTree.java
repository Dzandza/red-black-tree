package algorithms.red_black_tree;

public class RedBlackTree<Type extends Comparable> {
    private Node<Type> root;


    private void print(Node<Type> currentNode, StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        if (currentNode != null) {
            buffer.append(currentNode.getValue() + " " + currentNode.getColor() + "\n");
            if (!currentNode.isLeaf()) {
                print(currentNode.getRightChild(), buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                print(currentNode.getLeftChild(), buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        } else {
            buffer.append("\n");
        }

    }

    private Node<Type> findNodeByValue(Type value) {
        if (root == null) return null;
        Node<Type> currentNode = root;

        while (currentNode != null) {
            if (value.compareTo(currentNode.getValue()) < 0) {
                currentNode = currentNode.getLeftChild();
            }
            else if(value.compareTo(currentNode.getValue()) > 0) {
                currentNode = currentNode.getRightChild();
            }
            else return currentNode;
        }
        return currentNode;
    }

    private void rotateLeft(Node<Type> node) {
        if(node == null) throw new IllegalArgumentException();
        else if(node.isLeaf() || !node.hasRightChild()) return;

        Node<Type> rightChild = node.getRightChild();
        node.setRightChild(rightChild.getLeftChild());

        if (rightChild.getLeftChild() != null) rightChild.getLeftChild().setParent(node);
        rightChild.setParent(node.getParent());

        if(node.getParent() == null) root = rightChild;
        else if(node == node.getParent().getLeftChild()) node.getParent().setLeftChild(rightChild);
        else node.getParent().setRightChild(rightChild);

        rightChild.setLeftChild(node);
        node.setParent(rightChild);

    }

    private void rotateRight(Node<Type> node) {
        if(node == null) throw new IllegalArgumentException();
        else if(node.isLeaf() || node.hasLeftChild()) return;

        Node<Type> leftChild = node.getLeftChild();
        node.setLeftChild(leftChild.getRightChild());

        if (leftChild.getRightChild() != null) leftChild.getRightChild().setParent(node);
        leftChild.setParent(node.getParent());

        if(node.getParent() == null) root = leftChild;
        else if(node == node.getParent().getRightChild()) node.getParent().setRightChild(leftChild);
        else node.getParent().setLeftChild(leftChild);

        leftChild.setRightChild(node);
        node.setParent(leftChild);
    }

    private void fixTree(Node<Type> newNode) {
        while (!newNode.isRoot() && newNode.getParent().getColor() == Node.Color.RED) {
            if(newNode.getParent() == newNode.getParent().getParent().getLeftChild()) {
                Node<Type> newNodeUncle = newNode.getParent().getParent().getRightChild();
                if(newNodeUncle != null && newNodeUncle.getColor() == Node.Color.RED) {
                    newNode.getParent().setColor(Node.Color.BLACK);
                    newNodeUncle.setColor(Node.Color.BLACK);
                    newNode.getParent().getParent().setColor(Node.Color.RED);
                    newNode = newNode.getParent().getParent();
                }
                else {
                    if(newNode == newNode.getParent().getRightChild()) {
                        newNode = newNode.getParent();
                        rotateLeft(newNode);
                    }

                    newNode.getParent().setColor(Node.Color.BLACK);
                    newNode.getParent().getParent().setColor(Node.Color.RED);
                    rotateRight(newNode.getParent().getParent());
                }
            }
            else if(newNode.getParent() == newNode.getParent().getParent().getRightChild()) {
                Node<Type> newNodeUncle = newNode.getParent().getParent().getLeftChild();
                if(newNodeUncle != null && newNodeUncle.getColor() == Node.Color.RED) {
                    newNode.getParent().setColor(Node.Color.BLACK);
                    newNodeUncle.setColor(Node.Color.BLACK);
                    newNode.getParent().getParent().setColor(Node.Color.RED);
                    newNode = newNode.getParent().getParent();
                }
                else {
                    if(newNode == newNode.getParent().getLeftChild()) {
                        newNode = newNode.getParent();
                        rotateRight(newNode);
                    }

                    newNode.getParent().setColor(Node.Color.BLACK);
                    newNode.getParent().getParent().setColor(Node.Color.RED);
                    rotateLeft(newNode.getParent().getParent());
                }
            }
        }
        root.setColor(Node.Color.BLACK);
    }

    public RedBlackTree() {
        this.root = null;
    }

    public void insert(Type value) {
        Node<Type> newNodeParent = null;
        Node<Type> currentNode = root;
        while(currentNode != null) {
            newNodeParent = currentNode;
            if(value.compareTo(currentNode.getValue()) < 0) currentNode = currentNode.getLeftChild();
            else currentNode = currentNode.getRightChild();
        }

        Node<Type> newNode = new Node<>(value, Node.Color.RED, null, null, newNodeParent);

        if(newNodeParent == null) root = newNode;
        else if(newNode.getValue().compareTo(newNodeParent.getValue()) < 0) newNodeParent.setLeftChild(newNode);
        else newNodeParent.setRightChild(newNode);

        fixTree(newNode);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        print(root, buffer, "", "");
        return buffer.toString();
    }

}
