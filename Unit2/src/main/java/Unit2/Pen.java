package Unit2;

import java.math.BigDecimal;
import java.util.Locale;


public class Pen {
  private String     color;
  private BigDecimal price;

  /**
   * @param color  any color you like
   * @param price  price in rubles
   * @exception    IllegalArgumentException if one o more arguments reference to null or equal zero
   */

  public Pen(String color, BigDecimal price) {
    if (color==null || price==null || price.equals(0))
      throw new IllegalArgumentException("One o more arguments reference to null or equal zero");

    this.color = color;
    this.price = price;
  }

  @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      return price.equals(((Pen)o).price) && color.equals(((Pen)o).color);
  }

  @Override
  public int hashCode() {
    int result = color.hashCode();
    result = 31 * result + price.intValue();
    return result;
  }

  @Override
  public String toString() {
    return String.format(Locale.ENGLISH, "Pen (color - %s, price - %.2f)", color, price);
  }
}


