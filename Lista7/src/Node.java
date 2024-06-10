public class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    final T key;

    public Node(T key, Node<T> parent) {
        this.key = key;
        this.parent = parent;
    }
}
