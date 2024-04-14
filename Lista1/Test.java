public class Test {
    public static void main(String[] args) {
        int n = 0;
        LiczbyPierwsze lp = new LiczbyPierwsze();
        try {
            if(args.length < 1)
                throw new NumberFormatException();
            n = Integer.parseInt(args[0]);

            if(n < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException e) {
            System.out.println("Nieprawidlowy zakres!");
            return;
        }
        lp.ZrobTablice(n);

        for(int i=1;i<args.length;i++) {
            System.out.print(args[i] + " - ");
            try {
                System.out.println(lp.liczba(Integer.parseInt(args[i])));
            } 
            catch (BladZakresu e) {
                System.out.println("liczba spoza zakresu");
            }
            catch(NumberFormatException e) {
                System.out.println("nieprawidlowa dana");
            }
        }
    }
}
