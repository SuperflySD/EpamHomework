package Unit2.StationaryHierarchy.WritingImplements.Abstract;

import Unit2.StationaryHierarchy.Abstract.Stationary;

import java.math.BigDecimal;

public abstract class WritingImplements extends Stationary {
    protected String color;

   protected WritingImplements(BigDecimal cost, String color, String name){
       super(cost, name);
       if (color==null || color.length()==0)
           throw new IllegalArgumentException("One o more arguments reference to null or equal zero");
        this.color=color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
