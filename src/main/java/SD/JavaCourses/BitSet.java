package SD.JavaCourses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitSet {
    private long [] positiveData = new long [1];
    private long [] negativeData = new long [1];
    private  int quotient;
    private  int remainder;

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
        else
            negativeData[-quotient] = negativeData[-quotient] & 0L << remainder;

    }

    public ArrayList<Integer> getAllValues () {
    ArrayList<Integer> list = new ArrayList<Integer> (); 
        for (int i = negativeData.length; i >= 0; i--)
            for (int j = 64; j > 0; j--) {
                if (contains(-i*64-j))
                    list.add (-i*64-j);
            }
        for (int i = 0; i <= positiveData.length; i++)
            for (int j = 0; j < 64 ; j++) {
                if (contains(i*64+j))
                    list.add(i*64+j);
            }
        return list;
    }

    public BitSet unite (BitSet inputSet) {
        BitSet retSet = new BitSet();
        retSet.negativeData = (negativeData.length >= inputSet.negativeData.length) ?
                Arrays.copyOf(negativeData, negativeData.length): Arrays.copyOf(negativeData, negativeData.length);

        for (int i=0;i<retSet.negativeData.length;i++)
                 retSet.negativeData[i] = negativeData[i] | inputSet.negativeData[i];

        retSet.positiveData = (positiveData.length >= inputSet.positiveData.length) ?
                Arrays.copyOf(positiveData, positiveData.length): Arrays.copyOf(positiveData, positiveData.length);

        for (int i=0;i<inputSet.positiveData.length;i++)
                 retSet.positiveData[i] = positiveData[i] | inputSet.positiveData[i];
        return retSet;
    }

    public BitSet intersect (BitSet inputSet){
        BitSet retSet = new BitSet();
        int less = negativeData.length  < inputSet.negativeData.length ? negativeData.length : inputSet.negativeData.length;
        retSet.negativeData = new long [less];
        for (int i=0;i<less;i++)
            retSet.negativeData[i] = negativeData[i] & inputSet.negativeData[i];

        less = positiveData.length  < inputSet.positiveData.length ? positiveData.length : inputSet.positiveData.length;
        retSet.positiveData = new long [less];
        for (int i=0;i<less;i++)
            retSet.positiveData[i] = positiveData[i] & inputSet.positiveData[i];

        return retSet;
    }


    public BitSet difference(BitSet inputSet){
        BitSet retSet = new BitSet();
        retSet.negativeData = (negativeData.length >= inputSet.negativeData.length) ?
                Arrays.copyOf(negativeData, negativeData.length): Arrays.copyOf(negativeData, negativeData.length);

        for (int i=0;i<retSet.negativeData.length;i++)
                 retSet.negativeData[i] = negativeData[i] ^ inputSet.negativeData[i];

        retSet.positiveData = (positiveData.length >= inputSet.positiveData.length) ?
                Arrays.copyOf(positiveData, positiveData.length): Arrays.copyOf(positiveData, positiveData.length);

        for (int i=0;i<inputSet.positiveData.length;i++)
            retSet.positiveData[i] = positiveData[i] ^ inputSet.positiveData[i];
        return retSet;
    }

    public boolean isSubsetOf(BitSet inputSet){
        BitSet intersectedSet = intersect(inputSet);
        return  Arrays.equals(intersectedSet.negativeData,negativeData) &&
                Arrays.equals(intersectedSet.positiveData,positiveData);
    }


    public int countElements(){
       int counter=0;
       for (long unit: negativeData) 
            counter+=Long.bitCount(unit);
       for (long unit: positiveData) 
            counter+=Long.bitCount(unit);      
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


