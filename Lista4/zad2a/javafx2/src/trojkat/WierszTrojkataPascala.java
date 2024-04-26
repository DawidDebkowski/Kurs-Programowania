package trojkat;

public class WierszTrojkataPascala {
    private int numerWiersza;
    private int[] wiersz;

    public WierszTrojkataPascala(int n) throws BladZakresu{
        if(numerWiersza<0)
            throw new BladZakresu("nie moze byc ujemna");
        numerWiersza = n;
    }

    // nC(k+1) = nCk * (n-k) / (k+1) | source: math on paper
    public void obliczWiersz(){

        wiersz = new int[numerWiersza+1];

        int choose = 1; // n choose 0
        for(int k=0;k<=numerWiersza;k++) {
            wiersz[k] = choose;
            //System.out.println(wiersz[k] + " ");
            choose = choose * (numerWiersza-k) / (k+1);
        }
    }  

    public int wezElement(int m) throws BladZakresu{
        if(m > numerWiersza)
            throw new BladZakresu("za duży argument");
        if(m < 0)
            throw new BladZakresu("argument nie może być ujemny");

        return wiersz[m];
    }
}