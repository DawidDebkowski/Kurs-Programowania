public class BinaryTree<T extends Comparable<T>> {
    private Node<T> rootNode = null;

    public BinaryTree() {

    }

    public BinaryTree(T rootValue) {
        rootNode = new Node<T>(rootValue, null);
    }

    public T search() {
        return null;
    }

    public void insert(Node<T> node) {
        Node<T> parent = null;
        Node<T> searchNode = rootNode;
        
        //szukanie miejsca na węzeł
        while (searchNode != null) {
            parent = searchNode;
            if (searchNode.value.compareTo(node.value) < 0) {
                searchNode = searchNode.left;
            } else
                searchNode = searchNode.right;
        }

        //podpięcie węzła
        node.parent = parent;
        if (parent == null) {
            rootNode = node;
        } else if (parent.value.compareTo(node.value) < 0) {
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
