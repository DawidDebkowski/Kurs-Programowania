#include<iostream>
#include<list>
#include<string>

int main(int argc, char* argv[]) {

	//Tworznie listy elementow typu string
    std::list<std::string> *nazwiska = new std::list<std::string>();
	// Wstawianie elementow
    nazwiska->push_back("Nowak"); nazwiska->push_back("Kowalski"); 
    nazwiska->push_back("Bielecki"); nazwiska->push_back("Adamski");
    nazwiska->push_back("Kowalski");

	// Przechodznie po elementach za pomoca iteratra
  std::cout << "Prechodzenie po elementach" << std::endl;
  std::list<std::string>::iterator it;
  for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
    std::cout << " - " << *it << std::endl;
    
	// Sortowanie listy
  std::cout << "Lista posortowana" << std::endl;
    nazwiska->sort(); 
    for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
      std::cout << " + " << *it << std::endl;
    
	// Odwracanie listy
    std::cout << "Lista odwrocona" << std::endl;
    nazwiska->reverse(); 
    for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
      std::cout << " - " << *it << std::endl;
      
	// Usuwanie elementow z listy
   std::cout << "Po usunieciu Kowalskiego" << std::endl;
    nazwiska->remove("Kowalski"); 
    for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
      std::cout << " + " << *it << std::endl;
    delete(nazwiska);
}
