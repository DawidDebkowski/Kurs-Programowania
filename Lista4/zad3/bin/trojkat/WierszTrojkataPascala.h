class WierszTrojkataPascala {
private:
    int numerWiersza;
    int *wiersz;
public:
    WierszTrojkataPascala(int n);
    void obliczWiersz();
    int wezElement(int m);
    ~WierszTrojkataPascala();
};