//Kajetan Ozog gr. 3
import java.util.Scanner;

/*
Glowna idea programu jest nie przywiazywanie wagi czy wskazniki w wagonach "next" i "prev" rzeczywiscie wskazuja
odpowiedni kierunek. Dzieki temu oszczedzam na zlozonosci obliczeniowej.
 */
public class Source {
    public static class Wagony
    {
        public String name;
        public Wagony next;
        public Wagony prev;
    }
    public static class Pociag
    {
        public String name;
        public Pociag next;

        public Wagony last;
        public Wagony first;

    }

    public static class Pociagi
    {
        public Pociag first;
        public Pociagi()
        {
            first = null;
        }

        public void New(String pociag, String wagon)
        {
            Pociag nowyP = new Pociag();
            Wagony nowyW = new Wagony();
            nowyP.name = pociag;
            nowyW.name = wagon;
            nowyW.prev = null;
            nowyW.next = null;

            if (first == null)
            {
                first = nowyP;
                nowyP.first = nowyW;
                nowyP.last = nowyW;
            }
            else
            {
                nowyP.first = nowyW;
                nowyP.last = nowyW;
                nowyP.next = first;
                first = nowyP;
            }
        }

        public void TrainsList()
        {
            StringBuilder trains = new StringBuilder("Trains: ");
            if(first == null)
            {
                System.out.println(trains);
            }
            else
            {
                Pociag pom = first;
                while (pom.next != null)
                {
                    trains.append(pom.name);
                    trains.append(" ");
                    pom = pom.next;
                }
                trains.append(pom.name);
                System.out.println(trains);
            }
        }

        void Display (String nazwa)
        {
            StringBuilder dis = new StringBuilder(nazwa);
            dis.append(":");
            Pociag pom = first;
            while (!pom.name.equals(nazwa))
            {
                pom = pom.next;
                if(pom == null)
                {
                    return;
                }
            }
            Wagony pom2 = pom.first;
            if(pom2.next == null && pom2.prev == null) //Sytuacja gdy jest tylko jeden wagon
            {
                System.out.println(dis + " " + pom2.name);
            }
            else if(pom2.prev == null)
            {
                Wagony poprzedni = null;
                while (pom2 != null) //Te ify sa dlatego ze nie wiemy w ktorym kierunku rzeczywiscie jest zwrocony
                                    //poszczegolny wskaznik
                {
                    if(poprzedni == pom2.prev)
                    {
                        poprzedni = pom2;
                        dis.append(" ");
                        dis.append(pom2.name);
                        pom2 = pom2.next;
                    }
                    else
                    {
                        poprzedni = pom2;
                        dis.append(" ");
                        dis.append(pom2.name);
                        pom2 = pom2.prev;
                    }
                }

                System.out.println(dis);
            }
            else
            {
                Wagony poprzedni = null;
                while (pom2 != null)
                {
                    if(poprzedni == pom2.prev)
                    {
                        poprzedni = pom2;
                        dis.append(" ");
                        dis.append(pom2.name);
                        pom2 = pom2.next;
                    }
                    else
                    {
                        poprzedni = pom2;
                        dis.append(" ");
                        dis.append(pom2.name);
                        pom2 = pom2.prev;
                    }
                }
                System.out.println(dis);
            }
        }

        void Reverse (String nazwa) //Zamieniam tutaj last z first dzieki czemu zlozonosc jest O(1)
                                    // (wymaga to tego aby pozostale funkcje byly napisane w odpowiedni sposob)
        {
            Pociag pom = first;
            while (!pom.name.equals(nazwa))
            {
                pom = pom.next;
            }
            Wagony temp = pom.last;
            pom.last = pom.first;
            pom.first = temp;
        }

