//Kajetan Ozog grupa 3
import java.util.Scanner;

/*
Tworze trzy stosy, ktore przechowuja wartosci: char, string, int.

Na poczatku wczytuje ilosc przejsc. Uzywam metody Integer.parseInt aby uzyskac wartosc Int a nie String.
Nastepnie tworze petle ktora bedzie pobierala kolejne wyrazenia.
Zmienna String typ przechowuje pierwsze 5 znakow wejscia.
Dzieki czemu bede wiedzial w jakiej notacji jest zapisane wyrazenie.

Jezeli wyrazenie jest zapisane w notacji infiksowej:

Najpierw wywoluje metode usunINF(wejscie), tworzy ona nowy napis do ktorego wpisuje tylko litery i dozwolone znaki, lacznie z nawiasami.
Nastepnie wywoluje funkcje czyDzialaINF(wejscie).
Dziala ona dokladnie tak samo jak maszyna Turinga przedstawiona w tresci zadania.
Dodatkowo zlicza ona czy liczba nawiasow i ich ulozenie sie zgadza oraz czy operatory i operandy sa w dobrym miejscu.
Jesli zostanie zwrocona wartosc FALSE to wtedy wypisuje na wyjscie ,,error", w przeciwnym wypadku dokonuje zamiany za pomoca funkcji naONP(wejscie).
Tworze stos ktory bedzie przechowywal znaki oraz tworze string ktory bedzie wynikiem. Iteruje po kolejnych znakach napisu.
Jesli pojawia sie litera to wpisuje ja do stringu ,,wyjscie".
Jesli pojawia sie nawias otwierajacy to wstawiam go na stos, jesli pojawia sie nawias zamykajacy to wypisuje do ,,wyjscie" wszystkie znaki ze stosu dopoki nie napotkam nawiasu otwierajacego.
Jesli pojawia sie operator to wypisuje wszystkie elementy ze stosu o wyzszym priorytecie (lub o tym samym ale gdy operator jest lewostronny) lub dopoki nie napotkam nawiasu otwierajacego lub nie skonczy sie stos.
Na koniec zwracam string wyjscie.

Jezeli wyrazenie jest zapisane w notacji ONP:

Najpierw wywoluje metode usunONP(wejscie), tworzy ona nowy napis do ktorego wpisuje litery i dozwolone znaki ale bez nawiasow.
Nastepnie uzywam funkcji czyDzialaONP(wejscie) ktora sprawdza czy ilosc operandow i odpowiednich operatorow jest prawidlowa.
Na sam koniec wywoluje funkcje naINF(wejscie) ktora tworzy dwa stosy, pierwszy z napisami wyrazen ktore beda laczone ze soba a drugi z wartosciami priorytetow.
Tworze string gora ktory bedzie wyrazeniem dodawanym na czubek stosu. Iteruje po kolejnych znakach napisu.
Jezeli znak jest operandem to wpisuje go na czubek stosu napisow, oraz na szubek stosu priorytetow wpisuje jego priorytet (operandy maja najwyzszy priorytet).
Jezeli znak jest operatorem ale jednoczesnie nie jest ani '~' ani '!' (dla nich jest specjalna czesc) bede laczyl ze soba dwa najwyzszej polozone napisy w stosie napisow.
W zaleznosci od priorytetow i tego czy sa one prawo czy lewo stronne bede dodawal do nich nawiasy.
Jezeli znak jest '~' lub '!' wtedy wyjmuje tylko najwyzszy element ze stosu stringow i w zaleznosci od tego jaki byl priorytet dodaje nawiasy.
Na sam koniec zdejmuje z samej gory stosu wynik i za pomoca funkcji dodajSpacje(wynik) dodaje do niego spacje tak aby wyglad odpowiedzi sie zgadzal.
 */
class StosPriorytety {
    int poczatek;
    int stos[] = new int[300];

    StosPriorytety()
    {
        poczatek = -1;
    };

    void wstaw(int x)
    {
        poczatek++;
        stos[poczatek] = x;
    }

    int wyjmij()
    {
        if(poczatek >= 0)
        {
            poczatek--;
            return  stos[poczatek + 1];
        }
        else
        {
            return 0;
        }
    }

