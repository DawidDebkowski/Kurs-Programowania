#include<iostream>
#include<string>

// Klasa element stosu (typ generyczny T)
template<typename T>
class ElemStosu {
  public:
    T elem;
    ElemStosu<T>* nast;
    ElemStosu(T elem, ElemStosu<T>* nast)
    {
      this->elem = elem;
      this->nast = nast;
    }
};

// Klasa Stosu (typ generyczny T)
template<typename T>
class Stos {
  private:
    ElemStosu<T>* wierzch;
  public:
    Stos() { wierzch = NULL; }
    bool empty() { return wierzch==NULL; }
    void push(T elem) { wierzch = new ElemStosu<T>(elem,wierzch); }
    T pop() {
      if( empty() ) throw (std::string)"PustyStos!";
      T wynik = wierzch->elem;
      wierzch = wierzch->nast;
      return wynik;
    }
};

int main(int argc, char* argv[]) {
  // Tworzenie dwoch stosow
  Stos<int> a;
  Stos<std::string> b;

  a.push(2); a.push(3);
  try {
    std::cout << "Wypisywanie elementow stosu typu int:" << std::endl;
    std::cout << a.pop() << " " << a.pop() << std::endl;
    std::cout << a.pop() << " " << a.pop() << std::endl;
  }
  catch(std::string e) {
    std::cout << e << std::endl;
  }
  b.push("Maciek"); b.push("Ala");
  try {
    std::cout << "Wypisywanie elementow stosu typu string:" << std::endl;
    while( !b.empty() ) 
      std::cout << b.pop() << std::endl;
  }
  catch(std::string e) {
    std::cout << e << std::endl;
  }
}