        void InsertLast(String pociag, String wagon)
        {
            Pociag pom = first;
            while (!pom.name.equals(pociag))
            {
                pom = pom.next;
                if(pom == null)
                {
                    return;
                }
            }
            Wagony pom2 = pom.last;
            Wagony wstaw = new Wagony();
            wstaw.name = wagon;
            if(pom2.next == null && pom2.prev == null)
            {
                wstaw.prev = pom2;
                wstaw.next = null;
                pom2.next = wstaw;
                pom.last = wstaw;
            }
            else if(pom2.prev == null)
            {
                wstaw.prev = null;
                wstaw.next = pom2;
                pom2.prev = wstaw;
                pom.last = wstaw;
            }
            else
            {
                wstaw.prev = pom2;
                wstaw.next = null;
                pom2.next = wstaw;
                pom.last = wstaw;
            }
        }

        void InsertFirst(String pociag, String wagon)
        {
            Pociag pom = first;
            while (!pom.name.equals(pociag))
            {
                pom = pom.next;
                if(pom == null)
                {
                    return;
                }
            }
            Wagony pom2 = pom.first;
            Wagony wstaw = new Wagony();
            wstaw.name = wagon;
            if(pom2.next == null && pom2.prev == null)
            {
                wstaw.next = pom2;
                wstaw.prev = null;
                pom2.prev = wstaw;
                pom.first = wstaw;
            }
            else if(pom2.prev == null)
            {
                wstaw.prev = null;
                wstaw.next = pom2;
                pom2.prev = wstaw;
                pom.first = wstaw;
            }
            else
            {
                wstaw.prev = pom2;
                wstaw.next = null;
                pom2.next = wstaw;
                pom.first = wstaw;
            }
        }

        void Union(String T1, String T2)
        {
            Pociag pierwszy = first;
            Pociag poprzedni = first;
            Pociag drugi = first;
            while(!pierwszy.name.equals(T1) || !drugi.name.equals(T2))
            {
                if(!pierwszy.name.equals(T1))
                {
                    pierwszy = pierwszy.next;
                }
                if(!drugi.name.equals(T2))
                {
                    poprzedni = drugi;
                    drugi = drugi.next;
                }
            }
            Wagony ostP = pierwszy.last;
            Wagony pieD = drugi.first;
            if(ostP.next == null) //Sprawdzam w ktore miejsce moge wstawic drugi pociag
            {
                if(pieD.prev == null)
                {
                    pieD.prev = ostP;
                    ostP.next = pieD;
                    pierwszy.last = drugi.last;
                }
                else if(pieD.next == null)
                {
                    pieD.next = ostP;
                    ostP.next = pieD;
                    pierwszy.last = drugi.last;
                }
            }
            else if(ostP.prev == null)
            {
                if(pieD.prev == null)
                {
                    pieD.prev = ostP;
                    ostP.prev = pieD;

                }
                else if(pieD.next == null)
                {
                    pieD.next = ostP;
                    ostP.prev = pieD;
                }
                pierwszy.last = drugi.last;
            }
            if(drugi == first)
            {
                first = poprzedni.next;
            }
            else
            {
                poprzedni.next = drugi.next;
            }
        }

        void DelFirst(String T1, String T2)
        {
            Pociag pierwszy  = first;
            Pociag poprzedni = first;
            while(!pierwszy.name.equals(T1))
            {
                poprzedni = pierwszy;
                pierwszy = pierwszy.next;
            }

            Wagony pom = pierwszy.first;
            if(pom.prev == null && pom.next == null)
            {
                if(pierwszy == first) //Sytacja gdy usuwany pociag jest pierwszy
                {
                    pierwszy.name = T2;
                }
                else
                {
                    poprzedni.next = pierwszy.next;
                    Pociag drugi = new Pociag();
                    drugi.name = T2;
                    drugi.next = first;
                    first = drugi;
                    drugi.first = pom;
                    drugi.last = pom;
                }
            }
            else if (pom.prev == null)
            {
                pierwszy.first = pom.next;
                Wagony pom2 = pom.next;
                if(pom2.prev == pom)  //Tutaj jak w poprzednich funkcjach rozpatruje w ktora strone jest zwrocony wskaznik
                {
                    pom2.prev = null;
                }
                else
                {
                    pom2.next = null;
                }
                Pociag drugi = new Pociag(); 
                drugi.name = T2;
                drugi.next = first;
                first = drugi;
                drugi.first = pom;
                drugi.last = pom;
                pom.next = null;
            }
            else
            {
                pierwszy.first = pom.prev;
                Wagony pom2 = pom.prev;
                if(pom2.prev == pom)
                {
                    pom2.prev = null;
                }
                else
                {
                    pom2.next = null;
                }
                Pociag drugi = new Pociag();
                drugi.name = T2;
                drugi.next = first;
                first = drugi;
                drugi.first = pom;
                drugi.last = pom;
                pom.prev = null;
            }
        }

