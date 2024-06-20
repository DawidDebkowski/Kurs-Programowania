#include <iostream>

#include "BinaryTree.cpp"

using namespace std;

int main() {
    cout << "------TESTY INT------\n";
    BinaryTree<int> bt;
    bt.insert(1);
    bt.insert(2);
    bt.insert(3);
    bt.insert(5);
    cout << bt.draw() << "\n";
    for (int i = 10; i < 50; i += 20) {
        bt.insert(i / 2);
    }
    cout << bt.draw() << "\n";
    bt.deleteKey(1);
    bt.deleteKey(3);
    bt.deleteKey(2);
    bt.deleteKey(5);
    cout << bt.draw() << "\n";
    cout << "Szukanie 1: " << bt.search(1) << " \nSzukanie 5: " << bt.search(5) << "\n";
    for (int i = 50; i > 10; i -= i / 2) {
        bt.insert(i);
    }
    cout << bt.draw() << "\n";
    cout << "------TESTY STRING------\n";
    BinaryTree<string> bs;
    bs.insert("d");
    bs.insert("c");
    bs.insert("a");
    bs.insert("b");
    cout << bs.draw() << "\n";
    for (char i = 'A'; i < 'Z'; i += 5) {
        string s(1,i);
        bs.insert(s);
    }
    cout << bs.draw() << "\n";
    bs.deleteKey("d");
    bs.deleteKey("a");
    bs.deleteKey("c");
    bs.deleteKey("E");
    cout << bs.draw() << "\n";
    cout << "Szukanie 1: " << bs.search("a") << " \nSzukanie 5: " << bs.search("A") << "\n";
    cout << bs.draw() << "\n";
    for (int i = 'Z'; i >= 'A'; i -= i / 2) {
        string s(1,i);
        bs.insert(s);
    }
    cout << bs.draw() << "\n";
}