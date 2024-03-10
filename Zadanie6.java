//Kajetan Ozog
import java.util.Scanner;

public class Source {

    static class Param //To jest klasa przechowujaca wage elementu i jego miejsce w tablicy
    {
        int waga;
        int miejsce;
        Param(int w, int m)
        {
            waga = w;
            miejsce = m;
        }
    }

    static class Stack { //Jest to stos z podstawowywmi metodami
        int size = 10000;
        int miejsce = -1;
        Param[] element = new Param[10000];

        void Push(Param o)
        {
            element[++miejsce] = o;
        }

        Param Pop()
        {
            return element[miejsce--];
        }

        Param Last()
        {
            return element[miejsce];
        }

        boolean IsEmpty()
        {
            return miejsce <= -1;
        }

        void WriteReverse() //Metoda sluzaca do wypisania elementow stosu od konca
        {
            for(int i = 0; i <= miejsce; i++)
            {
                System.out.print(" " + element[i].waga);
            }
        }
    }
    public static boolean rec_pakuj(int[] tab, int miejsce, int suma, boolean[] B) //funkcja rekurencyjna
    {   //Funckja ta ma celu wpisanie do tablicy boolean wartosci true w miejsca gdzie beda pasujace elementy
        if(tab.length == miejsce)
        {
            return false;  //przerwanie gdy dojdziemy do ostatniego elementu
        }
        if(suma - tab[miejsce] > 0) //jesli element moze "pasowac" to wywolujemy funkcje dalej
        {
            if(rec_pakuj(tab, miejsce + 1, suma - tab[miejsce],B))
            {
                B[miejsce] = true;
                return true;
            }
            else
            {
                return rec_pakuj(tab,miejsce + 1, suma,B);
            }
        }
        else if (suma - tab[miejsce] == 0) //wlozenie elementu gdy waga jest idealnie dobrana
        {
            B[miejsce] = true;
            return true;
        }
        else
        {
            return rec_pakuj(tab,miejsce + 1, suma,B); //wywolujemy funkcje dla pozostalych elementow
        }
    }

    public static void iter_pakuj(int[] tab, int suma)
    {
        Stack stos = new Stack(); //tworze stos w kotrym beda parametry z waga i miejscem
        for(int i = 0; i < tab.length; i++)
        {
            if(tab[i] <= suma) //jesli element pasuje to go wstawiam
            {
                suma -= tab[i];
                Param nast = new Param(tab[i], i);
                stos.Push(nast);
                if(suma == 0)
                {
                    break;
                }
                while (i == tab.length - 1) //jesli element jest ostatni a waga nie jest rowna sumie to usuwam go
                {
                    if(stos.IsEmpty())
                    {
                        break;
                    }
                    Param b = stos.Pop();
                    suma += b.waga;
                    i = b.miejsce;
                }
            }
            else
            {
                if (i == tab.length - 1) //jesli element nie pasuje i jest ostatni to usuwam poprzedni element
                {
                    if(stos.IsEmpty())
                    {
                        break;
                    }
                    Param b = stos.Pop();
                    suma += b.waga;
                    i = b.miejsce;
                }
            }
        }
        if(suma == 0) //jesli da sie uzupelnic plecak to wypisuje elementy ze stosu
        {
            stos.WriteReverse();
        }
        else
        {
            System.out.println("BRAK");
        }
    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int zestawy = in.nextInt();
        for(int i = 0; i < zestawy; i++)
        {
            int suma = in.nextInt();
            int size = in.nextInt();
            int[] A = new int[size];
            boolean[] B = new boolean[size];

            for(int j = 0; j < size; j++)
            {
                A[j] = in.nextInt();
                B[j] = false;
            }

            if(rec_pakuj(A,0,suma,B))
            {
                System.out.print("REC: " + suma + " =");
                for(int k = 0; k < size; k++)
                {
                    if(B[k])
                    {
                        System.out.print(" " + A[k]);
                    }
                }
                System.out.println();
                System.out.print("ITER: " + suma + " =");
                iter_pakuj(A,suma);
                System.out.println();
            }
            else
            {
                iter_pakuj(A, suma);
                //System.out.println("BRAK");
            }
        }
    }
}

/*
Testy:

4
67
4
3 4 7 60
78
5
1 2 3 4 5
5
4
1 1 1 5
7
5
1 2 3 1 4

Wyniki:

REC: 67 = 3 4 60
ITER: 67 = 3 4 60
BRAK
REC: 5 = 5
ITER: 5 = 5
REC: 7 = 1 2 3 1
ITER: 7 = 1 2 3 1

*/