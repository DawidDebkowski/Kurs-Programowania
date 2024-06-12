#include <iostream>
#include <fstream>
#include <string>

int main () {

	// Tworzenie pliku i otwieranie strumienia do zapisu
  std::ofstream myfile ("example.txt");
  if (myfile.is_open())
  {
    myfile << "This is a line.\n";
    myfile << "This is another line.\n";
    myfile.close();
  }
  else std::cout << "Unable to open file";

  return 0;
}