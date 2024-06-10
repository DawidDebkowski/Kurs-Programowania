public class BinaryTree<T extends Comparable<T>> {
    private Node<T> rootNode = null;

    public BinaryTree() {
    }

    // TODO zmienić if () na if()
    public BinaryTree(T rootKey) {
        insert(new Node<T>(rootKey, null));
    }

    public Node<T> search(Node<T> startNode, T key) {
        if (startNode == null ||
                startNode.key.compareTo(key) == 0) {
            return startNode;
        }

        if (startNode.key.compareTo(key) < 0) {
            return search(startNode.left, key);
        } else
            return search(startNode.right, key);
    }

    public void insert(Node<T> node) {
        Node<T> parent = null;
        Node<T> searchNode = rootNode;

        // szukanie miejsca na węzeł
        while (searchNode != null) {
            parent = searchNode;
            if (searchNode.key.compareTo(node.key) < 0) {
                searchNode = searchNode.left;
            } else
                searchNode = searchNode.right;
        }

        // podpięcie węzła
        node.parent = parent;
        if (parent == null) {
            rootNode = node;
        } else if (parent.key.compareTo(node.key) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    public void delete(Node<T> node) {

    }

    public void draw() {

    }
}
