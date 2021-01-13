package algorithms.red_black_tree;

public class Node<Type extends Comparable> {
    private Node<Type> leftChild, rightChild, parent;
    private Type value;
    private Color color;


    public enum Color {
        RED,
        BLACK
    }

    public Node(Type value, Color color, Node<Type> leftChild, Node<Type> rightChild, Node<Type> parent) {
        this.value = value;
        this.color = color;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }

    public Node<Type> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<Type> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<Type> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<Type> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean hasLeftChild() {
        return leftChild.getValue() != null;
    }

    public boolean hasRightChild() {
        return rightChild.getValue() != null;
    }

    public boolean hasBothChildren() {
        return rightChild.getValue() != null && leftChild.getValue() != null;
    }

    public boolean isLeaf() {
        return rightChild.getValue() == null && leftChild.getValue() == null;
    }

    public Node<Type> getParent() {
        return parent;
    }

    public void setParent(Node<Type> parent) {
        this.parent = parent;
    }

    public boolean isRoot() {
        return parent.value == null;
    }

    public Type getValue() {
        return value;
    }

    public void setValue(Type value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}