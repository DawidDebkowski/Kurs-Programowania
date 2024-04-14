class BladZakresu extends Exception {};

public class LiczbyPierwsze {
    int liczbyPierwsze[];
    int ilePierwszych = 0;

    public LiczbyPierwsze() {}
    
    public void ZrobTablice(int n)
    {
       liczbyPierwsze = new int[n];

        for(int i=2;i<n;i++)
        {
            if(pierwsza(i))
            {
                liczbyPierwsze[ilePierwszych] = i;
                ilePierwszych++;
            }
        }
    }

    private boolean pierwsza(int n)
    {
        for(int i=2;i*i<=n;i++)
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public int liczba(int m) throws BladZakresu
    {
        if(m < ilePierwszych && m >= 0)
        {
            return liczbyPierwsze[m];
        }
        else throw new BladZakresu();
    }
}
