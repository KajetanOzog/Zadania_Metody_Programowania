public class Main {
    public static void main(String[] args)
    {
        int[] tabliczka = {-1, 1, 2, -3, -1, 11, 2};
        szescienna(tabliczka);
        kwadratowa(tabliczka);
        kadano(tabliczka);
    }
    static void szescienna (int[] tab)
    {
        int poczatek = 0;
        int koniec = 0;
        int max = 0;
        int sum;
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = i; j < tab.length; j++)
            {
                sum = 0;
                for (int k = i; k <= j; k++)
                {
                    sum += tab[k];
                }
                if(sum > max)
                {
                    max = sum;
                    poczatek = i;
                    koniec = j;
                }
            }
        }
        System.out.println("Max suma = " + max + ", poczatek to: " + poczatek + ", koniec to: " + koniec);
    }

    static void kwadratowa(int[] tab)
    {
        int poczatek = 0;
        int koniec = 0;
        int max = 0;
        int sum;
        for(int i = 0; i < tab.length; i++)
        {
            sum = 0;
            for(int j = i; j < tab.length; j++)
            {
                sum += tab[j];
                if(sum > max)
                {
                    max = sum;
                    poczatek = i;
                    koniec = j;
                }
            }
        }
        System.out.println("Max suma = " + max + ", poczatek to: " + poczatek + ", koniec to: " + koniec);
    }

    static void kadano(int[] tab)
    {
        int poczatek = 0;
        int koniec = 0;
        int max = 0;
        int sum = 0;
        int pom = 0;
        for(int i = 0; i < tab.length; i++)
        {
            sum += tab[i];
            if(sum < 0)
            {
                sum = 0;
                pom = i+1;
            }
            else if (sum > max)
            {
                max = sum;
                poczatek = pom;
                koniec = i;
            }
        }
        System.out.println("Max suma = " + max + ", poczatek to: " + poczatek + ", koniec to: " + koniec);
    }
}