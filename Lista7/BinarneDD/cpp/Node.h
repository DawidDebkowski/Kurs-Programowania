template <typename T>
class Node {
 public:
  T key;
  Node<T>* parent;
  Node<T>* left;
  Node<T>* right;
  Node(T key);
  ~Node();
};

template <typename T>
Node<T>::Node(T key) {this->key = key;this-> left = nullptr; this->right = nullptr;};

template <typename T>
Node<T>::~Node() {};
