#include "Pieciakat.hpp"
#include <math.h>
#include <string>


double Pieciakat::obliczPole()
{
    return bok*bok/4*sqrt(25+10*sqrt(5));
}

double Pieciakat::obliczObwod()
{
    return bok*5;
}

Pieciakat::Pieciakat(double bok)
{
    this->bok = bok;
}

std::string Pieciakat::podajNazwe()
{
    return "Pieciakat";
}