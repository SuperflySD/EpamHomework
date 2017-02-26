package Unit2.StationaryHierarchy.WritingImplements.Concrete;

import Unit2.StationaryHierarchy.WritingImplements.Abstract.WritingImplements;
import java.math.BigDecimal;
import java.util.Locale;

public class Pencil extends WritingImplements {
    private String     color;
    private BigDecimal cost;

    /**
     * @param color  any color
     * @param cost  cost in rubles
     * @exception    IllegalArgumentException if one o more arguments reference to null or equal zero
     */

    public Pencil(String color, BigDecimal cost, String name) {
        super(cost,color,  name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        return super.cost.compareTo(((Pencil)o).cost)==0 && super.color.equals(((Pencil)o).color);
    }

    @Override
    public int hashCode() {
        int result = super.color.hashCode();
        result = 31 * result + super.cost.intValue();
        return result;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s (color - %s, cost - %.2f)", super.name, super.color, super.cost);
    }
}



