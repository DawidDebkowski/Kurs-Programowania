#include <iostream>
#include <math.h>
#include "Czworokaty.hpp"
#include "Kolo.hpp"
#include "Pieciakat.hpp"
#include "Szesciakat.hpp"

using namespace std;

bool dCompare(double a, double b)
{
    return fabs(a - b) < 0.001;
}

void wypiszFigure(string nazwa, double pole, double obwod)
{
    cout << nazwa << endl;
    cout << "Pole: " << pole << endl;
    cout << "Obwod: " << obwod << endl;
}

double stringToDouble(string a) noexcept(false)
{
    double r = stod(a);
    if (r <= 0)
        throw (" - musi byc > 0");
    return r;
}

int main(int argc, char const *argv[])
{
    // skip the name
    argv++;
    argc--;
    if (argc < 2)
    {
        cout << "Brak argumentow";
        return -1;
    }

    try
    {
        Figura *fig;
        switch (argv[0][0])
        {
        case 'o':
        {
            fig = new Kolo(stringToDouble(argv[1]));
            break;
        }
        case 'c':
        {
            if (argc < 6)
                throw ("Za malo argumentow!");
            
            double a = stringToDouble(argv[1]);
            double b = a;
            double kat = stringToDouble(argv[5]);
            for(int i=2;i<5;i++)
            {
                double pot = stringToDouble(argv[i]); 
                if(pot != b) // nie dziala
                {
                    b = pot;
                    break;
                }
            }
            
            int aCount = 0;
            int bCount = 0;
            for (int i = 1; i < 5; i++)
            {
                if (dCompare(stringToDouble(argv[i]), a))
                    aCount++;

                if (dCompare(stringToDouble(argv[i]), b))
                    bCount++;
            }

            if ((aCount != 2 || bCount != 2) && aCount != 4)
            {
                throw "Niepoprawny czworokat";
            }

            string nazwa;
            if(!dCompare(kat, 90.0))
            {
                if(aCount == 4)
                {
                    fig = new Romb(a, kat);
                    nazwa = "Romb";
                }
                else throw "Niepoprawny czworokat";
            }
            else if(dCompare(a,b))
            {
                fig = new Kwadrat(a);
                nazwa = "Kwadrat";
            }
            else 
            {
                fig = new Prostokat(a, b);
                nazwa = "Prostokat";
            }
            break;
        }
        case 'p':
        {
            fig = new Pieciakat(stringToDouble(argv[1]));
            break;
        }
        case 's':
        {
            fig = new Szesciakat(stringToDouble(argv[1]));
            break;
        }
        default:
            throw "Poprawne litery: o, c, p, s";
            break;
        }
        wypiszFigure(fig->podajNazwe(), fig->obliczPole(), fig->obliczObwod());
        delete fig;
    }
    catch (const char* s)
    {
        cout << s;
    }
    return 0;
}
