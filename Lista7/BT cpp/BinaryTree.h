#include <string.h>

#include <iostream>

#include "Node.h"

template <typename T>
class BinaryTree {
 public:
  void insert(T key);
  void deleteKey(T key);
  bool search(T key);
  std::string draw();

 private:
  Node<T>* search(Node<T>* startNode, T key);
  void insert(Node<T>* node);
  Node<T>* deleteNode(Node<T>* node);
  std::string toString(Node<T>* node);
};

template <typename T>
void BinaryTree<T>::insert(T key) {}

template <typename T>
void BinaryTree<T>::deleteKey(T key) {}

template <typename T>
bool BinaryTree<T>::search(T key) {
  return false;
}

template <typename T>
std::string BinaryTree<T>::draw() {
  return std::string();
}

template <typename T>
Node<T>* BinaryTree<T>::search(Node<T>* startNode, T key) {
    if(startNode == nullptr || startNode->key == key) {
        return startNode;
    }

    if(startNode.key < key)
    return Node<T>();
}

template <typename T>
void BinaryTree<T>::insert(Node<T>* node) {}

template <typename T>
Node<T>* BinaryTree<T>::deleteNode(Node<T>* node) {
  return Node<T>();
}

template <typename T>
std::string BinaryTree<T>::toString(Node<T>* node) {
  return std::string();
}
