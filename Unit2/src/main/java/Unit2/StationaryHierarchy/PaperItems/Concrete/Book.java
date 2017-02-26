package Unit2.StationaryHierarchy.PaperItems.Concrete;

import Unit2.StationaryHierarchy.PaperItems.Abstract.PaperItems;

import java.math.BigDecimal;
import java.util.Locale;

public class Book extends PaperItems {
    private String author;

    /**
     * @param author      any author
     * @param cost        cost in rubles
     * @param totalSheets totalSheets of brush
     * @throws IllegalArgumentException if one o more arguments reference to null or equal zero
     */

    public Book(String author, BigDecimal cost, int totalSheets) {
        super(cost, totalSheets);
        if (totalSheets <= 0)
            throw new IllegalArgumentException("totalSheets can't be equal zero");

        this.author = author;
    }

    public int getauthor() {
        return totalSheets;
    }

    public void setauthor(int totalSheets) {
        this.totalSheets = totalSheets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        return super.cost.compareTo(((Book) o).cost) == 0 && super.totalSheets == (((Book) o).totalSheets)
                && author.equals(((Book) o).author);
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + super.cost.intValue() + super.totalSheets;
        return result;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s (author - %s, cost - %.2f, totalSheets - %d)",
                super.name, author, super.cost, super.totalSheets);
    }
}
