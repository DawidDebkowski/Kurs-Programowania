#include <iostream>
#include <string>
#include "WierszTrojkataPascala.h"

using namespace std;

int main(int argc, char** argv)
{
    int n;
    try 
    {
        if(argc < 0)
            throw "brak argumentow";
        
        n = stoi(argv[1]);

        if(n < 0)
            throw "zakres nie moze byc ujemny";
    }
    catch(const char* e) 
    {
        cout << e;
        return -1;
    }
    catch(const invalid_argument e)
    {
        cout << "To nie jest liczba";
        return -1;
    }

    WierszTrojkataPascala wsp = WierszTrojkataPascala(n);
    wsp.obliczWiersz();

    for(int i=2;i<argc;i++)
    {
        cout << argv[i] << " - ";
        try 
        {
            cout << wsp.wezElement(stoi(argv[i])); 
        }
        catch(const char* e)
        {
            cout << e;
        }
        catch(const invalid_argument e)
        {
            cout << "to nie jest liczba";
        }
        cout << endl;
    }
    
}