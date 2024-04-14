public class dzielnik {
    public static void main(String[] args) {
        for(int i=0;i<args.length;i++)
        {
            try {
                int n = Integer.parseInt(args[i]);
                System.out.println(n + " " + div(Math.abs(n)));
            }
            catch (NumberFormatException ex) {
                System.out.println(args[i] + " nie jest liczba calkowita");
            }
        }
    }

    public static int div(int n)
    {
        
        for(int i=2;i*i<=n;i++)
        {
            if(n % i == 0)
            {
                return n/i;
            }
        }
        return 1;
    }
}
