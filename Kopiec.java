public class Main
{
    public static void downHeap(int[] array, int index, int size)
    {
        int j;
        int temp = array[index];
        while (index < size / 2)
        {
            j = 2 * index + 1;
            if(j < size - 1 && array[j] > array[j+1])
            {
                j++;
            }
            if(temp <= array[j])
            {
                break;
            }
            array[index] = array[j];
            index = j;
        }
        array[index] = temp;
    }
    public static void heapSort(int[] array, int size)
    {
        for(int i = (size - 1)/2; i >= 0; i--)
        {
            downHeap(array,i,size);
        }
        while(size > 0)
        {
            int t = array[0];
            array[0] = array[size - 1];
            array[size - 1] = t;
            size--;
            downHeap(array,0,size);
        }
    }

    public static void print(int[] array, int size)
    {
        for(int i = 0; i < size; i++)
        {
            System.out.print(array[i] + " ");
        }
    }
    public static void main(String[] args)
    {

        int[] arr = {54, 23, 76, 12, 89, 34, 67, 45, 91, 18};
        heapSort(arr,10);
        print(arr,10);

        /*
         mamy tablice: {54, 23, 76, 12, 89, 34, 67, 45, 91, 18};

         faza 1: tworzymy z niej kopiec
            downheap(4,10): {54, 23, 76, 12, 18, 34, 67, 45, 91, 89}
            downheap(3,10): {54, 23, 76, 12, 18, 34, 67, 45, 91, 89}
            downheap(2,10): {54, 23, 34, 12, 18, 76, 67, 45, 91, 89}
            downheap(1,10): {54, 12, 34, 23, 18, 76, 67, 45, 91, 89}
            downheap(0,10): {12, 18, 34, 23, 54, 76, 67, 45, 91, 89}

        faza 2: tutaj elementy najmniejsze ida na koniec
            swap(0,9) i downheap(0,9): {18, 23, 34, 45, 54, 76, 67, 89, 91, 12}
            swap(0,8) i downheap(0,8): {23, 45, 34, 89, 54, 76, 67, 91, 18, 12}
            swap(0,7) i downheap(0,7): {34, 45, 67, 89, 54, 76, 91, 23, 18, 12}
            swap(0,6) i downheap(0,6): {45, 54, 67, 89, 91, 76, 34, 23, 18, 12}
            swap(0,5) i downheap(0,5): {54, 76, 67, 89, 91, 45, 34, 23, 18, 12}
            swap(0,4) i downheap(0,4): {67, 76, 91, 89, 54, 45, 34, 23, 18, 12}
            swap(0,3) i downheap(0,3): {76, 89, 91, 67, 54, 45, 34, 23, 18, 12}
            swap(0,2) i downheap(0,2): {89, 91, 76, 67, 54, 45, 34, 23, 18, 12}
            swap(0,1) i downheap(0,1): {91, 89, 76, 67, 54, 45, 34, 23, 18, 12}
            swap(0,0) i downheap(0,0): {91, 89, 76, 67, 54, 45, 34, 23, 18, 12}
        */
    }
}