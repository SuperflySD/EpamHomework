package Unit2.StationaryHierarchy.PaperItems.Concrete;

import Unit2.StationaryHierarchy.PaperItems.Abstract.PaperItems;

import java.math.BigDecimal;
import java.util.Locale;

public class Notebook extends PaperItems {
       private String color;

    /**
     * @param color  any color
     * @param cost   cost in rubles
     * @param color  color of brush
     * @exception    IllegalArgumentException if one o more arguments reference to null or equal zero
     */

    public Notebook(String color, BigDecimal cost, int totalSheets) {
        super(cost,totalSheets);
        if (color.equals("")||color==null)
            throw new IllegalArgumentException("color can't be equal zero.length or null");

        this.color=color;
    }

    public String getcolor() {
        return color;
    }

    public void setcolor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        return super.cost.compareTo(((Notebook)o).cost)==0 && super.totalSheets==(((Notebook)o).totalSheets)
        && color.equals(((Notebook)o).color);
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + super.cost.intValue() + super.totalSheets;
        return result;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s (color - %s, cost - %.2f, totalSheets - %d)",
                super.name, color, super.cost, super.totalSheets);
    }
}



