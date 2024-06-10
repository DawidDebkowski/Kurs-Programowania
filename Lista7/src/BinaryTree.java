public class BinaryTree<T extends Comparable<T>> {
    private Node<T> rootNode = null;

    public BinaryTree() {
    }

    // TODO zmienić if () na if()
    public BinaryTree(T rootKey) {
        insert(new Node<T>(rootKey, null));
    }

    public Node<T> search(T key) {
        return search(rootNode, key);
    }

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

    public void insert(T key) {
        insert(new Node<T>(key, null));
    }

    public void insert(Node<T> node) {
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

    public Node<T> delete(Node<T> node) {
        if(node == null)
            return null;

        Node<T> successor = null;
        if(node.left == null || node.right == null) {
            successor = node;
        } else successor = treeSuccessor(node);
        
        Node<T> child = null;
        if(successor.left != null) {
            child = successor.left;
        } else child = successor.right;

        if(child != null) {
            child.parent = successor.parent;
        }

        if(successor.parent == null) {
            setRoot(child);
        } else if(successor == successor.parent.left) {
            successor.parent.left = child;
        } else successor.parent.right = child;

        if(successor != node) {
            node.copyNode(successor);
        }
        return successor;
    }

    private Node<T> treeSuccessor(Node<T> node) {
        if(node.right != null)
            return treeMinimum(node.right);
        
        Node<T> parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private Node<T> treeMinimum(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void setRoot(Node<T> newRoot) {
        rootNode = newRoot;
    }

    public String toString() { return toS(rootNode); }
    
    private String toS(Node<T> w) { 
      if( w!=null )
        return "("+w.key+"l:"+toS(w.left)+"p:"+toS(w.right)+");";
      return "()";
    }

    public String draw() {
        return toS(rootNode);
    }
}
