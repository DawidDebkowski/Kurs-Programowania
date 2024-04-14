class BladZakresu extends Exception {
    BladZakresu(String e) {
        super(e);
    }
}

public class WierszTrojkataPascala {
    private int numerWiersza;
    private int[] wiersz;

    public WierszTrojkataPascala(int n) {
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
/*    
    private int wybierz(int n, int k)
    {
        // n silnia dolna / k silnia

        int a = n; // domnazamy przez n, zostaje nam (n-1) silnia dolna k
        //przesunięty indeks o 1 w górę, aby dobrze liczyć k! (i=1 -> i=k). Dlatego przesuwamy (n-i) od i odejmujemy jeden
        // i otrzymujemy x=(n-(i-1)) (x=n-1 -> x=n-k+1), czyli tak jak silnia dolna. 
        for(int i=1;i<=k;i++)
        {
            a *= (n-i+1) / i;
        }
        return a;
    }
 */