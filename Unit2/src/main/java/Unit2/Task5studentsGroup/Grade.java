package Unit2.Task5studentsGroup;

public class Grade {

    private Number grade;

    Grade (Number grade){
        if(grade.longValue()<1&&grade.longValue()>10)
            throw new IllegalArgumentException(("Inappropriate grade"));

        this.grade=grade;
    }

    public Number getValue() {
        return grade;
    }

    @Override
    public String toString() {
        return grade.toString();
    }
}
