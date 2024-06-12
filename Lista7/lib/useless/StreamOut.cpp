#include <iostream>
#include <fstream>
#include <string>


int main () {
  std::string line;
  // Otwieranie strumienia do odczytu
  std::ifstream myfile ("example.txt");
  if (myfile.is_open())
  {
    while ( getline (myfile,line) )
    {
      std::cout << line << '\n';
    }
    myfile.close();
  }

  else std::cout << "Unable to open file"; 

  return 0;
}