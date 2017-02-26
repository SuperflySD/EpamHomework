package Unit2.StationaryHierarchy.WritingImplements.Concrete;

import Unit2.StationaryHierarchy.WritingImplements.Abstract.WritingImplements;

import java.math.BigDecimal;
import java.util.Locale;

public class Brush extends WritingImplements {
       private int width;

    /**
     * @param color  any color
     * @param cost   cost in rubles
     * @param width  width of brush
     * @exception    IllegalArgumentException if one o more arguments reference to null or equal zero
     */

    public Brush(String color, BigDecimal cost, int width, String name) {
        super(cost,color, name);
        if (width<=0)
            throw new IllegalArgumentException("Width can't be equal zero");

        this.width=width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        return super.cost.compareTo(((Brush)o).cost)==0 && super.color.equals(((Brush)o).color)&&width==((Brush)o).width;
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + cost.intValue() + width;
        return result;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s (color - %s, cost - %.2f, width - %d)",super.name, super.color, super.cost, width);
    }
}



