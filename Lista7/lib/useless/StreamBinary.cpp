#include <iostream>
#include <fstream>


int main () {
  std::streampos begin,end;
  // Otwieranie strumienia do odczytu
  std::ifstream myfile ("mydata.bin", std::ios::binary);
  // Zwraca aktualna pozycje strumienia
  begin = myfile.tellg();
  // Ustawia pozycje na koniec pliku
  myfile.seekg (0, std::ios::end);
  end = myfile.tellg();
  myfile.close();
  std::cout << "size is: " << (end-begin) << " bytes.\n";
  return 0;
}