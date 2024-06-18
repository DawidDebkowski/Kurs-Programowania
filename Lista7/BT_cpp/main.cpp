#include <iostream>
#include "BinaryTree.cpp"

using namespace std;

int main() {
    BinaryTree<int> bt;
    bt.insert(1);
    bt.insert(2);
    bt.insert(3);
    bt.insert(5);
    cout << bt.draw();
    for(int i=10;i<50;i+=1) {
        cout << i;
        bt.insert(i);
        cout << i << "a";
    }
    cout << bt.draw();
    for(int i=50;i>10;i-=i/2) {
        bt.insert(i);
    }
    cout << bt.draw();
}