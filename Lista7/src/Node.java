/**
 * Węzeł - klasa budująca drzewo binarne.
 * Posiada wskaźniki na lewego i prawego syna oraz na ojca.
 * Posiada także pole wartości.
 */
public class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    T key;

    /**
     * Tworzy węzeł wraz z przypisaną wartością klucza
     * 
     * @param key klucz
     */
    public Node(T key) {
        this.key = key;
        left = null;
        right = null;
        parent = null;
    }

    /**
     * Tworzy węzeł z podanym kluczem i rodzicem.
     * 
     * @param key    klucz
     * @param parent rodzic
     */
    public Node(T key, Node<T> parent) {
        this(key);
        this.parent = parent;
    }

    /**
     * Kopiuje wartość innego węzła. (NIE wskaźniki na inne węzły)
     * Zalecane na wypadek implementacji większej ilości danych niż klucz.
     * 
     * @param node węzeł od którego mają być skopiowane wartości
     */
    public void copyNode(Node<T> node) {
        key = node.key;
    }
}
