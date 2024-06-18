template <typename T>
class Node {
 public:
  T key;
  Node<T>* parent;
  Node<T>* left;
  Node<T>* right;
};