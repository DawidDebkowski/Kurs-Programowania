#pragma once

#include <iostream>

class LiczbyPierwsze
{
private:
    int* liczbyPierwsze;
    int ilePierwszych;

public:
    LiczbyPierwsze(int n) noexcept(false);
    int liczba(int m);
    bool pierwsza(int n);
    ~LiczbyPierwsze();
};