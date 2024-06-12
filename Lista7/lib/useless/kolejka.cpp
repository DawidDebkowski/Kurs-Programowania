#include <queue>
#include <iostream>
#include <conio.h>

int main()
{
	// Tworznie kolejki
    std::queue < int > kolejkaLiczb;
    
    std::cout << "Podaj liczbe: ";
    int liczba;
    std::cin >> liczba;
    
	// Wstawianie do kolejki
    kolejkaLiczb.push( liczba );
    kolejkaLiczb.push( 222 );
    kolejkaLiczb.push( 555 );
    
	std::cout << "W kolejce znajduje sie " << kolejkaLiczb.size() << " liczb." << std::endl;

    std::cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << std::endl;
    kolejkaLiczb.front() *= 2;
    
    std::cout << "Zmodyfikowalem pierwsza liczbe w kolejce" << std::endl;
    std::cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << std::endl;
    
    kolejkaLiczb.front() = 1234;
    std::cout << "Zmodyfikowalem pierwsza liczbe w kolejce" << std::endl;
    
    std::cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << std::endl;

	std::cout << "Ostatnia liczba w kolejce to: " << kolejkaLiczb.back() << std::endl;

	std::cout << "Usuwam liczbe z kolejki "  << std::endl;
	kolejkaLiczb.pop();

	std::cout << "W kolejce znajduje sie " << kolejkaLiczb.size() << " liczb." << std::endl;
	    
	std::cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << std::endl;
    
    
    return 0;
}