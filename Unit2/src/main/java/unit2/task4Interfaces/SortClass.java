package unit2.task4Interfaces;

import unit2.task3stationaryhierarchy.Abstract.Stationary;
import unit2.task3stationaryhierarchy.PaperItems.Concrete.Book;
import unit2.task3stationaryhierarchy.PaperItems.Concrete.Notebook;
import unit2.task3stationaryhierarchy.WritingImplements.Concrete.Brush;
import unit2.task3stationaryhierarchy.WritingImplements.Concrete.Pen;
import unit2.task3stationaryhierarchy.WritingImplements.Concrete.Pencil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SortClass {

    public static List<Stationary> getList () {

        List<Stationary> list = new ArrayList<>();
        list.add(new Pen("Blue", BigDecimal.valueOf(37),"Pen"));
        list.add(new Pencil("Unknown", BigDecimal.valueOf(1000), "Pencil"));
        list.add(new Brush("Pink", BigDecimal.valueOf(22), 15, "Brush"));
        list.add(new Book("Some author", BigDecimal.valueOf(44), 77));
        list.add(new Notebook("Orange", BigDecimal.valueOf(32),44));
        list.add(new Book("Green", BigDecimal.valueOf(0.01), 155));
        list.add(new Brush("Dark", BigDecimal.valueOf(227), 15, "Brush"));
        list.add(new Pen("Blue", BigDecimal.valueOf(32.43),"Pen"));
        list.add(new Pen("Blue", BigDecimal.valueOf(25),"Pen"));

        return list;
    }

    }

