#include <iostream>
#include <string>

#include "Node.h"

template <typename T>
class BinaryTree {
 private:
  Node<T>* rootNode;

  Node<T>* search(Node<T>* startNode, T key) {
    if (!startNode || startNode->key == key) {
      return startNode;
    }

    if (key < startNode->key) {
      return search(startNode->left, key);
    } else {
      return search(startNode->right, key);
    }
  }

  void insert(Node<T>* node) {
    Node<T>* parent = nullptr;
    Node<T>* searchNode = rootNode;

    // szuka miejsca na na node
    while (searchNode) {
      parent = searchNode;
      if (node->key < searchNode->key) {
        searchNode = searchNode->left;
      } else {
        searchNode = searchNode->right;
      }
    }

    // podpina node
    node->parent = parent;

    if (!parent) {
      setRoot(node);
    } else if (node->key < parent->key) {
      parent->left = node;
    } else {
      parent->right = node;
    }
  }

  Node<T>* deleteNode(Node<T>* node) {
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

    delete successor;
    return successor;
  }

  Node<T>* treeSuccessor(Node<T>* node) {
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

  Node<T>* treeMinimum(Node<T>* node) {
    while (node->left) {
      node = node->left;
    }
    return node;
  }

  void setRoot(Node<T>* newRoot) { rootNode = newRoot; }

  std::string toS(Node<T>* node) const {
    if (node) {
      return "(" + std::to_string(node->key) + ":" + toS(node->left) + ":" +
             toS(node->right) + ")";
    }
    return "()";
  }

  void deleteSubtree(Node<T>* node) {
    if (node) {
      deleteSubtree(node->left);
      deleteSubtree(node->right);
      delete node;
    }
  }

 public:
  BinaryTree() : rootNode(nullptr) {}

  ~BinaryTree() { deleteSubtree(rootNode); }

  BinaryTree(T rootKey) {
    rootNode = nullptr;
    insert(rootKey);
  }

  bool search(T key) { return search(rootNode, key) != nullptr; }

  void insert(T key) {
    Node<T>* node = new Node(key);
    insert(node);
  }

  void deleteKey(T key) {
    Node<T>* node = search(rootNode, key);
    if (node) {
      Node<T>* deletedNode = deleteNode(node);
      delete deletedNode;  // Usuwanie węzła z pamięci
    }
  }

  std::string draw() const { return toS(rootNode); }
};

int main() {
  BinaryTree<int> tree;
  tree.insert(10);
  tree.insert(5);
  tree.insert(20);

  std::cout << "Tree: " << tree.draw() << std::endl;

  tree.deleteKey(10);

  std::cout << "Tree after deletion: " << tree.draw() << std::endl;

  return 0;
}
