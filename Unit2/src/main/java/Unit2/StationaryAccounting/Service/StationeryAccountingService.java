package Unit2.StationaryAccounting.Service;

import Unit2.StationaryAccounting.Abstract.StationaryItemInterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**Provides facilities to add, remove and count various stationery items for employee.
 * Any complementary item can be simply added to StationeryAccountingService in case of implementation StationaryItemInterface
 *
 */
public class StationeryAccountingService {
    private List<StationaryItemInterface> itemsList = new ArrayList<>();


    public StationeryAccountingService addItem (StationaryItemInterface item){
        if (item==null)
            throw new NullPointerException("Adding item can't be a null reference");

        itemsList.add(item);
        return this;
    }

    public boolean contains (StationaryItemInterface item){
        if (item==null)
            throw new NullPointerException("Searching item can't be a null reference");

        return itemsList.contains(item);
    }

    /** Removes pointed stationery item from StationaryAccounting
     *
     * @param item  certain stationery item, wishing to be removed
     * @return true if that item exists, else - false
     */
    public boolean remove (StationaryItemInterface item){
        if (item==null)
            throw new NullPointerException("Removing item can't be a null reference");

        return itemsList.remove(item);
    }

    public void clear (){
         itemsList.clear();
    }

    public BigDecimal totalCost(){
        return itemsList.stream().map(item -> item.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
