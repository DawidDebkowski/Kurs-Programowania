#include <iostream>
#include <queue>
#include <vector>
#include <string>

class Osoba
{
public:
	int wiek;
	std::string nazwisko;

	Osoba(int wiek, std::string nazwisko)
	{
		this->wiek = wiek;
		this->nazwisko = nazwisko;
	}
};

struct OsobaKomp
{
	bool operator()(const Osoba &o1, const Osoba &o2) const
	{
		if (o1.wiek != o2.wiek)
			return o1.wiek < o2.wiek;
		else
			return o1.nazwisko < o2.nazwisko;
	}

};

int main(int argc, char ** argv)
{
	Osoba os1(21, "Kowalski");
	Osoba os2(34, "Kowal");
	Osoba os3(28, "Nowak");
	Osoba os4(21, "Grzyb");
	

	std::priority_queue<Osoba, std::vector<Osoba>, OsobaKomp> kolejka;

	kolejka.push(os1);
	kolejka.push(os2);
	kolejka.push(os3);
	kolejka.push(os4);


	std::cout << "Prechodzenie po elementach" << std::endl;

	while (!kolejka.empty())
	{
		Osoba o = kolejka.top();
		std::cout << "wiek " << o.wiek << " nazwisko " << o.nazwisko << std::endl;
		kolejka.pop();
	}

	return 0;
}