#include <iostream>
#include <string>
#include "LiczbyPierwsze.hpp"

using namespace std;

int main(int argc, char *argv[])
{
    int n; //no nie dziala
    LiczbyPierwsze *lp;
    try
    {
        if(argc < 2)
            throw "Brak danych!";
        n = stoi(argv[1]);
        lp = new LiczbyPierwsze(n); //to musi byc pod try-catch
    }
    catch(char const* e)
    {
        printf("%s", e);
        return -1;
    }
    catch(const invalid_argument e)
    {
        printf("%s - Bledny Zakres!", argv[1]);
        return -1;
    }

    for(int i=2;i<argc;i++)
    {
        printf("%s - ", argv[i]);
        try
        {
            printf("%d", lp->liczba(stoi(argv[i])));
        }
        catch(char const* e)
        {
            printf("%s", e);
        }
        catch(invalid_argument e)
        {
            printf("bledna dana");
        }
        printf("\n");
    }
}
