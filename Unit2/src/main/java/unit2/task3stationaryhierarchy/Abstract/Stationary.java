package unit2.task3stationaryhierarchy.Abstract;

import java.math.BigDecimal;

public abstract class Stationary {
    protected BigDecimal cost;
    protected String name = "Unknown item";

    protected Stationary(BigDecimal cost){
        if (cost==null || cost.compareTo(new BigDecimal(0))<=0)
            throw new IllegalArgumentException("One o more arguments reference to null or equal zero");
        this.cost=cost;
    }

    protected Stationary(BigDecimal cost,String name){
        if (cost==null || cost.compareTo(new BigDecimal(0))<=0||name==null|| name.equals(""))
            throw new IllegalArgumentException("One o more arguments reference to null or equal zero");
        this.cost=cost;
        this.name=name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
