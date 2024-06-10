public class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    final T value;
    
    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }
}
