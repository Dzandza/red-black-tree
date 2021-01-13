package algorithms.red_black_tree;

public class RedBlackTree<Type extends Comparable> {
    private Node<Type> sentinel;
    private Node<Type> root;


    private Node<Type> treeMinimum(Node<Type> node) {
        while (node.getLeftChild().getValue() != null) {
            node = node.getLeftChild();
        }
        return node;
    }


    private void print(Node<Type> currentNode, StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        if (currentNode.getValue() != null) {
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
        Node<Type> currentNode = root;
        while (currentNode != null && currentNode.getValue() != null) {
            if (value.compareTo(currentNode.getValue()) < 0) {
                currentNode = currentNode.getLeftChild();
            } else if (value.compareTo(currentNode.getValue()) > 0) {
                currentNode = currentNode.getRightChild();
            } else return currentNode;
        }
        return currentNode;
    }

    private void transplant(Node<Type> node, Node<Type> replacementNode) {
        if (node.isRoot()) {
            root = replacementNode;
            sentinel.setRightChild(replacementNode);
            sentinel.setLeftChild(replacementNode);
        }
        else if (node == node.getParent().getLeftChild()) node.getParent().setLeftChild(replacementNode);
        else node.getParent().setRightChild(replacementNode);

        replacementNode.setParent(node.getParent());

    }

    private void rotateLeft(Node<Type> node) {
        Node<Type> rightChild = node.getRightChild();
        node.setRightChild(rightChild.getLeftChild());

        if (rightChild.getLeftChild().getValue() != null) rightChild.getLeftChild().setParent(node);
        rightChild.setParent(node.getParent());

        if (node.getParent().getValue() == null) {
            root = rightChild;
            sentinel.setLeftChild(root);
            sentinel.setRightChild(root);
        } else if (node == node.getParent().getLeftChild()) node.getParent().setLeftChild(rightChild);
        else node.getParent().setRightChild(rightChild);

        rightChild.setLeftChild(node);
        node.setParent(rightChild);

    }

    private void rotateRight(Node<Type> node) {
        Node<Type> leftChild = node.getLeftChild();
        node.setLeftChild(leftChild.getRightChild());

        if (leftChild.getRightChild().getValue() != null) leftChild.getRightChild().setParent(node);
        leftChild.setParent(node.getParent());

        if (node.getParent().getValue() == null) {
            root = leftChild;
            sentinel.setLeftChild(root);
            sentinel.setRightChild(root);
        } else if (node == node.getParent().getRightChild()) node.getParent().setRightChild(leftChild);
        else node.getParent().setLeftChild(leftChild);

        leftChild.setRightChild(node);
        node.setParent(leftChild);
    }

    private void fixAfterInsert(Node<Type> newNode) {
        while (newNode.getParent().getColor() == Node.Color.RED) {
            if (newNode.getParent() == newNode.getParent().getParent().getLeftChild()) {
                Node<Type> newNodeUncle = newNode.getParent().getParent().getRightChild();
                if (newNodeUncle.getColor() == Node.Color.RED) {
                    newNode.getParent().setColor(Node.Color.BLACK);
                    newNodeUncle.setColor(Node.Color.BLACK);
                    newNode.getParent().getParent().setColor(Node.Color.RED);
                    newNode = newNode.getParent().getParent();
                } else {
                    if (newNode == newNode.getParent().getRightChild()) {
                        newNode = newNode.getParent();
                        rotateLeft(newNode);
                    }
                    newNode.getParent().setColor(Node.Color.BLACK);
                    newNode.getParent().getParent().setColor(Node.Color.RED);
                    rotateRight(newNode.getParent().getParent());
                }
            } else {
                Node<Type> newNodeUncle = newNode.getParent().getParent().getLeftChild();
                if (newNodeUncle.getColor() == Node.Color.RED) {
                    newNode.getParent().setColor(Node.Color.BLACK);
                    newNodeUncle.setColor(Node.Color.BLACK);
                    newNode.getParent().getParent().setColor(Node.Color.RED);
                    newNode = newNode.getParent().getParent();
                } else {
                    if (newNode == newNode.getParent().getLeftChild()) {
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

    private void fixAfterDelete(Node<Type> replacementNode) {
        while(replacementNode != root && replacementNode.getColor() == Node.Color.BLACK) {
            if(replacementNode == replacementNode.getParent().getLeftChild()) {
                Node<Type> sibling = replacementNode.getParent().getRightChild();
                if(sibling.getColor() == Node.Color.RED) {
                    sibling.setColor(Node.Color.BLACK);
                    replacementNode.getParent().setColor(Node.Color.RED);
                    rotateLeft(replacementNode.getParent());
                    sibling = replacementNode.getParent().getRightChild();
                }
                if(sibling.getLeftChild().getColor() == Node.Color.BLACK && sibling.getRightChild().getColor() == Node.Color.BLACK) {
                    sibling.setColor(Node.Color.RED);
                    replacementNode = replacementNode.getParent();
                }
                else {
                    if(sibling.getRightChild().getColor() == Node.Color.BLACK) {
                        sibling.getLeftChild().setColor(Node.Color.BLACK);
                        sibling.setColor(Node.Color.RED);
                        rotateRight(sibling);
                        sibling = replacementNode.getParent().getRightChild();
                    }
                    sibling.setColor(replacementNode.getParent().getColor());
                    replacementNode.getParent().setColor(Node.Color.BLACK);
                    sibling.getRightChild().setColor(Node.Color.BLACK);
                    rotateLeft(replacementNode.getParent());
                    replacementNode = root;
                }
            } else {
                Node<Type> sibling = replacementNode.getParent().getLeftChild();
                if(sibling.getColor() == Node.Color.RED) {
                    sibling.setColor(Node.Color.BLACK);
                    replacementNode.getParent().setColor(Node.Color.RED);
                    rotateRight(replacementNode.getParent());
                    sibling = replacementNode.getParent().getLeftChild();
                }
                if(sibling.getLeftChild().getColor() == Node.Color.BLACK && sibling.getRightChild().getColor() == Node.Color.BLACK) {
                    sibling.setColor(Node.Color.RED);
                    replacementNode = replacementNode.getParent();
                }
                else {
                    if(sibling.getLeftChild().getColor() == Node.Color.BLACK) {
                        sibling.getRightChild().setColor(Node.Color.BLACK);
                        sibling.setColor(Node.Color.RED);
                        rotateLeft(sibling);
                        sibling = replacementNode.getParent().getLeftChild();
                    }
                    sibling.setColor(replacementNode.getParent().getColor());
                    replacementNode.getParent().setColor(Node.Color.BLACK);
                    sibling.getLeftChild().setColor(Node.Color.BLACK);
                    rotateRight(replacementNode.getParent());
                    replacementNode = root;
                }
            }
        }

        replacementNode.setColor(Node.Color.BLACK);
    }


    public RedBlackTree() {
        root = null;
        sentinel = new Node<>(null, Node.Color.BLACK, root, root, null);
    }

    public Node<Type> getRoot() {
        return root;
    }

    public void insert(Type value) {
        Node<Type> newNodeParent = sentinel;
        Node<Type> currentNode = root;
        while (currentNode != null && currentNode.getValue() != null) {
            newNodeParent = currentNode;
            if (value.compareTo(currentNode.getValue()) < 0) currentNode = currentNode.getLeftChild();
            else currentNode = currentNode.getRightChild();
        }

        Node<Type> newNode = new Node<>(value, Node.Color.RED, sentinel, sentinel, newNodeParent);

        if (newNodeParent.getValue() == null) {
            root = newNode;
            root.setParent(sentinel);
            sentinel.setLeftChild(root);
            sentinel.setRightChild(root);
        } else if (newNode.getValue().compareTo(newNodeParent.getValue()) < 0) newNodeParent.setLeftChild(newNode);
        else newNodeParent.setRightChild(newNode);

        fixAfterInsert(newNode);
    }

    public void delete(Type value) {
        Node<Type> node = findNodeByValue(value);
        if (node == null) throw new IllegalArgumentException("Broj ne postoji u stablu.");

        Node<Type> tempNode = node, replacementNode;
        Node.Color originalColor = tempNode.getColor();

        if (node.getLeftChild().getValue() == null) {
            replacementNode = node.getRightChild();
            transplant(node, node.getRightChild());
        } else if (node.getRightChild().getValue() == null) {
            replacementNode = node.getLeftChild();
            transplant(node, node.getLeftChild());
        } else {
            tempNode = treeMinimum(node.getRightChild());
            originalColor = tempNode.getColor();
            replacementNode = tempNode.getRightChild();
            if (tempNode.getParent() == node) {
                replacementNode.setParent(tempNode);
            } else {
                transplant(tempNode, tempNode.getRightChild());
                tempNode.setRightChild(node.getRightChild());
                tempNode.getRightChild().setParent(tempNode);
            }

            transplant(node, tempNode);
            tempNode.setLeftChild(node.getLeftChild());
            tempNode.getLeftChild().setParent(tempNode);
            tempNode.setColor(node.getColor());
        }

        if (originalColor == Node.Color.BLACK)
            fixAfterDelete(replacementNode);
        sentinel.setParent(null);
    }


    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        print(root, buffer, "", "");
        return buffer.toString();
    }

}
