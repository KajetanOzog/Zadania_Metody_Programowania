//Kajetan Ozog gr nr. 3

//Caly algorytm polega na tym, ze wczytuje kolejne wiersze do tablicy pomoczniczej
//za pomoca lekko przerobionego algorytmu Kadano wyszukuje najwiekszej sumy
//i tak robie dla kazdego mozliwego prostokata w tej tablicy
import java.util.Scanner;
public class Source {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int ile = sc.nextInt(); //Wczytanie ilosci tablic
        for(int l = 0; l < ile; l++) {
            int n = sc.nextInt(); //Wczytanie danych
            String s = "";
            s = sc.next();
            int w = sc.nextInt(); //liczba wierszy
            int k = sc.nextInt(); // liczba kolumn
            boolean isempty = true;
            boolean czysamezera = true;
            int[][] tab = new int[w][k];
            for (int i = 0; i < w; i++) { //Wczytywanie tablicy
                for (int j = 0; j < k; j++) {
                    tab[i][j] = sc.nextInt();
                    if(tab[i][j] > 0)
                    {
                        isempty = false;
                        czysamezera = false;
                    }
                    if(tab[i][j] == 0)
                    {
                        isempty = false;
                    }
                }
            }
            int[] pom = new int[k]; //Pomocnicza tablica
            int size = 0; //Rozmiar tablicy
            int Max = 0; //Maksymalna suma
            int L_G = 0; //Wspolrzedne tablicy koncowej
            int L_D = 0;
            int P_G = 0;
            int P_D = 0;
            for(int i = 0; i < w; i++)
            {
                for(int j = 0; j < k; j++) //Wypelnienie tablicy pomocniczej zerami
                {
                    pom[j] = 0;
                }
                for(int j = i; j < w; j++)
                {
                    int curr = 0; //suma aktualnej podatblicy
                    int b1 = 0; //poczatek podtablicy
                    int size3 = 0; //rozmiar podtablicy
                    for(int p = 0; p < k; p++) {
                        pom[p] += tab[j][p];
                        curr += pom[p];
                        if(pom[p] >= 0)
                        {
                            isempty = false; //sprawdzenie czy tablica ma jakas liczbe dodatnia
                        }
                        if (curr <= 0)
                        {
                            curr = 0;
                            b1 = p + 1;
                        }
                        else
                        {
                            size3 = (j - i + 1) * (p - b1 + 1); //obliczanie pola podtablicy
                        }
                        if (curr >= Max) //sprawdzenie czy podtablica ma wieksza sume od poprzedniej maksymalnej lub czy ma taka sama sume
                        {                //jak maksymalna i porownanie wielkosci
                            if (curr > Max)
                            {
                                Max = curr;
                                size = size3;
                                L_G = i;
                                L_D = j;
                                P_G = b1;
                                P_D = p;
                            }
                            else
                            {
                                if (size > size3)
                                {
                                    size = size3;
                                    L_G = i;
                                    L_D = j;
                                    P_G = b1;
                                    P_D = p;
                                }
                            }
                        }
                    }
                }
            }
            if(isempty)
            {
                System.out.println((l + 1)  + s + " " + "n = " + w + " m = " + k + ", ms = 0, mst is empty" );
            }
            else if (czysamezera)
            {
                int a = 0;
                int b = 0;
                boolean kuniec = true;
                for (int i = 0; i < w; i++) { //Wczytywanie tablicy
                    for (int j = 0; j < k; j++) {
                        if(tab[i][j] == 0 && kuniec)
                        {
                            a = i;
                            b = j;
                            kuniec = false;
                        }
                    }
                }
                System.out.println((l + 1)  + s + " " + "n = " + w + " m = " + k + ", ms = " + Max + ", mst = a[" + a + ".." + a + "]" + "[" + b + ".." + b + "]" );
            }
            else
            {
                //System.out.println((l + 1)  + s + " " + "n = " + w + " m = " + k + ", ms = " + Max);
                System.out.println((l + 1)  + s + " " + "n = " + w + " m = " + k + ", ms = " + Max + ", mst = a[" + L_G + ".." + L_D + "]" + "[" + P_G + ".." + P_D + "]" );
            }
        }
    }
}
