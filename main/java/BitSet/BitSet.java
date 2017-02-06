package BitSet;

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
            divideBy63(value);
            if (value >= 0) {
                if (quotient >= positiveData.length) {
                    positiveData = Arrays.copyOfRange(positiveData, 0, quotient + 1);
                }
                positiveData[quotient] |= 1L << remainder;
                // System.out.println(Long.toBinaryString(positiveData[quotient]));
            } else {
                quotient = Math.abs(quotient);
                if (quotient >= negativeData.length) {
                    negativeData = Arrays.copyOfRange(negativeData, 0, quotient + 1);
                }
                negativeData[quotient] |= 1L << (-remainder);
                //System.out.println(Long.toBinaryString(negativeData[quotient]));
            }
        }
    }
     public boolean contains(int value) {
        divideBy63(value);
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
        divideBy63(value);
        if (value >= 0)
           positiveData[quotient] = positiveData[quotient]&0L<<remainder;
        else {
            quotient = Math.abs(quotient);
            negativeData[quotient] = negativeData[quotient] & 0L << remainder;
        }
    }

    public List<Integer> getAllValues () {
        ArrayList<Integer> list = new ArrayList<Integer>( 64 * negativeData.length);
        for (int i = negativeData.length; i >= 0; i--)
            for (int j = 64; j > 0; j--) {
                if (contains(-i*64-j))
                    list.add(-i*64-j);
            }
        for (int i = 0; i <= positiveData.length; i++)
            for (int j = 0; j < 64 ; j++) {
                if (contains(i*64+j))
                    list.add(i*64+j);
            }
        return list;
    }

    public BitSet unite (BitSet inputSet) throws NoSuchFieldException, IllegalAccessException {
        long [] negInput = (long[])inputSet.getClass().getDeclaredField("negativeData").get(inputSet);
        if (negativeData.length>=negInput.length)
            for (int i=0;i<negInput.length;i++)
                negativeData[i]|=negInput[i];
        else {
            for (int i = 0; i < negativeData.length; i++)
                negInput[i] |= negativeData[i];
        negativeData = negInput;
        }

        long [] posInput = (long[])inputSet.getClass().getDeclaredField("positiveData").get(inputSet);
        if (positiveData.length>=posInput.length)
            for (int i=0;i<posInput.length;i++)
                positiveData[i]|=posInput[i];
        else {
            for (int i = 0; i < positiveData.length; i++)
                posInput[i] |= positiveData[i];
        positiveData = posInput;
        }
        return this;
    }

    public BitSet intersect (BitSet inputSet) throws NoSuchFieldException, IllegalAccessException{
        long[] newNegativeData = Arrays.copyOf(negativeData, negativeData.length);
        long [] negInput = (long[])inputSet.getClass().getDeclaredField("negativeData").get(inputSet);
        if (newNegativeData.length>=negInput.length)
            for (int i=0;i<negInput.length;i++)
                newNegativeData[i]&=negInput[i];
        else {
            for (int i = 0; i < newNegativeData.length; i++)
                negInput[i] &= newNegativeData[i];
            newNegativeData = negInput;
        }
        long[] newPositiveData = Arrays.copyOf(positiveData, positiveData.length);
        long [] posInput = (long[])inputSet.getClass().getDeclaredField("positiveData").get(inputSet);
        if (newPositiveData.length>=posInput.length)
            for (int i=0;i<posInput.length;i++)
                newPositiveData[i]&=posInput[i];
        else {
            for (int i = 0; i < newPositiveData.length; i++)
                posInput[i] &= newPositiveData[i];
            newPositiveData = posInput;
        }
        return new BitSet(newNegativeData,newPositiveData);
    }

    public BitSet difference(BitSet inputSet) throws NoSuchFieldException, IllegalAccessException{
        long [] negInput = (long[])inputSet.getClass().getDeclaredField("negativeData").get(inputSet);
        if (negativeData.length>=negInput.length)
            for (int i=0;i<negInput.length;i++)
                negativeData[i]^=negInput[i];
        else {
            for (int i = 0; i < negativeData.length; i++)
                negInput[i] ^= negativeData[i];
            negativeData = negInput;
        }

        long [] posInput = (long[])inputSet.getClass().getDeclaredField("positiveData").get(inputSet);
        if (positiveData.length>=posInput.length)
            for (int i=0;i<posInput.length;i++)
                positiveData[i]^=posInput[i];
        else {
            for (int i = 0; i < positiveData.length; i++)
                posInput[i] ^= positiveData[i];
            positiveData = posInput;
        }
        return this;
    }

    public boolean isSubsetOf(BitSet inputSet) throws NoSuchFieldException, IllegalAccessException{
        BitSet intersectedSet = intersect(inputSet);
        long [] negInput = (long[])intersectedSet.getClass().getDeclaredField("negativeData").get(intersectedSet);
        boolean negBool = Arrays.equals(negInput,negativeData);

        long [] posInput = (long[])intersectedSet.getClass().getDeclaredField("positiveData").get(intersectedSet);
        boolean posBool = Arrays.equals(posInput,positiveData);

        return negBool&&posBool;
    }


    private void divideBy63(int dividend){
        if (dividend>=0) {
            quotient = dividend / 63;
            remainder = dividend % 63;
        }
        else {
            quotient = (dividend+1) / 63;
            remainder = (dividend+1) % 63;
        }
    }

    public BitSet(long[] negativeData, long[] positiveData) {
        this.positiveData = positiveData;
        this.negativeData = negativeData;
    }
    public BitSet() {
    }
}