    int ostatni()
    {
        return stos[poczatek];
    }
}
class StosZnaki
{
    char[] stos = new char[300];
    int poczatek = 0;


    StosZnaki()
    {
        this.poczatek = -1;
    }

    boolean pusty()
    {
        if(poczatek >= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    void wstaw(char znak)
    {
        this.poczatek++;
        stos[poczatek] = znak;
    }
    char wyjmij()
    {
        if(pusty())
        {
            return ' ';
        }
        else
        {
            this.poczatek--;
            return stos[poczatek + 1];
        }
    }

    char ostatni()
    {
        if(pusty())
        {
            return ' ';
        }
        else
        {
            return stos[poczatek];
        }
    }
}

class StosStringi
{
    String[] stos = new String[300];
    int poczatek;

    StosStringi()
    {
        poczatek = -1;
    }
    void wstaw(String napis)
    {
        poczatek++;
        stos[poczatek] = napis;
    }
    String wyjmij()
    {
        if(poczatek >= 0)
        {
            poczatek--;
            return stos[poczatek + 1];
        }
        else
        {
            return "";
        }
    }

    String ostatni()
    {
        return stos[poczatek];
    }

}
public class Source {

    public static int Priorytet(char x) {
        if (x == '=')
        {
            return 0;
        }
        else if ( x == '|')
        {
            return 1;
        }
        else if ( x == '&')
        {
            return 2;
        }
        else if ( x == '?')
        {
            return 3;
        }
        else if ( x == '<' || x == '>' )
        {
            return 4;
        }
        else if ( x == '+' || x == '-' )
        {
            return 5;
        }
        else if ( x == '*' || x == '/' || x == '%' )
        {
            return 6;
        }
        else if ( x == '^' )
        {
            return 7;
        }
        else if ( x == '~' || x == '!')
        {
            return 8;
        }
        else
        {
            return 9; //Jezeli mam do czynienia z operandem to daje mu najwyzszy priorytet,
            // bedzie to potrzebne przedewszystkim w czasie konwersji z ONP na INF
        }
    }
    public static String usunINF(String napis)
    {
        String wyjscie = "";
        for(int i = 0; i < napis.length(); i++)
        {
            char litera = napis.charAt(i);
            if (litera <= 'z' && litera >= 'a')
            {
                wyjscie += litera;
            }
            else
            {
                switch (litera)
                {
                    case '(':
                    case ')':
                    case '!':
                    case '~':
                    case '^':
                    case '*':
                    case '/':
                    case '%':
                    case '+':
                    case '-':
                    case '<':
                    case '>':
                    case '?':
                    case '&':
                    case '|':
                    case '=':
                        wyjscie += litera;
                        break;
                }
            }
        }
        return wyjscie;
    }

    public static boolean czyDzialaINF(String napis)
    {
        int stan = 0;
        int iloscNawiasow = 0;
        char litera = '*';
        for(int i = 0; i < napis.length(); i++)
        {
            litera = napis.charAt(i);
            if(stan == 0)
            {
                if(litera <= 'z' && litera >= 'a')
                {
                    stan = 1;
                }
                else if (litera == '(')
                {
                    if(iloscNawiasow < 0)
                    {
                        return false;
                    }
                    else
                    {
                        iloscNawiasow++;
                    }
                    stan = 0;
                }
                else if (litera == '~' || litera == '!')
                {
                    stan = 2;
                }
                else
                {
                    return false;
                }
            }
            else if (stan == 1)
            {
                if (litera == ')')
                {
                    if(iloscNawiasow > 0)
                    {
                        iloscNawiasow--;
                        stan = 1;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    switch (litera)
                    {
                        case '/':
                        case '%':
                        case '+':
                        case '^':
                        case '=':
                        case '<':
                        case '>':
                        case '*':
                        case '-':
                        case '|':
                        case '&':
                        case '?':
                            stan = 0;
                            break;
                        default:
                            return false;
                    }
                }
            }
            else if (stan == 2)
            {
                if (litera == '~' || litera == '!')
                {
                    stan = 2;
                }
                else
                {
                    if (litera == '(')
                    {
                        if (iloscNawiasow >= 0)
                        {
                            iloscNawiasow++;
                        }
                        else
                        {
                            return false;
                        }
                        stan = 0;
                    }
                    else if (litera <= 'z' && litera >= 'a')
                    {
                        stan = 1;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        if(iloscNawiasow == 0)
        {
            switch (litera)
            {
                case '/':
                case '%':
                case '+':
                case '^':
                case '=':
                case '<':
                case '>':
                case '*':
                case '-':
                case '|':
                case '&':
                case '?':
                    return false;
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    public static char lacznosc(char znak)
    {
        switch(znak) {
            case '!':
                return 'p';
            case '~':
                return 'p';
            case '=':
                return 'p';
            case '^':
                return 'p';
            case '*':
                return 'l';
            case '/':
                return 'l';
            case '%':
                return 'l';
            case '+':
                return 'l';
            case '-':
                return 'l';
            case '<':
                return 'l';
            case '>':
                return 'l';
            case '?':
                return 'l';
            case '&':
                return 'l';
            case '|':
                return 'l';
        }
        return 'x';
    }

    public static String naONP(String napis)
    {
        StosZnaki stos = new StosZnaki();
        String wyjscie = "";
        for(int i = 0; i < napis.length(); i++)
        {
            char litera = napis.charAt(i);
            if (litera <= 'z' && litera >= 'a')
            {
                wyjscie += litera;
                wyjscie += " ";
            }
            else if (litera == '(')
            {
                stos.wstaw(litera);
            }
            else if (litera == ')')
            {
                char znak = stos.wyjmij();
                while (znak != '(')
                {
                    wyjscie += znak;
                    wyjscie += " ";
                    znak = stos.wyjmij();
                }
            }
            else
            {
                if(stos.pusty())
                {
                    stos.wstaw(litera);
                }
                else
                {
                    char znak = stos.ostatni();
                    while (znak != '(' && !stos.pusty())
                    {
                        int pL = Priorytet(litera);
                        int pZ = Priorytet(znak);
                        char LL = lacznosc(litera);
                        if(LL == 'l')
                        {
                            if(pL <= pZ)
                            {
                                znak = stos.wyjmij();
                                wyjscie += znak;
                                wyjscie += " ";
                            }
                            else
                            {
                                break;
                            }
                        }
                        else
                        {
                            if(pL < pZ)
                            {
                                znak = stos.wyjmij();
                                wyjscie += znak;
                                wyjscie += " ";
                            }
                            else
                            {
                                break;
                            }
                        }
                        znak = stos.ostatni();
                    }
                    stos.wstaw(litera);
                }
            }
        }
        while (!stos.pusty()) //Wypisuje pozostale znaki ze stosu
        {
            wyjscie += stos.wyjmij();
            wyjscie += " ";
        }
        int dl = wyjscie.length();
        wyjscie = wyjscie.substring(0, dl-1); //usuam ostatni znak czyli spacje
        return wyjscie;
    }

    public static String usunONP(String napis)
    {
        String wyjscie = "";
        for(int i = 0; i < napis.length(); i++)
        {
            char litera = napis.charAt(i);
            if (litera <= 'z' && litera >= 'a')
            {
                wyjscie += litera;
            }
            else
            {
                switch (litera)
                {
                    case '!':
                    case '~':
                    case '^':
                    case '*':
                    case '/':
                    case '%':
                    case '+':
                    case '-':
                    case '<':
                    case '>':
                    case '?':
                    case '&':
                    case '|':
                    case '=':
                        wyjscie += litera;
                        break;
                }
            }
        }
        return wyjscie;
    }

    public static boolean czyDzialaONP(String napis)
    {
        int iloscLiter = 0;
        int iloscOperatorow = 0;
        char znak;
        for(int i = 0; i < napis.length(); i++) //Ilosc operandow musi byc o jeden wieksza od ilosci operatorow
        {
            znak = napis.charAt(i);
            if(znak <= 'z' && znak >= 'a')
            {
                iloscLiter++;
            }
            else if (znak != '~' && znak != '!')
            {
                iloscOperatorow++;
            }

            if(iloscLiter - iloscOperatorow < 1) //Sprawdzam czy w pewnym momencie nie ma za duzo operatorow
            {
                return false;
            }
        }
        if(iloscLiter - iloscOperatorow == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public static String dodajSpacje(String napis)
    {
        String wynik = "";
        for(int i = 0; i < napis.length(); i++)
        {
            wynik = wynik + napis.charAt(i) + " ";
        }
        wynik = wynik.substring(0, wynik.length() -1);
        return wynik;
    }

    public static String naINF(String napis) {
        StosStringi stosS = new StosStringi();
        StosPriorytety stosP = new StosPriorytety();
        String gora;
        for (int i = 0; i < napis.length(); i++)
        {
            char znak = napis.charAt(i);
            gora = "";
            if (znak >= 'a' && znak <= 'z')
            {
                String x = "";
                x += znak;
                stosS.wstaw(x);
                stosP.wstaw(Priorytet(znak));
            }
            else if (znak != '~' && znak != '!') //Lacze dwa ostatnie wyrazy stosu napisow
            {
                if (stosP.ostatni() <= Priorytet(znak) && znak != '=') //Biore pierwszy wyraz i sprawdzam czy nie nalezy dodac nawiasow
                {
                    if (stosP.ostatni() == Priorytet(znak) && znak == '^')
                    {
                        gora = stosS.wyjmij();
                    }
                    else
                    {
                        gora = "(" + stosS.wyjmij() + ")";
                    }
                }  else {
                    gora = stosS.wyjmij();
                }
                stosP.wyjmij();
                if (stosP.ostatni() < Priorytet(znak)) //Biore drugi wyraz i sprawdzam czy nie nalezy dodac nawiasow
                {
                    gora = "(" + stosS.wyjmij() + ")" + znak + gora;
                }
                else
                {
                    gora = stosS.wyjmij() + znak + gora;
                }
                stosP.wyjmij();
                stosS.wstaw(gora);
                stosP.wstaw(Priorytet(znak)); //Wstawiam znak pobrany jako znak laczacy ostatni wyraz stosu
            }
            else
            {
                if (stosP.ostatni() < Priorytet(znak))
                {
                    gora = znak + "(" + stosS.wyjmij() + ")";
                }
                else
                {
                    gora = znak + stosS.wyjmij();
                }
                stosP.wyjmij();
                stosS.wstaw(gora);
                stosP.wstaw(Priorytet(znak));
            }

        }
        String wynik = stosS.wyjmij();
        wynik = dodajSpacje(wynik);
        return wynik;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int iloscPrzejsc = Integer.parseInt(in.nextLine());
        String wejscie;
        String typ;
        for(int i = 0; i < iloscPrzejsc; i++)
        {
            wejscie = in.nextLine();
            typ = wejscie.substring(0,5);
            if(typ.equals("INF: "))
            {
                wejscie = usunINF(wejscie);
                if(czyDzialaINF(wejscie))
                {
                    System.out.println("ONP: " + naONP(wejscie));

                }
                else
                {
                    System.out.println("ONP: error");
                }
            }
            else
            {
                wejscie = usunONP(wejscie);
                if(czyDzialaONP(wejscie))
                {
                    System.out.println("INF: " + naINF(wejscie));
                }
                else
                {
                    System.out.println("INF: error");
                }
            }
        }
    }
}

//TESTY
    //Dane:
/*
14
INF: ((a
ONP: xabc**=
ONP: abc^^
INF: d/~p
INF: x=a*(b*c)
ONP: ab^c
INF: (a)
ONP: a~b~~+c~/~d~*~e~^~^~
INF: a+b+
ONP: xaab-c++=
ONP: dfghjklwertyuiop+-*^/<>=+-*^/<>=
INF: y-a*(b + x^v - e) / c + d / (~ p)
INF: y-a*(b + x^v - e) / c + d / ~ p
ONP: aa~*
*/


    //Rezultat:
/*
ONP: error
INF: x = a * ( b * c )
INF: a ^ b ^ c
ONP: d p ~ /
ONP: x a b c * * =
INF: error
ONP: a
INF: error
ONP: error
INF: x = a + ( a - b + c )
INF: error
ONP: y a b x v ^ + e - * c / - d p ~ / +
ONP: y a b x v ^ + e - * c / - d p ~ / +
INF: a * ~ a
*/