        void DelLast(String T1, String T2)
        {
            Pociag pierwszy = first;
            Pociag poprzedni = first;
            while(!pierwszy.name.equals(T1))
            {
                poprzedni = pierwszy;
                pierwszy = pierwszy.next;
            }
            Wagony pom = pierwszy.last;
            if(pom.prev == null && pom.next == null)
            {
                if(pierwszy == first)
                {
                    pierwszy.name = T2;
                }
                else
                {
                    poprzedni.next = pierwszy.next;
                    Pociag drugi = new Pociag();
                    drugi.name = T2;
                    drugi.next = first;
                    first = drugi;
                    drugi.first = pom;
                    drugi.last = pom;
                }
            }
            else if (pom.next == null)
            {
                pierwszy.last = pom.prev;
                Wagony pom2 = pom.prev;
                if(pom2.prev == pom)
                {
                    pom2.prev = null;
                }
                else
                {
                    pom2.next = null;
                }
                Pociag drugi = new Pociag();
                drugi.name = T2;
                drugi.next = first;
                first = drugi;
                drugi.first = pom;
                drugi.last = pom;
                pom.prev = null;
            }
            else
            {
                pierwszy.last = pom.next;
                Wagony pom2 = pom.next;
                if(pom2.prev == pom)
                {
                    pom2.prev = null;
                }
                else
                {
                    pom2.next = null;
                }
                Pociag drugi = new Pociag();
                drugi.name = T2;
                drugi.next = first;
                first = drugi;
                drugi.first = pom;
                drugi.last = pom;
                pom.next = null;
            }
        }
    }


    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        int z = in.nextInt();
        for(int i = 0; i < z; i++)
        {
            Pociagi P = new Pociagi();
            int n = in.nextInt();
            for(int j = 0; j < n; j++)
            {
                String operation = in.next();
                if(operation.equals("New"))
                {
                    String T = in.next();
                    String W = in.next();
                    P.New(T, W);
                }
                else if(operation.equals("InsertFirst"))
                {
                    String T = in.next();
                    String W = in.next();
                    P.InsertFirst(T, W);
                }
                else if(operation.equals("InsertLast"))
                {
                    String T = in.next();
                    String W = in.next();
                    P.InsertLast(T, W);
                }
                else if(operation.equals("Display"))
                {
                    String T = in.next();
                    P.Display(T);
                }
                else if(operation.equals("TrainsList"))
                {
                    P.TrainsList();
                }
                else if(operation.equals("Reverse"))
                {
                    String T = in.next();
                    P.Reverse(T);
                }
                else if(operation.equals("Union"))
                {
                    String T = in.next();
                    String W = in.next();
                    P.Union(T, W);
                }
                else if(operation.equals("DelFirst"))
                {
                    String T = in.next();
                    String W = in.next();
                    P.DelFirst(T, W);
                }
                else if(operation.equals("DelLast"))
                {
                    String T = in.next();
                    String W = in.next();
                    P.DelLast(T, W);
                }
            }
        }
    }
}

/*
wejscie
1
14
New T1 W1
InsertFirst T1 W0
InsertLast T1 W2
DelFirst T1 T2
Reverse T1
TrainsList
New T6 P1
InsertFirst T6 W0
InsertLast T6 W2
DelFirst T6 T2
Reverse T6
Union T6 T1
TrainsList
Display T6
* */

/*
wyjscie:
Trains: T2 T1
Trains: T2 T6 T2
T6: W2 P1 W2 W1
* */