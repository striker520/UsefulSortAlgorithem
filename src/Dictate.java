import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Dictate {

    private static class HeapSort{
        private static <T extends Comparable<T>>void swap(T[] arr,int a,int b){
            T t=arr[a];
            arr[a]=arr[b];
            arr[b]=t;
        }


        private static <T extends Comparable<T>> void heapAdjust(T[] objs,int root,int last){
            for(int i=root*2+1;i<=last;i=i*2+1){
                if(i<last&&(objs[i].compareTo(objs[i+1]))<0)i++;
                if(objs[i].compareTo(objs[root])>0){
                    swap(objs,i,root);
                    root=i;
                }else break;
            }
        }
        private static <T extends Comparable<T>> void heapSort(T[] objs){
            for(int i=objs.length/2-1;i>=0;i--){
                heapAdjust(objs, i, objs.length-1);
            }
            for(int i=objs.length-1;i>0;i--){
                swap(objs,0,i);
                heapAdjust(objs, 0, i-1);
            }

        }

    }
    private static class QuickSort{
        private static <T extends Comparable<T>> T selectPivot(T[] arr,int low ,int high){
            int mid=(low+high)>>1;
            if(arr[mid].compareTo(arr[high])>0)HeapSort.swap(arr, mid, high);//mid<high
            if(arr[mid].compareTo(arr[low])>0)HeapSort.swap(arr,mid,low);//mid<low
            if(arr[low].compareTo(arr[high])>0)HeapSort.swap(arr, low, high);//low<high
            return arr[low];
        }
        private static <T extends Comparable<T>> int partition(T[] arr,int low,int high){
            T pivot=selectPivot(arr, low, high);
            while(low<high){
                while(low<high&&arr[high].compareTo(pivot)>=0)high--;
                arr[low]=arr[high];
                while(low<high&&arr[low].compareTo(pivot)<=0)low++;
                arr[high]=arr[low];
            }
            arr[low]=pivot;
            return low;
        }
        private static <T extends Comparable<T>> void quickSort(T[] arr,int low,int high){
            if(low<high){
                int partition =partition(arr, low, high);
                quickSort(arr, low, partition-1);
                quickSort(arr, partition+1, high);
            }
        }
    }

    private static class MergeSort{

        private static <T extends Comparable<T>> void SortRecursive(T[] arr,Class<T> clazz){
            @SuppressWarnings({"unckeked","hidden"})
            T[] temp = (T[]) Array.newInstance(clazz, arr.length);
            SortRecursive(arr, temp, 0, arr.length-1);
        }
        private static <T extends Comparable<T>> void SortUnRecursive(T[] arr,Class<T> clazz){
            @SuppressWarnings({"unckeked","hidden"})
            T[] temp = (T[]) Array.newInstance(clazz, arr.length);
            SortUnRecursive(arr, temp);
        }

        private static <T extends Comparable<T>> void SortRecursive(T[] arr,T[] temp,int low,int high){
            if(low<high){
                int mid=(low+high)>>1;
                SortRecursive(arr, temp,low, mid);
                SortRecursive(arr,temp ,mid+1, high);
                merge(arr,temp,low,mid,high);
            }
        }
        private static <T extends Comparable<T>> void SortUnRecursive(T[] arr,T[] temp){
            int t=1;//初始状态,每个元素都是一个有序序列
            while(t<arr.length){
                mergePass(arr,temp,t);
                t<<=1;
                mergePass(temp,arr,t);
                t<<=1;
            }
        }

        private static <T extends Comparable<T>> void mergePass(T[] a, T[] b, int t) {//按照当前每组的长度,a两两归并到b
            int i=0;
            while(i+(t<<1)<a.length){
                merge(a, b, i, i+t-1, i+(t<<1)-1);
                i+=t<<1;
            }
            if(i+t<a.length){
                merge(a, b, i, i+t-1, a.length-1);
            }else{
                if (a.length - i >= 0) System.arraycopy(a, i, b, i, a.length - i);
            }

        }

        private static <T extends Comparable<T>> void mergeUnCopy(T[] arr, T[] temp,int low, int mid,int high) {
            int i=low;
            int j=mid+1;
            int k=i;
            while(i<=mid&&j<=high){
                if(arr[i].compareTo(arr[j])<=0)temp[k++]=arr[i++];
                else temp[k++]=arr[j++];
            }
            while(i<=mid)temp[k++]=arr[i++];
            while(j<=high)temp[k++]=arr[j++];
        }

        private static <T extends Comparable<T>> void merge(T[] arr, T[] temp,int low, int mid,int high) {
            int i=low;
            int j=mid+1;
            int k=i;
            while(i<=mid&&j<=high){
                if(arr[i].compareTo(arr[j])<=0)temp[k++]=arr[i++];
                else temp[k++]=arr[j++];
            }
            while(i<=mid)temp[k++]=arr[i++];
            while(j<=high)temp[k++]=arr[j++];
            if (high + 1 - low >= 0) System.arraycopy(temp, low, arr, low, high + 1 - low);
        }


    }

    public static void main(String[] args) {
        Random random=new Random(System.currentTimeMillis());
        Integer[] ints1 = random.ints(0,100).limit(30).boxed().toArray(Integer[]::new);
        Integer[] ints2=Arrays.copyOf(ints1, ints1.length);
        Integer[] ints3=Arrays.copyOf(ints1, ints1.length);
        Integer[] ints4=Arrays.copyOf(ints1, ints1.length);
        Long start,stop;


        System.out.println("随机序列: "+Arrays.toString(ints1));


        start=System.currentTimeMillis();
        HeapSort.heapSort(ints1);
        System.out.println(Arrays.toString(ints1));
        stop=System.currentTimeMillis();
        System.out.println("堆排用时: "+(stop-start));


        start=System.currentTimeMillis();
        QuickSort.quickSort(ints2, 0, ints2.length-1);
        System.out.println(Arrays.toString(ints2));
        stop=System.currentTimeMillis();
        System.out.println("快排用时 : "+(stop-start));

        start=System.currentTimeMillis();
        MergeSort.SortRecursive(ints3, Integer.class);
        System.out.println(Arrays.toString(ints3));
        stop=System.currentTimeMillis();
        System.out.println("递归二路归并排序用时: "+(stop-start));

        start=System.currentTimeMillis();
        MergeSort.SortUnRecursive(ints4, Integer.class);
        System.out.println(Arrays.toString(ints4));
        stop=System.currentTimeMillis();
        System.out.println("非递归二路归并排序用时: "+(stop - start));


    }
}
