#include "Szesciakat.hpp"
#include <math.h>
#include <string>

double Szesciakat::obliczPole()
{
    return 3*bok*bok*sqrt(3)/2;
}

double Szesciakat::obliczObwod()
{
    return bok*6;
}

Szesciakat::Szesciakat(double bok)
{
    this->bok = bok;
}

std::string Szesciakat::podajNazwe()
{
    return "Szesciokat";
}
