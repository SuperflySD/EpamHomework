package SD.JavaCourses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitSet {
    private long [] positiveData = new long [1];
    private long [] negativeData = new long [1];
    private  int quotient;
    private  int remainder;
    private long counter = 0;

    public void add(int... i) {
        for (int value : i) {
            divideBy64(value);
            if (value >= 0) {
                if (quotient >= positiveData.length) {
                    positiveData = Arrays.copyOfRange(positiveData, 0, quotient + 1);
                }
                positiveData[quotient] |= 1L << remainder;

            } else {
                quotient = Math.abs(quotient);
                if (quotient >= negativeData.length) {
                    negativeData = Arrays.copyOfRange(negativeData, 0, quotient + 1);
                }
                negativeData[quotient] |= 1L << (-remainder);
            }
            counter++;
        }
    }
     public boolean contains(int value) {
        divideBy64(value);
         if (value >= 0) {
             if(quotient+1>positiveData.length)
                 return false;

             long temp = positiveData[quotient];
             return Long.lowestOneBit(temp >> remainder) == 1 ? true : false;
         }
         else{
             quotient=Math.abs(quotient);
             if(quotient +1 > negativeData.length)
                 return false;

             long temp = negativeData[quotient];
             return Long.lowestOneBit(temp >> -remainder) == 1 ? true : false;
         }
     }

    public void remove (int value) {
        divideBy64(value);
        if (value >= 0)
           positiveData[quotient] = positiveData[quotient]&0L<<remainder;
        else {
            quotient = Math.abs(quotient);
            negativeData[quotient] = negativeData[quotient] & 0L << remainder;
        }
        counter--;
    }

    public int[] getAllValues () {
       int[] arr = new int[(int)counter];
       int count=0;
        for (int i = negativeData.length; i >= 0; i--)
            for (int j = 64; j > 0; j--) {
                if (contains(-i*64-j))
                    arr[count++] = (-i*64-j);
            }
        for (int i = 0; i <= positiveData.length; i++)
            for (int j = 0; j < 64 ; j++) {
                if (contains(i*64+j))
                    arr[count++]=(i*64+j);
            }
        return arr;
    }

    public BitSet unite (BitSet inputSet) {
         BitSet retSet = new BitSet();
        int more = negativeData.length >= inputSet.negativeData.length ? negativeData.length : inputSet.negativeData.length;
        int less = negativeData.length  < inputSet.negativeData.length ? negativeData.length : inputSet.negativeData.length;
        retSet.negativeData = new long [more];
        for (int i=0;i<less;i++)
            retSet.negativeData[i] = negativeData[i] | inputSet.negativeData[i];

        retSet.positiveData = new long [more];
        for (int i=0;i<less;i++)
            retSet.positiveData[i] = positiveData[i] | inputSet.positiveData[i];

        return retSet;
    }

    public BitSet intersect (BitSet inputSet){
        BitSet retSet = new BitSet();
        int more = negativeData.length >= inputSet.negativeData.length ? negativeData.length : inputSet.negativeData.length;
        int less = negativeData.length  < inputSet.negativeData.length ? negativeData.length : inputSet.negativeData.length;
        retSet.negativeData = new long [more];
        for (int i=0;i<less;i++)
            retSet.negativeData[i] = negativeData[i] & inputSet.negativeData[i];

        retSet.positiveData = new long [more];
        for (int i=0;i<less;i++)
            retSet.positiveData[i] = positiveData[i] & inputSet.positiveData[i];

        return retSet;
    }


    public BitSet difference(BitSet inputSet){
        BitSet retSet = new BitSet();
        int more = negativeData.length >= inputSet.negativeData.length ? negativeData.length : inputSet.negativeData.length;
        int less = negativeData.length  < inputSet.negativeData.length ? negativeData.length : inputSet.negativeData.length;
        retSet.negativeData = new long [more];
        for (int i=0;i<less;i++)
            retSet.negativeData[i] = negativeData[i] ^ inputSet.negativeData[i];

        retSet.positiveData = new long [more];
        for (int i=0;i<less;i++)
            retSet.positiveData[i] = positiveData[i] ^ inputSet.positiveData[i];

        return retSet;
    }

    public boolean isSubsetOf(BitSet inputSet){
        BitSet intersectedSet = intersect(inputSet);
        return  Arrays.equals(intersectedSet.negativeData,negativeData) &&
                Arrays.equals(intersectedSet.positiveData,positiveData);
    }


    public long countElements(){
        return counter;
    }


    private void divideBy64(int dividend){
        if (dividend>=0) {
            quotient = dividend / 64;
            remainder = dividend % 64;
        }
        else {
            quotient = (dividend+1) / 64;
            remainder = (dividend+1) % 64;
        }
    }

}
