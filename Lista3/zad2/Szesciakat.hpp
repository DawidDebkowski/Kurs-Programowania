#include "Figura.hpp"

class Szesciakat : public Figura
{
private:
    double bok;
public:
    virtual double obliczPole();
    virtual double obliczObwod();
public:
    Szesciakat(double bok);
    virtual std::string podajNazwe();
};