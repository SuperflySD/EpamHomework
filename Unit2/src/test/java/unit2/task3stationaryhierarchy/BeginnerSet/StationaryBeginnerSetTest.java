package unit2.task3stationaryhierarchy.BeginnerSet;

import unit2.task3stationaryhierarchy.Abstract.Stationary;
import unit2.task3stationaryhierarchy.PaperItems.Concrete.Book;
import unit2.task3stationaryhierarchy.PaperItems.Concrete.Notebook;
import unit2.task3stationaryhierarchy.WritingImplements.Abstract.WritingImplements;
import unit2.task3stationaryhierarchy.WritingImplements.Concrete.Brush;
import unit2.task3stationaryhierarchy.WritingImplements.Concrete.Pen;
import unit2.task3stationaryhierarchy.WritingImplements.Concrete.Pencil;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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