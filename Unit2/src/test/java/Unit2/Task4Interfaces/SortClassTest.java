package Unit2.Task4Interfaces;

import Unit2.StationaryHierarchy.Abstract.Stationary;
import Unit2.StationaryHierarchy.PaperItems.Concrete.Book;
import Unit2.StationaryHierarchy.PaperItems.Concrete.Notebook;
import Unit2.StationaryHierarchy.WritingImplements.Abstract.WritingImplements;
import Unit2.StationaryHierarchy.WritingImplements.Concrete.Brush;
import Unit2.StationaryHierarchy.WritingImplements.Concrete.Pen;
import Unit2.StationaryHierarchy.WritingImplements.Concrete.Pencil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import static org.junit.Assert.*;

public class SortClassTest {
    List<Stationary> list = SortClass.getList();

    @Test
    public void sortTestCost() throws Exception {

        Comparator<Stationary> comparatorCost = (x, y) -> x.getCost().subtract(y.getCost()).intValue();
        list.sort(comparatorCost);
        for (int j = 1; j < list.size(); j++) {
            assertTrue(list.get(j - 1).getCost().compareTo(list.get(j).getCost()) <= 0);
            System.out.println(list.get(j));
        }
    }

    @Test
    public void sortTestName() throws Exception {

        Comparator<Stationary> comparatorName = Comparator.comparing(Stationary::getName);
        list.sort(comparatorName);
        for (int j = 1; j < list.size(); j++) {
             assertTrue(list.get(j-1).getName().compareTo(list.get(j).getName())<=0);
            System.out.println(list.get(j));
        }
    }

    @Test
    public void sortTestNameandCost() throws Exception {

        Comparator<Stationary> comparatorName = Comparator.comparing(Stationary::getName).
                thenComparing((x, y) -> x.getCost().subtract(y.getCost()).intValue());

        list.sort(comparatorName);
        for (int j = 1; j < list.size(); j++) {
            assertTrue(list.get(j-1).getName().compareTo(list.get(j).getName())<=0);
            System.out.println(list.get(j));
        }
    }




    }

