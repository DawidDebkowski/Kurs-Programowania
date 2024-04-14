#pragma once
#include <string>
class Figura
{
public:
    virtual double obliczPole() = 0;
    virtual double obliczObwod() = 0;
    virtual std::string podajNazwe()=0;
    virtual ~Figura() {};
};