//Kajetan Ozog gr 3
/*
To zadanie to typowa to algorytm przedstawiony na wykladzie. Jedyna roznica jest fakt ze nie tworze nowej tablicy
do przechowywania median tylko mediany wstawiam na poczatek tablicy i na tym fragmencie wywoluje znowu ta sama metode
Metoda dziala tak dlugo az znajdzie sie dany element.

Zlozonosc:
Jest to O(n), tak jak zostalo to przedstawione na wykladzie.
Aby znalezc mediany mamy O(n)
Aby znalezc mediane median mamy T(n/5)
Dla przeszukania reszty mamy T(3n/4) poniewaz mediana median w pesymistycznym przypadku w taki sposob podzieli zbior
W skoro 1/5 + 3/4 = 19/20 < 1
W takim razie rownanie rekurencyjne to: T(n) <= O(n) + T(n/5) + T(3n/4) czyli w najgorszym przypadku cala zlozonosc to O(n)
*/
import java.util.Scanner;
class Source
{
    private static void selectionSort(int start, int end, int[] array)
    {
        for (int i = start; i < end; i++) {
            int minIndex = i;
            for (int j = i + 1; j < end; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
    public static int select(int[] array, int start, int end, int k)
    {
        if(end - start <= 1)
        {
            return array[start];
        }

        int j = 0;
        for(int i = start; i < end; i +=5 )
        {
            if( i+5 < end)
            {
                selectionSort(i,i+5,array);
                int temp = array[start + j];
                array[start+j] = array[i + 2];
                array[i + 2] = temp;
            }
            else
            {
                selectionSort(i,end,array);
                int temp = array[start+j];
                array[start+j] = array[(i+end) / 2];
                array[(i+end) / 2] = temp;
            }
            j++;
        }
        j++;

        int median = select(array,start,start + j - 1, j/2);

        int L = start;
        int R = end - 1;
        int i = start;

        while (i <= R)
        {
            if(array[i] < median)
            {
                int tmp = array[L];
                array[L] = array[i];
                array[i] = tmp;
                L++;
                i++;
            }
            else if (array[i] > median)
            {
                int tmp = array[R];
                array[R] = array[i];
                array[i] = tmp;
                R--;
            }
            else
            {
                i++;
            }
        }

        int S1, S2;
        int id = start;
        while (array[id] < median)
        {
            id++;
        }

        S1 = id - 1;
        while (id < end && array[id] == median)
        {
            id++;
        }

        S2 = id - 1;

        if(k <= S1 - start)
        {
            return select(array,start,S1 + 1, k);
        }
        else if (k <= S2 - start)
        {
            return median;
        }
        else
        {
            return select(array,S2 + 1, end, k + start - S2 - 1);
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int sets = in.nextInt();

        for(int i = 0; i < sets; i++)
        {
            int size = in.nextInt();
            int[] array = new int[size];
            for(int j = 0; j < size; j++)
            {
                array[j] = in.nextInt();
            }

            int pyt = in.nextInt();
            for(int j = 0; j < pyt; j++)
            {
                int nr = in.nextInt();

                if(nr <= 0 || nr > size)
                {
                    System.out.println(nr + " brak");
                }
                else
                {
                    System.out.println(nr + " " + select(array,0, size, nr - 1));
                }
            }

        }
    }
}

//Testy

/*
in:
3
7
1 2 3 4 5 6 7
4
1 2 3 234
5
5 10 15 20 4
6
2 5 1111 3 4 -7
10
1 1 1 1 1 1 1 1 1 1
5
1 10 0 2 11

out:
1 1
2 2
3 3
234 brak
2 5
5 20
1111 brak
3 10
4 15
-7 brak
1 1
10 1
0 brak
2 1
11 brak


*/