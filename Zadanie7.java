//Kajetan Ozog gr 3

/*
Pomysl polega na tym ze dane wczytuje jako stringi do tablicy 2D, nastepnie uzywam sortowania na wybranej kolumnie
  gdy jakies elementy kolumny beda wymagaly zamiany, to zamieniam caly wiersz w tablicy 2D. Kazdy ze stringow znakuje znakiem "?"
  poniewaz pozniej bedzie to wykorzystywane w metodzie quicksort, jesli mam do czynienia z liczba to dodaje na poczatek litere
  a, b, c, d lub e w zaleznosci od dlugosci liczby, jest to pozniej potrzebne w sorotwaniu bo liczbe traktuje jako string.
  Sorotowanie polega na dwoch petlach, pierwsza wykonuje sie az do momentu gdy okaze sie ze wszystkie elementy sa posortowane
  druga petla wykonuje sie dla mniejszych podzadan. W sytuacji gdy pozdadanie jest wieksze niz 5, to uzywam metody partition
  i dziele rowniez tablice na mniejsze pozdania ale w tej stytuacji w przeciwienstwie do zwyklego quicksorta musze oznakowac element
  nastepnej podtablicy ktora bede musial oznakowac znakiem "{", dzieki temu w nastepnym wywolaniu bede mogl znalezc poczatek
  kolejnej podtablicy.

  Zlozonosc:
  W zwiazku z tym ze algorytm to jest tak na prawde symulacja QuickSorta zatem zloznosc bedzie mial taka sama jak on
  czyli: srednia to O(nlogn) a pesymistyczna to O(n^2)
*/

import java.util.Scanner;
public class Source
{
    public static void quickSortStrR(int size, String[] album, String[][] meta, int size2) {
        int left = 0;
        int right = size - 1;
        int q, i = 0;
        int tmpr = right;
        while (true) {
            i--;
            while (left < tmpr) {
                if (tmpr - left > 5) {
                    q = partitionStrR(left, tmpr, album, meta, size2);
                    String a;
                    if (album[tmpr].charAt(0) == '?')
                    {
                        a = "{" + album[tmpr].substring(1);
                    } else {
                        a = "?" + album[tmpr].substring(1);
                    }
                    album[tmpr] = a;
                    tmpr = q - 1;
                    ++i;
                } else {
                    selectionSortStrR(left, tmpr, album, meta, size2);
                    left = tmpr; //potrzebne do tego zeby wiedziec gdzie nastepna podtablica sie zaczyna
                    break;
                }

            }
            if (i < 0) {
                break;
            }
            left++;
            tmpr = findNextRStrR(left, size, album, meta);
            String a;
            if (album[tmpr].charAt(0) == '?') {
                a = "{" + album[tmpr].substring(1);
            } else {
            a = "?" + album[tmpr].substring(1);
            }
            album[tmpr] = a;
        }
    }

    private static int findNextRStrR(int l, int size,  String[] album, String[][] meta) {
        for (int i = l; i < size; ++i) {
            if (album[i].charAt(0) == '?')
                return i;
        }
        return size - 1;
    }

    private static int partitionStrR(int l, int r, String[] album, String[][] meta, int size2) {
        String pivot = album[(l + r) / 2];
        while (l <= r) {
            while (album[r].compareTo(pivot) > 0)
                r--;
            while (album[l].compareTo(pivot) < 0)
                l++;
            if (l <= r) {

                swap(meta,l,r,size2);

                l++;
                r--;
            }
        }
        return l;
    }

