#include <iostream>
#include "BinaryTree.cpp"

using namespace std;

int main() {
    BinaryTree<int> bt;
    bt.insert(1);
    bt.insert(2);
    cout << bt.draw();
}