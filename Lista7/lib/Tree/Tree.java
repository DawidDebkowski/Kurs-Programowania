
// Klasa elemenu drzewa
class TreeElem<T extends Comparable<T>> {
  final T elem;
  TreeElem<T> left;
  TreeElem<T> right;

  TreeElem(T elem) {
    this.elem = elem;
    left = null;
    right = null;
  }

  public String toString() {
    return elem.toString();
  }
}

// Klasa do obslugi drzewa. Typ generyczny T musi implementowac Comparable,
// zeby mozna bylo porownac elementy do wstawienia do drzewa.
public class Tree<T extends Comparable<T>> {
  private TreeElem<T> root;

  public Tree() {
    root = null;
  }

  public void insert(T elem) {
    root = ins(elem, root);
  }

  private TreeElem<T> ins(T elem, TreeElem<T> w) {
    if (w == null)
      return new TreeElem<T>(elem);
    if (elem.compareTo(w.elem) < 0)
      w.left = ins(elem, w.left);
    else if (elem.compareTo(w.elem) > 0)
      w.right = ins(elem, w.right);
    return w;
  }

  public boolean isElement(T elem) {
    return isElem(elem, root);
  }

  private boolean isElem(T elem, TreeElem<T> w) {
    if (w == null)
      return false;
    if (elem.compareTo(w.elem) == 0)
      return true;
    if (elem.compareTo(w.elem) < 0)
      return isElem(elem, w.left);
    else
      return isElem(elem, w.right);
  }

  public String toString() {
    return toS(root);
  }

  private String toS(TreeElem<T> w) {
    if (w != null)
      return "(" + w.elem + ":" + toS(w.left) + ":" + toS(w.right) + ")";
    return "()";
  }
}
