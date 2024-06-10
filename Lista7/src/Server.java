public class Server<T extends Comparable<T>> {
    private BinaryTree<T> tree;

    public Server(T rootKey) {
        tree = new BinaryTree<T>(rootKey);
    }

    public Server() {
        tree = new BinaryTree<T>();
    }


    public void insert(BinaryTree<T> t, T key) {
        t.insert(key);
        t.draw();
    }

    public Node<T> search(BinaryTree<T> t, T key) {
        return t.search(key);
    }

    public Node<T> delete(BinaryTree<T> t, Node<T> node) {
        Node<T> temp = t.delete(node);
        t.draw();
        return temp;
    }
}
