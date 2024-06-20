#include <string>

#include "Node.h"

template <typename T>
class BinaryTree {
   private:
    Node<T>* rootNode;

    Node<T>* search(Node<T>* startNode, T key);
    void insert(Node<T>* node);
    Node<T>* deleteNode(Node<T>* node);
    Node<T>* treeSuccessor(Node<T>* node);
    Node<T>* treeMinimum(Node<T>* node);
    std::string toS(Node<T>* node);
    void deleteSubtree(Node<T>* node);

   public:
    BinaryTree();
    ~BinaryTree();
    BinaryTree(T rootKey);

    bool search(T key);
    void insert(T key);
    void deleteKey(T key);
    std::string draw();
};
