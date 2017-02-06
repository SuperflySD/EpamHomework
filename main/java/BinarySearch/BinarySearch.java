package BinarySearch;


public class BinarySearch {

    public static int binarySearch(int [] arr, int start, int end, int value){
            int mid = (end-start)/2+start;

            if (start == end)
                if (arr[start]==value)
                    return start;
                else  {
                    if (start==0)
                        return -start-1;
                    else if (start==arr.length-1)
                        return -start-2;
                }

            if (value < arr[mid])
                 return binarySearch(arr, start, mid, value);
            if (value > arr[mid])
                 return binarySearch(arr, mid+1, end, value);

            else return mid;
        }


    public static int binarySearch(int [] arr, int value){
        return binarySearch(arr, 0, arr.length-1, value);
        }
}

