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
    void setRoot(Node<T>* newRoot);
    std::string toS(Node<T>* node) const;
    void deleteSubtree(Node<T>* node);

public:
    BinaryTree();
    ~BinaryTree();
    BinaryTree(T rootKey);

    bool search(T key);
    void insert(T key);
    void deleteKey(T key);
    void hi(T test);
    std::string draw() const;
};
