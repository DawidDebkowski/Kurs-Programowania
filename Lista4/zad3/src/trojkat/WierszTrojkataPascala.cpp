#include <iostream>
#include "WierszTrojkataPascala.h"

WierszTrojkataPascala::WierszTrojkataPascala(int n)
{
    numerWiersza = n;
}

void WierszTrojkataPascala::obliczWiersz()
{
    wiersz = new int[numerWiersza+1];

    int choose = 1; // n choose 0
    for(int k=0;k<=numerWiersza;k++) 
    {
        wiersz[k] = choose;
        choose = choose * (numerWiersza-k) / (k+1);
    }
}  

int WierszTrojkataPascala::wezElement(int m)
{
    if(m > numerWiersza)
        throw "za duzy argument";
    if(m < 0)
        throw "argument nie moze byc ujemny";

    return wiersz[m];
}

WierszTrojkataPascala::~WierszTrojkataPascala()
{
    delete[] wiersz;
}
