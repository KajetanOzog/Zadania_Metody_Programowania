//Kajetan Ozog grupa 3
import java.util.Scanner;
public class Source
{
    Scanner in = new Scanner(System.in); //Wczytywanie danych z konsoli
    int ile; //Ilosc wykonan
    int tab_size; //Rozmiar tablicy danych
    int[] tablica = new int[0]; //Tablica danych
    int IloscZapytan; //Ilosc zapytan
    int x; //Poczatek przedzialu
    int y; //Koniec przedzialu
    int iloscRoznych = 0; //Ilosc roznych zeznan
    void setIle()
    {
        this.ile = in.nextInt(); //Wczytuje ile bedzie zestawow danych
    }
    void setTablica()
    {
        this.tab_size = in.nextInt(); //Ustawiam rozmiar tablicy
        this.tablica = new int[this.tab_size]; //Tworze ta tablice
        for(int i = 0; i < tab_size; i++)
        {
            this.tablica[i] = in.nextInt(); //Wypelniam tablice
        }
    }
    void diff()
    {
        this.iloscRoznych = this.tab_size;
        for(int j = 0; j < this.tab_size -1; j++)
        {
            if(this.tablica[j] == this.tablica[j + 1])
            {
                this.iloscRoznych--;
            }
        }
    }
    void setIloscZapytan() //Wczytuje iloscZapytan
    {
        this.IloscZapytan = in.nextInt();
    }
    void setXY() //Wczytuje koniec i poczatek przedzialu
    {
        this.x = in.nextInt();
        this.y = in.nextInt();
    }
    int binSearchFirst(int x) //Binarne wyszukiwanie ktore ma na celu znalezc pierwsze wystapienie liczby x
    {                        //dziala jak typowe wyszukiwanie binarne tylko ze jesli x nie wystapi w tablicy
        int left = 0;       //to zwracany jest numer indeksu liczby ktora jest wieksza od x ale jest najbardziej do niego zblizona
        int right = this.tab_size -1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (this.tablica[mid] == x)
            {
                result = mid;
                right = mid - 1;
            }
            else if (this.tablica[mid] < x)
            {

                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }
        if(result == -1)
        {
            return right;
        }
        return result;
    }

    public static void main(String[] args)
    {
        /*Source zadanie = new Source(); //Tutaj jest wykonanie funckji main ktore dziala tak jak w tresci zadania
        zadanie.setIle();
        for(int i = 0; i < zadanie.ile; i++)
        {
            zadanie.iloscRoznych = 0;
            zadanie.setTablica();
            zadanie.setIloscZapytan();
            for(int j = 0; j < zadanie.IloscZapytan; j++)
            {
                zadanie.setXY();
                int first;
                int last;
                if(zadanie.x > zadanie.y) //Szczegolny przypadek gdy pierwsza liczba jest wieksza od drugiej
                {
                    System.out.println(0);
                }
                else if(zadanie.x < zadanie.tablica[0])
                {
                    last = zadanie.binSearchFirst(zadanie.y + 1);
                    System.out.println(last);
                }
                else if(zadanie.y > zadanie.tablica[zadanie.tab_size - 1])
                {
                    first = zadanie.binSearchFirst(zadanie.x);
                    System.out.println(zadanie.tab_size - first);
                }
                else
                {
                    first = zadanie.binSearchFirst(zadanie.x); //Wyznaczam pierwszy indeks
                    last = zadanie.binSearchFirst(zadanie.y + 1); //Wyznaczam ostatni indeks
                    System.out.println(last - first); //Wyznaczam roznice miedzy indeksami i dodatkowo zwiekszam ja o 1
                }
            }
            zadanie.diff();
            System.out.println(zadanie.iloscRoznych);
        }*/
        int[] arr = {1,2,3,4,5,7,8,9};
        Source zadanie = new Source();

        zadanie.setTablica();
        int z = zadanie.binSearchFirst(6);
        System.out.print(z);

    }
}