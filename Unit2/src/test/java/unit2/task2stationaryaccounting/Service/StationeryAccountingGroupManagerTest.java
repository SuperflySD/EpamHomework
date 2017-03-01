package unit2.task2stationaryaccounting.Service;

import unit2.task2stationaryaccounting.Concrete.StationeryItems.Pen;
import unit2.task2stationaryaccounting.Concrete.StationeryItems.Pencil;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class StationeryAccountingGroupManagerTest {
    StationeryAccountingService service = new StationeryAccountingService();

    @Test
    public void addItemsWithCorrectValues() throws Exception {
        NullPointerException npe = new NullPointerException();
        try {
            service.addItem(null);
        } catch (NullPointerException e) {
            npe = e;
        } finally {
            assertEquals(npe.getMessage(), "Adding item can't be a null reference");
        }

        try {
            service.addItem(new Pencil("Grey" , new BigDecimal(5.55)));
            npe=new NullPointerException();
        } catch (NullPointerException e) {
            npe = e;
        } finally {
            assertNotEquals(npe.getMessage(), "Adding item can't be a null reference");
        }
    }

    @Test
    public void contains() throws Exception {
        Pen pen = new Pen("Grey" , new BigDecimal(5.55));
        service.addItem(pen);

        assertTrue(service.contains(new Pen("Grey" , new BigDecimal(5.55))));
        assertFalse(service.contains(new Pen("Grey" , new BigDecimal(99))));
    }

    @Test
    public void remove() throws Exception {
        service.clear();
        Pen pen = new Pen("Grey" , new BigDecimal(7.77));

        service.addItem(pen);
        assertTrue(service.contains(pen));

        assertTrue(service.remove(new Pen("Grey" , new BigDecimal(7.77))));
        assertFalse(service.contains(pen));
        assertFalse(service.remove(pen));

    }

    @Test
    public void totalCost() throws Exception {
        service.clear();
        service.addItem(new Pen("Grey" ,  BigDecimal.valueOf(7.77))).
                addItem(new Pen("Black" , BigDecimal.valueOf(27.00))).
                addItem(new Pencil("Red" ,   BigDecimal.valueOf(3)));
        BigDecimal b = service.totalCost();
        assertTrue(service.totalCost().compareTo(BigDecimal.valueOf(37.77))==0);
    }



}