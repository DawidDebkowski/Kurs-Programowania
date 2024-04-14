#include "LiczbyPierwsze.hpp"

LiczbyPierwsze::LiczbyPierwsze(int n) noexcept(false)
{
    if(n <= 0)
        throw "Bledny zakres!";

    liczbyPierwsze = new int[n];
    ilePierwszych = 0;
    for(int i=2;i<n;i++)
    {
        if(pierwsza(i))
        {
            liczbyPierwsze[ilePierwszych] = i;
            ilePierwszych++;
        }
    }
}

int LiczbyPierwsze::liczba(int m) noexcept(false)
{
    if(m < ilePierwszych && m >= 0)
    {
        return liczbyPierwsze[m];
    }
    else 
    {
        throw "poza zakresem";
    }
}

bool LiczbyPierwsze::pierwsza(int n)
{
    for(int i=2;i*i<=n;i++)
    {
        if(n % i == 0)
        {
            return false;
        }
    }
    return true;
}

LiczbyPierwsze::~LiczbyPierwsze()
{
    delete[] liczbyPierwsze;
}