    private static void selectionSortStrR(int start, int end, String[] album, String[][] meta, int size2) {
        for (int i = start; i <= end; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= end; j++) {
                if (album[j].compareTo(album[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(meta, i, minIndex, size2);
            }
        }
    }

    public static void quickSortStrL(int size, String[] album, String[][] meta, int size2)
    {
        int left = 0;
        int right = size - 1;
        int q, i = 0;
        int tmpr = right;
        while (true) {
            i--;
            while (left < tmpr) {
                while (left < tmpr) {
                    if (tmpr - left > 5) {
                        q = partitionStrL(left, tmpr, album, meta, size2);
                        String a;
                        if (album[tmpr].charAt(0) == '?') {
                            a = "{" + album[tmpr].substring(1);
                        } else {
                            a = "?" + album[tmpr].substring(1);
                        }
                        album[tmpr] = a;
                        tmpr = q - 1;
                        ++i;
                    } else {
                        selectionSortStrL(left, tmpr, album, meta, size2);
                        left = tmpr;
                        break;
                    }
                }
            }
            if (i < 0)
            {
                break;
            }
            left++;
            tmpr = findNextRStrL(left, size, album, meta);
            String a;
            if(album[tmpr].charAt(0) == '?')
            {
                a = "{" + album[tmpr].substring(1);
            }
            else
            {
                a = "?" + album[tmpr].substring(1);
            }
            album[tmpr] = a;
        }
    }

    private static int findNextRStrL(int l, int size,  String[] album, String[][] meta) {
        for (int i = l; i < size; ++i) {
            if (album[i].charAt(0) == '?')
                return i;
        }
        return size - 1;
    }

    private static int partitionStrL(int l, int r, String[] album, String[][] meta, int size2) {
        String pivot = album[(l + r) / 2];
        while (l <= r) {
            while (album[r].compareTo(pivot) < 0)
                r--;
            while (album[l].compareTo(pivot) > 0)
                l++;
            if (l <= r) {

                swap(meta,l,r,size2);

                l++;
                r--;
            }
        }
        return l;
    }

    private static void selectionSortStrL(int start, int end, String[] album, String[][] meta, int size2) {
        for (int i = start; i <= end; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= end; j++) {
                if (album[j].compareTo(album[minIndex]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(meta, i, minIndex, size2);
            }
        }
    }
    public static void swap(String[][] meta, int l, int r, int size2)
    {
        for(int i = 0; i < size2; i++)
        {
            String tmp = meta[i][l];
            meta[i][l] = meta[i][r];
            meta[i][r] = tmp;
        }
    }



    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int numOfSets= in.nextInt();
        in.nextLine();
        String firstLine;
        int numRows;
        int numCol;
        int direction;
        for(int i = 0; i < numOfSets; i++)
        {
            firstLine = in.nextLine();
            String[] numbers = firstLine.split(",");
            numRows = Integer.parseInt(numbers[0]);
            numCol = Integer.parseInt(numbers[1]);
            direction =  Integer.parseInt(numbers[2]);

/*            String[] album = new String[numRows];
            int[] year = new int[numRows];
            int[] songs = new int[numRows];
            int[] length = new int[numRows];*/

            String line =  in.nextLine();
            String[] elements = line.split(",");
            String[][] meta = new String[elements.length][numRows];
            for(int j = 0; j < numRows; j++)
            {
                line = in.nextLine();
                String[] arr = line.split(",");
                for (int k = 0; k < arr.length; k++)
                {
                    if(arr[k].charAt(0) >= '0' && arr[k].charAt(0) <= '9')
                    {
                        if(arr[k].length() == 1)
                        {
                            meta[k][j] = "{" + "a" + arr[k];
                        }
                        else if(arr[k].length() == 2)
                        {
                            meta[k][j] = "{" + "b" + arr[k];
                        }
                        else if(arr[k].length() == 3)
                        {
                            meta[k][j] = "{" + "c" + arr[k];
                        }
                        else if(arr[k].length() == 4)
                        {
                            meta[k][j] = "{" + "d" + arr[k];
                        }
                        else if(arr[k].length() == 5)
                        {
                            meta[k][j] = "{" + "e" + arr[k];
                        }
                    }
                    else
                    {
                        meta[k][j] = "{" + arr[k];
                    }

                }
            }

            if(direction > 0)
            {
                quickSortStrR(numRows,meta[numCol-1],meta,elements.length);

            }
            else
            {
                quickSortStrL(numRows,meta[numCol-1],meta,elements.length);
            }

            String tmp = elements[numCol - 1];

            for(int k = numCol - 1; k >= 1; k--)
            {
                elements[k] = elements[k-1];
            }
            elements[0] = tmp;

            for(int j = 0; j < elements.length; j++)
            {
                if(j == 0)
                {
                    System.out.print(elements[j]);
                }
                else
                {
                    System.out.print("," + elements[j]);
                }

            }

            System.out.println();

            String[] temp = meta[numCol - 1];

            for(int k = numCol - 1; k >= 1; k--)
            {
                meta[k] = meta[k-1];
            }
            meta[0] = temp;

            for(int j = 0; j < numRows; j++)
            {
                for(int k = 0; k < elements.length; k++)
                {
                    meta[k][j] = meta[k][j].substring(1);
                    if(meta[k][j].length() > 1)
                    {
                        if(meta[k][j].charAt(1) <= '9' && meta[k][j].charAt(1) >= '0')
                        {
                            meta[k][j] = meta[k][j].substring(1);
                        }
                    }
                    if(k == 0)
                    {
                        System.out.print(meta[k][j]);
                    }
                    else
                    {
                        System.out.print("," + meta[k][j]);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        in.close();

    }
}

//Testy
/*
in:
        3
        7,1,-1
        Album,Year,Songs,Length
        Stadium Arcadium,2006,28,122
        Unlimited Love,2022,17,73
        Californication,1999,15,56
        Fgasd,3321,54,577
        tert,8567,24,423
        vsysvs,63,2342,14
        sdff,765,234,123
        7,2,1
        Album,Year,Songs,Length
        Stadium Arcadium,2006,28,122
        Unlimited Love,2022,17,73
        Californication,1999,15,56
        Fgasd,3321,54,577
        tert,8567,24,423
        vsysvs,63,2342,14
        sdff,765,234,123
        7,4,-1
        Album,Year,Songs,Length
        Stadium Arcadium,2006,28,122
        Unlimited Love,2022,17,73
        Californication,1999,15,56
        Fgasd,3321,54,577
        tert,8567,24,423
        vsysvs,63,2342,14
        sdff,765,234,123
*/


/*
out:
        Album,Year,Songs,Length
        vsysvs,63,2342,14
        tert,8567,24,423
        sdff,765,234,123
        Unlimited Love,2022,17,73
        Stadium Arcadium,2006,28,122
        Fgasd,3321,54,577
        Californication,1999,15,56

        Year,Album,Songs,Length
        63,vsysvs,2342,14
        765,sdff,234,123
        1999,Californication,15,56
        2006,Stadium Arcadium,28,122
        2022,Unlimited Love,17,73
        3321,Fgasd,54,577
        8567,tert,24,423

        Length,Album,Year,Songs
        577,Fgasd,3321,54
        423,tert,8567,24
        123,sdff,765,234
        122,Stadium Arcadium,2006,28
        73,Unlimited Love,2022,17
        56,Californication,1999,15
        14,vsysvs,63,2342*/
