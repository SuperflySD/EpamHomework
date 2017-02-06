package MergeSortLoop;


import java.util.Arrays;

public class MergeSortLoop {

    public static void sort(int [] arr) throws Exception {
        if(arr==null || arr.length==0)
            throw new Exception("Empty or uninitialized array was submitted, must have at least one element");

        int size =1;
        while (size<arr.length){
          for(int i=0; i<arr.length;i+=size*2){
            merge(arr, i, i+size, i+size*2-1);
          }
        size*=2;
     }
    }

    private static void merge(int [] arr, int start, int mid, int end) {
        if(mid>=arr.length)
            return;
        if(end>=arr.length)
            end=arr.length-1;

        int [] tempArr = Arrays.copyOfRange(arr, start,end+1);
        int i=0;
        int j=mid-start;
        int k = start;
        while (true) {
            if (tempArr[j] < tempArr[i])
                arr[k++] = tempArr[j++];
            else
                arr[k++] = tempArr[i++];

            if (i == mid-start) {
                for (; k <= end; )
                    arr[k++] = tempArr[j++];
                return;
            }
            if (j == end-start+1) {
                for (; k <= end; )
                    arr[k++] = tempArr[i++];
                return;
            }
        }


    }




}
