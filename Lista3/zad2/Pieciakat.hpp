#include "Figura.hpp"

class Pieciakat : public Figura
{
private:
    double bok;
public:
    virtual double obliczPole();
    virtual double obliczObwod();
public:
    Pieciakat(double bok);
    virtual std::string podajNazwe();
};