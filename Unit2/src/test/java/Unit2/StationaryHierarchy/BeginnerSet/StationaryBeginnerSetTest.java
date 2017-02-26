package Unit2.StationaryHierarchy.BeginnerSet;

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
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;


public class StationaryBeginnerSetTest {
    @Test
    public void createBeginnerSet() throws Exception {

        List<Stationary> beginnerSet = new ArrayList<>();
        beginnerSet.add(new Pen("Blue", BigDecimal.valueOf(37),"Pen"));
        beginnerSet.add(new Pencil("Unknown", BigDecimal.valueOf(1000), "Pencil"));
        beginnerSet.add(new Brush("Pink", BigDecimal.valueOf(22), 15, "Brush"));
        beginnerSet.add(new Book("Some author", BigDecimal.valueOf(44), 77));
        beginnerSet.add(new Notebook("Orange", BigDecimal.valueOf(32),44));
        beginnerSet.add(new WritingImplements(BigDecimal.valueOf(0.0000001), "Nonexistent color", "Unknown item") {
            @Override
            public String toString() {
                return "unexpected writing implement";
            }
        });

        for (Stationary st : beginnerSet)
            System.out.println(st);



    }

}