//Kajetan Ozog
import java.util.Scanner;
public class Source
{
    public static class Node //w tym przechowuje liczbe oraz jej priorytet (ilosc wystapien)
    {
        int priority;
        int value;

        Node(int p, int v)
        {
            priority = p;
            value = v;
        }
    }

    public static class Heap //Jest to klasa w ktorej bede przechowywal tablice oraz informacje o jej rozmiarze
    {
        int maxN; //maksymalna ilosc roznych elementow
        int maxP; //maksymalna ilosc takich samych elementow
        int numN; //ilosc aktualnie roznych elementow
        int numP; //ilosc wszystkich elementow
        Node[] queue;

        Heap(int N, int P)
        {
            queue = new Node[N];
            maxN = N;
            maxP = P;
            numN = 0;
            numP = 0;
        }

        void insert(int x)
        {
            if(numP >= maxP)
            {
                return;
            }

            if(find(x)) //najpierw sprawdzam czy nie ma juz elementu o takim samym numerze, wtedy w funkcji find zwiekszam priorytet i wykonuje Upheap
            {
                numP++;
            }
            else
            {
                if(numN >= maxN)
                {
                    return;
                }
                else
                {
                    Node node = new Node(1,x); //jesli nie ma elementu a mozna go wstawic to wstawiam go i wykonuje funkcje upheap
                    queue[numN] = node;
                    upHeap(numN);
                    numN++;
                    numP++;
                }
            }
        }
        boolean find(int x)
        {
            for(int i = 0; i < numN; i++)
            {
                if(queue[i].value == x)
                {
                    queue[i].priority++;
                    upHeap(i);
                    return true;
                }
            }
            return false;
        }

        void upHeap(int index)
        {
            int i = (index-1)/2;
            Node tmp = queue[index];
            while (index > 0 && compare(queue[i], tmp)){
                queue[index] = queue[i];
                index = i ;
                i = (i-1)/2;
            }
            queue[index] = tmp;
        }

        boolean compare(Node a, Node b) //funkcja ktora porownuje priorytety
        {
            if(a.priority < b.priority)
            {
                return true;
            }
            else if (a.priority == b.priority && a.value < b.value)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        void getMax(int k)
        {
            if(numP == 0)
            {
                System.out.println(0 + " " + 0);
                return;
            }

            if(queue[0].priority <= k) //jezeli priorytet elementu jest mniejszy/rowny od k to usuwam element i wykonuje downheap
            {
                System.out.println(queue[0].value + " " + queue[0].priority);
                numP -= queue[0].priority;
                queue[0] = queue[numN - 1];
                queue[numN - 1] = null;
                numN--;
                downHeap(0,numN);
            }
            else //jezeli jest wiekszy to zmienjszam go i wykonuje downheap
            {
                System.out.println(queue[0].value + " " + k);
                numP -= k;
                queue[0].priority -= k;
                downHeap(0,numN);
            }

        }


       void downHeap(int k, int n) //downheap taki jak na wykladzie

       {
           int j;
           Node tmp = queue[k];
           while ( k < n / 2 ) {
               j = 2*k+1;
               if ( j < n-1 && compare(queue[j], queue[j+1]) ) j++;
               if (compare(queue[j], tmp)) break ;
               queue[k] = queue[j] ;
               k = j ;
           }
           queue[k] = tmp;
       }



        void heapSort() //heapsort taki jak na wykladzie
        {
            int size = numN;

            for (int i = (size / 2) - 1; i >= 0; i--) {
                downHeap(i, size);
            }

            for (int i = size - 1; i > 0; i--) {
                swap(0, i);
                downHeap(0, i);
            }
        }

        void swap(int i, int j) {
            Node temp = queue[i];
            queue[i] = queue[j];
            queue[j] = temp;
        }

        void printQueue() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numN; i++) {
                sb.append(queue[i].value).append(" ").append(queue[i].priority).append(" ");
            }
            System.out.println(sb.toString().trim());
        }



    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sets = in.nextInt();
        for(int i = 0; i < sets; i++)
        {
            int N = in.nextInt();
            int P = in.nextInt();
            Heap heap = new Heap(N, P);
            while (true)
            {
                String operation = in.next();
                if(operation.charAt(0) == 'i')
                {
                    int k = in.nextInt();
                    for(int j = 0; j < k; j++)
                    {
                        heap.insert(in.nextInt());
                    }
                }
                else if (operation.charAt(0) == 's')
                {
                    heap.heapSort();
                    heap.printQueue();
                    break;
                }
                else if(operation.charAt(0) == 'g')
                {
                    heap.getMax(in.nextInt());
                }
            }
        }
    }
}


//TESTY:

/*
in:
3
3 7
i 3 2 2 2 g 1 s
6 8
i 6 1 2 3 4 5 1 g 2 s
8 15
i 5 1 2 3 4 5 i 7 1 2 3 4 5 6 7 g 392 i 3 5 6 5 g 3 s


out:
2 1
2 2
1 2
2 1 3 1 4 1 5 1
5 2
6 2
7 1 1 2 2 2 3 2 4 2 5 2
*/