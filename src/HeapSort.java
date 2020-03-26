import javafx.scene.paint.Stop;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void heap(int[] arr,int root,int last){

        for(int i=2*root+1;i<=last;i=i*2+1){
            if(i<last&&arr[i]<arr[i+1])i++;
            if(arr[i]>arr[root]){
                swap(arr, i, root);
                root=i;
            }else break;
        }

    }
    public static void heapSort(int[] arr){
        for(int i=arr.length/2-1;i>=0;i--){
            heap(arr, i, arr.length-1);
        }
        for(int i=arr.length-1;i>0;i--){
            swap(arr, 0, i);
            heap(arr, 0, i-1);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }
    public static int partition(int[] arr,int low,int high){
        int sentinel=arr[low];
        while(low<high){
            while (low<high&&arr[high]>=sentinel)high--;
            arr[low]=arr[high];
            while ((low<high)&&arr[low]<=sentinel)low++;
            arr[high]=arr[low];
        }
        arr[low]=sentinel;
        return low;
    }
    public static void quickSort(int[] arr,int low,int high){
        if(low<high){
            int pivot=partition(arr, low, high);
            quickSort(arr, low, pivot-1);
            quickSort(arr,pivot+1,high);
        }
    }

    public static void main(String[] args) {

        Random random=new Random(System.currentTimeMillis());
        int[] ints = random.ints().limit(10000000).toArray();
        int [] ints1=Arrays.copyOf(ints, ints.length);
        int [] ints2=Arrays.copyOf(ints, ints.length);
        Long start=System.currentTimeMillis();
        heapSort(ints);
        Long end=System.currentTimeMillis();
        System.out.println((end - start));
        start=System.currentTimeMillis();
        quickSort(ints1,0, ints.length-1);
        end=System.currentTimeMillis();
        System.out.println((end - start));
        start=System.currentTimeMillis();
        bubbleSort(ints2);
        end=System.currentTimeMillis();
        System.out.println(end-start);

    }
    public static void bubbleSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=1;j<arr.length-i;j++){
                if(arr[j-1]>=arr[j]){
                    swap(arr, j-1, j);
                }
            }
        }
    }
}
