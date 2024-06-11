public class Server<T extends Comparable<T>> {
    private BinaryTree<T> tree;

    public Server(T rootKey) {
        tree = new BinaryTree<T>(rootKey);
    }

    public Server() {
        tree = new BinaryTree<T>();
    }

    /**
     * Wyszukuje czy podana wartość jest w drzewie.
     * @param key klucz do wyszukania
     * @return Prawa/Fałsz - Prawda jeżeli podana wartość jest w drzewie
     */
    public boolean search(T key) {
        return tree.search(key) != null;
    }

    /**
     * Dodaje węzeł o wskazanym kluczu do drzewa
     * 
     * @param key klucz nowego węzła
     * @return wygląd drzewa po zmianie
     */
    public String insert(T key) {
        tree.insert(key);
        return tree.draw();
    }

    /**
     * Usuwa węzeł o wskazanym kluczu z drzewa
     * 
     * @param key klucz węzła do usunięcia
     * @return wygląd drzewa po zmianie
     */
    public String delete(T key) {
        tree.delete(new Node<T>(key));
        return tree.draw();
    }
}
