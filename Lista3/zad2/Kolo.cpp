#include "Kolo.hpp"
#include <math.h>
#include <string>
// I didnt find PI :(

Kolo::Kolo(double promien)
{
    r = promien;
}

double Kolo::obliczObwod()
{
    return r*3.14*2;
}

double Kolo::obliczPole()
{
    return r*r*3.14;
}

std::string Kolo::podajNazwe()
{
    return "Kolo";
}