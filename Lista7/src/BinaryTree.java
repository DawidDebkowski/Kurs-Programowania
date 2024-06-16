import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> rootNode = null;

    public BinaryTree() {
    }

    // TODO zmienić if () na if()
    public BinaryTree(T rootKey) {
        insert(new Node<T>(rootKey, null));
    }

    /**
     * Wyszukuje węzeł o wskazanym kluczu zaczynając od korzenia
     * 
     * @param key klucz szukanego węzła
     * @return obiekt węzła
     */
    public boolean search(T key) {
        return search(rootNode, key) != null;
    }

    /**
     * Wyszukuje węzeł o wskazanym kluczu zaczynając od startNode
     * 
     * @param startNode obiekt węzła do rozpoczęcia wyszukiwania
     * @param key       klucz szukanego węzła
     * @return obiekt węzła
     */
    public Node<T> search(Node<T> startNode, T key) {
        if (startNode == null ||
                startNode.key.compareTo(key) == 0) {
            return startNode;
        }

        if (key.compareTo(startNode.key) < 0) {
            return search(startNode.left, key);
        } else
            return search(startNode.right, key);
    }

    /**
     * Dodaje węzeł o wskazanym kluczu do drzewa
     * 
     * @param key klucz nowego węzła
     */
    public void insert(T key) {
        insert(new Node<T>(key, null));
    }

    /**
     * Dodaje wskazany węzeł do drzewa
     * 
     * @param node obiekt węzła do dodania, musi mieć klucz
     */
    private void insert(Node<T> node) {
        Node<T> parent = null;
        Node<T> searchNode = rootNode;

        // szukanie miejsca na węzeł
        while (searchNode != null) {
            parent = searchNode;
            if (node.key.compareTo(searchNode.key) < 0) {
                searchNode = searchNode.left;
            } else
                searchNode = searchNode.right;
        }

        // podpięcie węzła
        node.parent = parent;
        if (parent == null) {
            setRoot(node);
        } else if (node.key.compareTo(parent.key) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    public void delete(T key) {
        delete(search(rootNode, key));
    }

    /**
     * Usuwa wskazany węzeł
     * 
     * @param node węzeł do usunięcia
     * @return usunięty węzeł
     */
    public Node<T> delete(Node<T> node) {
        if (node == null)
            return null;

        Node<T> successor = null;
        if (node.left == null || node.right == null) {
            successor = node;
        } else
            successor = treeSuccessor(node);

        Node<T> child = null;
        if (successor.left != null) {
            child = successor.left;
        } else
            child = successor.right;

        if (child != null) {
            child.parent = successor.parent;
        }

        if (successor.parent == null) {
            setRoot(child);
        } else if (successor == successor.parent.left) {
            successor.parent.left = child;
        } else
            successor.parent.right = child;

        if (successor != node) {
            node.copyNode(successor);
        }
        return successor;
    }

    // podaje następnika wybranego węzła
    private Node<T> treeSuccessor(Node<T> node) {
        if (node.right != null)
            return treeMinimum(node.right);

        Node<T> parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    // znajduje najmniejszy(kluczem) węzeł w poddrzewie
    private Node<T> treeMinimum(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void setRoot(Node<T> newRoot) {
        rootNode = newRoot;
    }

    public String toString() {
        return toS(rootNode);
    }

    private String toS(Node<T> w) {
        if (w != null)
            return "(" + w.key + ":" + toS(w.left) + ":" + toS(w.right) + ";);";
        return "()";
    }

    public String draw() {
        return toS(rootNode);
    }

    public void printLevelOrder(Node<T> root) {
        int h = 3;
        int i;
        for (i = 1; i <= h; i++)
            printCurrentLevel(root, i);
    }

    // Print nodes at a current level
    public void printCurrentLevel(Node<T> root, int level) {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.key + " ");
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }

    public void finalTest() {
        printLevelOrder(rootNode);
    }

    public void test(Node<T> w) {
        String draw = draw();
        int depth = 0;
        int index = 1;
        if (draw.length() < 3)
            return;

        String key = "";
        for (int i = 1; i < draw.length() - 1; i++) {
            if (draw.charAt(index) == '(') {
                depth++;
            } else if (draw.charAt(index) == ')') {
                depth--;
            } else {
                while (draw.charAt(index) != ':' || draw.charAt(index) != ';') {
                    key += draw.charAt(index);
                }
            }

        }
    }

    public void arrayRepresentation(Node<T> w, int index, int depth, ArrayList<String> representation) {
        // int index = 1;
        // int depth = 0;
        int arrayIndex = index + (int) (Math.pow(2, depth));
        if (w == null) {
            representation.set(arrayIndex, null);
            return;
        }

        representation.set(arrayIndex, w.key.toString());
        arrayRepresentation(w.left, index, depth, representation);
    }
}
