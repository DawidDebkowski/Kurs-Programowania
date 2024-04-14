#include "Czworokaty.hpp"
#include <math.h>
#include <string>

Kwadrat::Kwadrat(double bok)
{
    a = bok;
    b = bok;
    kat = 90;
}

double Kwadrat::obliczPole()
{
    return a * a;
}

double Kwadrat::obliczObwod()
{
    return a * 4;
}

std::string Kwadrat::podajNazwe()
{
    return "Kwadrat";
}

Romb::Romb(double bok, double kat)
{
    a = bok;
    b = bok;
    this->kat = kat;
}

double Romb::obliczObwod()
{
    return a * 4;
}

double Romb::obliczPole()
{
    return a * a * sin(kat * 3.14 / 180);
}

std::string Romb::podajNazwe()
{
    return "Romb";
}

Prostokat::Prostokat(double bokA, double bokB)
{
    a = bokA;
    b = bokB;
    kat = 90;
}

double Prostokat::obliczObwod()
{
    return a * 2 + b * 2;
}

double Prostokat::obliczPole()
{
    return a * b;
}

std::string Prostokat::podajNazwe()
{
    return "Prostokat";
}