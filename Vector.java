import java.util.Random;
class Vectorr {

    private int[] a; // referencja do wektora
    private int maxSize; // maksymalna długość wektora
    private int n; // aktualna długość wektora

    public Vectorr(int m) { // konstruktor
        maxSize = m;
        n = 0;
        a = new int[maxSize];
    }

    // Dopisz:

    // konstruktor przyjmujący maksymalny rozmiar "m" i wypełniający wektor
    // "l" losowymi wartościami z przedziału 0-100 (proszę znaleźć jak losować w
    // Internecie )
    public Vectorr(int m, int l) {
        Random los = new Random();
        n = l;
        maxSize = m;
        a = new int[maxSize];
        for(int i = 0; i < l; i++)
        {
            int liczba = los.nextInt(101);
            a[i] = liczba;
        }
    }

    // wyświetl wektor na ekran
    public void display() {
        for(int i = 0; i < n; i++)
        {
            System.out.print(a[i] + " ");
        }
    }

    // zwróć Stringa z kolejnymi wartościami z wektora rozdzielonymi spacją
    @Override
    public String toString() {
        String wynik = "";
        for(int i = 0; i < n; i++)
        {
           wynik += a[i];
           wynik += " ";
        }
        return wynik;
    }

    // wstaw "x" na koniec wektora (uwaga na maksymalny rozmiar)
    public void insert(int x) {
        if(n < maxSize)
        {
            a[n] = x;
            n += 1;
        } //zakladam ze jezeli wektor jest pelny to ostatni element zastepuje
        else
        {
            a[maxSize - 1] = x;
        }
    }

    // usuń z wektora wszystkie wystąpienia liczby "x"
    public void remove(int x) {
        for(int i = 0; i < n; i++)
        {
            if(a[i] == x)
            {
                for(int j = i; j < n - 1; j++)
                {
                    a[j] = a[j+1];
                }
                n--;
            }
        }
    }

    // zwraca element o podanym indeksie (uwaga na nieprawidłowe indeksy)
    public int at(int i) {
        if(i < n)
        {
            return a[i];
        }
        else
        {
            System.out.println("BLAD!!!");
            return -234234;
        }
    }
    // Stwórz w funkcji main prezentację wszystkich funkcji

}
