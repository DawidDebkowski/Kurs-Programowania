/**
 * Drzewo binarne dla dowolnego typu danych.
 * Może: dodawać, wyszukiwać, usuwać węzły. 
 * Także narysować siebie w postaci napisu.
 */
public class BinaryTree<T extends Comparable<T>> {
    private Node<T> rootNode;

    /**
     * Tworzy pusty obiekt klasy drzewa binarnego.
     */
    public BinaryTree() {
        rootNode = null;
    }

    /**
     * Tworzy drzewo binarne z korzeniem o podanej wartości.
     */
    public BinaryTree(T rootKey) {
        insert(new Node<T>(rootKey, null));
    }

    /**
     * Wyszukuje czy klucz jest w drzewie zaczynając od korzenia.
     * Prawda - klucz jest, wpp. fałsz.
     * 
     * @param key klucz
     * @return Prawda/Fałsz jeżeli klucz jest/nie jest w drzewie
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

    /**
     * Usuwa wskazany klucz drzewa.
     * Jeżeli jest wiele węzłów o tym samym kluczu usuwa ten najgłębiej w drzewie.
     * 
     * @param key klucz do usunięcia
     */
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

    // ustawia korzeń drzewa
    private void setRoot(Node<T> newRoot) {
        rootNode = newRoot;
    }

    public String toString() {
        return toS(rootNode);
    }

    private String toS(Node<T> w) {
        if (w != null)
            return "(" + w.key + ":" + toS(w.left) + ":" + toS(w.right) + ")";
        return "()";
    }

    /**
     * Zwraca drzewo w formacie (klucz : lewe_poddrzewo : prawe_poddrzewo)
     * 
     * @return drzewo jako napis
     */
    public String draw() {
        return toS(rootNode);
    }
}
