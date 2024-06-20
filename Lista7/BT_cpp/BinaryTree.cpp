#include "BinaryTree.h"

/**
 * Tworzy pusty obiekt klasy drzewa binarnego.
 */
template <typename T>
BinaryTree<T>::BinaryTree() {
    rootNode = nullptr;
}

/**
 * Destruktor. Usuwa wszystkie węzły z drzewa.
 */
template <typename T>
BinaryTree<T>::~BinaryTree() {
    deleteSubtree(rootNode);
}

/**
 * Tworzy drzewo binarne z korzeniem o podanej wartości.
 */
template <typename T>
BinaryTree<T>::BinaryTree(T rootKey) {
    rootNode = nullptr;
    insert(rootKey);
}

/**
 * Wyszukuje węzeł o wskazanym kluczu zaczynając od startNode
 *
 * @param startNode obiekt węzła do rozpoczęcia wyszukiwania
 * @param key       klucz szukanego węzła
 * @return obiekt węzła
 */
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

/**
 * Dodaje wskazany węzeł do drzewa
 *
 * @param node obiekt węzła do dodania, musi mieć klucz
 */
template <typename T>
void BinaryTree<T>::insert(Node<T>* node) {
    Node<T>* parent = nullptr;
    Node<T>* searchNode = rootNode;
    // cout << node->key;

    while (searchNode) {
        parent = searchNode;
        // cout << "Teraz: " << node->key << "Rodzic: " << (searchNode ==
        // nullptr) << " ";
        if (node->key < searchNode->key) {
            searchNode = searchNode->left;
        } else {
            searchNode = searchNode->right;
        }
    }
    // cout << "\n";
    node->parent = parent;

    if (!parent) {
        rootNode = node;
    } else if (node->key < parent->key) {
        parent->left = node;
    } else {
        parent->right = node;
    }
    // cout << node->key;
}

/**
 * Usuwa wskazany węzeł
 *
 * @param node węzeł do usunięcia
 * @return usunięty węzeł
 */
template <typename T>
void BinaryTree<T>::deleteNode(Node<T>* node) {
    if (!node) return;

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
        rootNode = child;
    } else if (successor == successor->parent->left) {
        successor->parent->left = child;
    } else {
        successor->parent->right = child;
    }

    if (successor != node) {
        node->key = successor->key;
    }

    delete successor;  // Usuwanie węzła z pamięci
}

// podaje następnika wybranego węzła
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

// znajduje najmniejszy(kluczem) węzeł w poddrzewie
template <typename T>
Node<T>* BinaryTree<T>::treeMinimum(Node<T>* node) {
    while (node->left) {
        node = node->left;
    }
    return node;
}

template <typename T>
std::string BinaryTree<T>::to_string(std::string s) {
    return s;
}

template <typename T>
std::string BinaryTree<T>::to_string(int s) {
    return std::to_string(s);
}

template <typename T>
std::string BinaryTree<T>::to_string(double s) {
    return std::to_string(s);
}

// Prywatna metoda konwersji drzewa do stringa
template <typename T>
std::string BinaryTree<T>::toS(Node<T>* node) {
    if (node) {
        return "(" + to_string(node->key) + ":" + toS(node->left) + ":" +
               toS(node->right) + ")";
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

/**
 * Wyszukuje czy klucz jest w drzewie zaczynając od korzenia
 * Prawda - klucz
 *
 * @param key klucz
 * @return Prawda/Fałsz jeżeli klucz jest/nie jest w drzewie
 */
template <typename T>
bool BinaryTree<T>::search(T key) {
    return search(rootNode, key) != nullptr;
}

/**
 * Dodaje węzeł o wskazanym kluczu do drzewa
 *
 * @param key klucz nowego węzła
 */
template <typename T>
void BinaryTree<T>::insert(T key) {
    Node<T>* node = new Node<T>(key);
    insert(node);
}

/**
 * Usuwa wskazany klucz drzewa.
 * Jeżeli jest wiele węzłów o tym samym kluczu usuwa ten najgłębiej w drzewie.
 *
 * @param key klucz do usunięcia
 */
template <typename T>
void BinaryTree<T>::deleteKey(T key) {
    Node<T>* node = search(rootNode, key);
    deleteNode(node);
}

/**
 * Zwraca drzewo w formacie (klucz : lewe_poddrzewo : prawe_poddrzewo)
 *
 * @return drzewo jako napis
 */
template <typename T>
std::string BinaryTree<T>::draw() {
    return toS(rootNode);
}