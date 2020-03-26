import java.util.Arrays;
public class QuickSort {
    public static int getSentinel( int[] arr,int low,int high){
        int mid=(low+high)/2;
        int[] t=new int[3];
        t[0]=arr[low];
        t[1]=arr[mid];
        t[2]=arr[high];
       if(t[0]>t[1])swap(t, 0, 1);
       if(t[1]>t[2])swap(t, 1, 2);
       if(t[0]>t[1])swap(t, 0, 1);
        return t[1];
    }
   public static int  partition(int[] arr,int low,int high){
       int pivot=getSentinel(arr, low, high);
       while(low<high){
           while(low<high&&pivot<=arr[high])high--;
           arr[low]=arr[high];
           while(low<high&&pivot>=arr[low])low++;
           arr[high]=arr[low];
       }
       arr[low]=pivot;
       return low;
    }
    public static void quickSort(int[] arr,int low,int high){
        if(low<high){
            int pivot=partition(arr,low, high);
            quickSort(arr, low, pivot-1);
            quickSort(arr, pivot+1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,4,6,3,8,33,56,23,9};

        System.out.println(getSentinel(arr, 0, 8));

        quickSort(arr, 0, 8);
        System.out.println(Arrays.toString(arr));
    }
    public static void swap(int[] arr,int a,int b){
        int t=arr[a];
        arr[a]=arr[b];
        arr[b]=t;

    }


}
