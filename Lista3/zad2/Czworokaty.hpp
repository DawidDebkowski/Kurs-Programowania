#pragma once
#include "Czworokat.hpp"
#include <string>

class Kwadrat : public Czworokat
{
public:
    Kwadrat(double bok);
public:
    virtual double obliczPole();
    virtual double obliczObwod();
    virtual std::string podajNazwe();
};

class Romb : public Czworokat
{
public:
    Romb(double bok, double kat);
public:
    virtual double obliczPole();
    virtual double obliczObwod();
    virtual std::string podajNazwe();
};

class Prostokat : public Czworokat
{
public:
    Prostokat(double bokA, double bokB);
public:
    virtual double obliczPole();
    virtual double obliczObwod();
    virtual std::string podajNazwe();
};