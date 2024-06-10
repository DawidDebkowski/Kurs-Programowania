public class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    T key;

    public Node(T key) {
        this.key = key;
    }

    public Node(T key, Node<T> parent) {
        this.parent = parent;
        this.key = key;
    }


    public void copyNode(Node<T> node) {
        key = node.key;
    }
}
