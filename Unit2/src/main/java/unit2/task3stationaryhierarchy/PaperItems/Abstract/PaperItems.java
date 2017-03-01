package unit2.task3stationaryhierarchy.PaperItems.Abstract;

import unit2.task3stationaryhierarchy.Abstract.Stationary;

import java.math.BigDecimal;

public abstract class PaperItems extends Stationary {
    protected int totalSheets;

   protected PaperItems(BigDecimal cost, int totalSheets){
       super(cost);
       if (totalSheets==0)
           throw new IllegalArgumentException("totalSheets can't be equal zero");
        this.totalSheets=totalSheets;
    }

    public int gettotalSheets() {
        return totalSheets;
    }

    public void settotalSheets(int totalSheets) {
        this.totalSheets = totalSheets;
    }


}
