//Kajetan Ozog gr.3

/*
Jest to poprostu lekko zmodyfikowany merge sort ktory zlicza ilosc zamian
 */
import java.util.Scanner;
public class Source {

    public static long Inversion(long[] a, int start, int koniec) {
        if (start >= koniec)
        {
            return 0;
        }

        int mid = (start + koniec) / 2;

        long x = Inversion(a, start, mid);
        x += Inversion(a, mid + 1, koniec);

        int lewo = start;
        int prawo = mid + 1;
        int pom = 0;

        if(start == 0 && koniec == a.length) //Jest to finalne wykonanie tej funkcji wiec nie oplaca sie juz
                                            //tworzyc nowej tablicy bo interesuja nas tylko inwersje
        {
            while (lewo <= mid && prawo <= koniec) {
                if (a[lewo] <= a[prawo]) {
                    lewo++;
                } else {
                    prawo++;
                    x += mid - lewo + 1;
                }
            }
        }
        else  //Jest to sytuacja gdy musimy miec posortowana tablice zatem tworzymy nowa tablice o rozmiarze
        {   // koniec - start + 1 ale w najgorszej sytuacji rozmiar tej tablicy to (rozmiar tablic)/2 + 1
            long[] temp = new long[koniec - start + 1];

            while (lewo <= mid && prawo <= koniec) {
                if (a[lewo] <= a[prawo])
                {
                    temp[pom++] = a[lewo++];
                }
                else
                {
                    temp[pom++] = a[prawo++];
                    x += mid - lewo + 1;
                }
            }
            while (lewo <= mid)
            {
                temp[pom++] = a[lewo++];
            }

            while (prawo <= koniec) {
                temp[pom++] = a[prawo++];
            }

            for (lewo = start, pom = 0; lewo <= koniec; lewo++, pom++) {
                a[lewo] = temp[pom];
            }
        }
        return x;
    }



    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++)
        {
            int size = in.nextInt();
            long[] tabliczka = new long[size];
            for(int j = 0; j < size; j++)
            {
                tabliczka[j] = in.nextLong();
            }
            System.out.println(Inversion(tabliczka, 0, tabliczka.length - 1));
        }
    }
}

/*wejście:
5
10
1 2 3 4 5 6 7 8 9 10
10
1 10 2 9 3 8 4 7 5 6
10
10 9 8 7 6 5 4 3 2 1
10
1 5 8 3 9 4 2 5 7 3
10
1 75 3 6535 123 876 132 2345 123 12 756
*/
/*

wyjscie
0
20
45
20
17
*/
