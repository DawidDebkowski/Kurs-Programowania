#include "Figura.hpp"

class Kolo : public Figura
{
private:
    double r;
public:
    virtual double obliczPole();
    virtual double obliczObwod();
    virtual std::string podajNazwe();

public:
    Kolo(double promien);
};