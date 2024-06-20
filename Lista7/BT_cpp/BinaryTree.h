#include <string>

#include "Node.h"

template <typename T>
class BinaryTree {
   private:
    Node<T>* rootNode;

    Node<T>* search(Node<T>* startNode, T key);
    void insert(Node<T>* node);
    void deleteNode(Node<T>* node);
    Node<T>* treeSuccessor(Node<T>* node);
    Node<T>* treeMinimum(Node<T>* node);
    std::string toS(Node<T>* node);
    void deleteSubtree(Node<T>* node);
    std::string to_string(std::string s);
    std::string to_string(int s);
    std::string to_string(double s);

   public:
    BinaryTree();
    ~BinaryTree();
    BinaryTree(T rootKey);

    bool search(T key);
    void insert(T key);
    void deleteKey(T key);
    std::string draw();
};
