#include "BinaryTree.h"

// Konstruktor drzewa binarnego
template <typename T>
BinaryTree<T>::BinaryTree() : rootNode(nullptr) {}

// Destruktor drzewa binarnego
template <typename T>
BinaryTree<T>::~BinaryTree() {
    deleteSubtree(rootNode);
}

// Konstruktor drzewa binarnego z korzeniem
template <typename T>
BinaryTree<T>::BinaryTree(T rootKey) {
    rootNode = nullptr;
    insert(rootKey);
}

// Prywatna metoda wyszukiwania węzła
template <typename T>
Node<T>* BinaryTree<T>::search(Node<T>* startNode, T key) {
    if (!startNode || startNode->key == key) {
        return startNode;
    }

    if (key < startNode->key) {
        return search(startNode->left, key);
    } else {
        return search(startNode->right, key);
    }
}

// Prywatna metoda wstawiania węzła
template <typename T>
void BinaryTree<T>::insert(Node<T>* node) {
    Node<T>* parent = nullptr;
    Node<T>* searchNode = rootNode;

    while (searchNode) {
        parent = searchNode;
        if (node->key < searchNode->key) {
            searchNode = searchNode->left;
        } else {
            searchNode = searchNode->right;
        }
    }

    node->parent = parent;

    if (!parent) {
        setRoot(node);
    } else if (node->key < parent->key) {
        parent->left = node;
    } else {
        parent->right = node;
    }
}

// Prywatna metoda usuwania węzła
template <typename T>
Node<T>* BinaryTree<T>::deleteNode(Node<T>* node) {
    if (!node) return nullptr;

    Node<T>* successor = nullptr;

    if (!node->left || !node->right) {
        successor = node;
    } else {
        successor = treeSuccessor(node);
    }

    Node<T>* child = nullptr;
    if (successor->left) {
        child = successor->left;
    } else {
        child = successor->right;
    }

    if (child) {
        child->parent = successor->parent;
    }

    if (!successor->parent) {
        setRoot(child);
    } else if (successor == successor->parent->left) {
        successor->parent->left = child;
    } else {
        successor->parent->right = child;
    }

    if (successor != node) {
        node->key = successor->key;
    }

    delete successor; // Usuwanie węzła z pamięci

    return node;
}

// Prywatna metoda wyszukiwania następcy węzła
template <typename T>
Node<T>* BinaryTree<T>::treeSuccessor(Node<T>* node) {
    if (node->right) {
        return treeMinimum(node->right);
    }

    Node<T>* parent = node->parent;
    while (parent && node == parent->right) {
        node = parent;
        parent = parent->parent;
    }
    return parent;
}

// Prywatna metoda wyszukiwania minimalnego węzła
template <typename T>
Node<T>* BinaryTree<T>::treeMinimum(Node<T>* node) {
    while (node->left) {
        node = node->left;
    }
    return node;
}

// Prywatna metoda ustawiania korzenia
template <typename T>
void BinaryTree<T>::setRoot(Node<T>* newRoot) {
    rootNode = newRoot;
}

// Prywatna metoda konwersji drzewa do stringa
template <typename T>
std::string BinaryTree<T>::toS(Node<T>* node) const {
    if (node) {
        return "(" + std::to_string(node->key) + ":" + toS(node->left) + ":" + toS(node->right) + ")";
    }
    return "()";
}

// Prywatna metoda usuwania poddrzewa
template <typename T>
void BinaryTree<T>::deleteSubtree(Node<T>* node) {
    if (node) {
        deleteSubtree(node->left);
        deleteSubtree(node->right);
        delete node;
    }
}

// Publiczna metoda wyszukiwania węzła
template <typename T>
bool BinaryTree<T>::search(T key) {
    return search(rootNode, key) != nullptr;
}

// Publiczna metoda wstawiania węzła
template <typename T>
void BinaryTree<T>::insert(T key) {
    Node<T>* node = new Node<T>(key);
    insert(node);
}

// Publiczna metoda usuwania węzła
template <typename T>
void BinaryTree<T>::deleteKey(T key) {
    Node<T>* node = search(rootNode, key);
    if (node) {
        Node<T>* deletedNode = deleteNode(node);
        delete deletedNode; // Usuwanie węzła z pamięci
    }
}

// Publiczna metoda rysowania drzewa
template <typename T>
std::string BinaryTree<T>::draw() const {
    return toS(rootNode);
}