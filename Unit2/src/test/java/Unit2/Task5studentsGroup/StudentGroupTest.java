package Unit2.Task5studentsGroup;

import Unit2.Task5studentsGroup.Enums.Student;
import Unit2.Task5studentsGroup.Enums.StudySubject;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentGroupTest {
       StudentGroup group = new StudentGroup(StudySubject.Astronomy, StudySubject.TypeOfGrades.Decimal).
            addStudent(Student.Alekseev, Student.Ivanov, Student.Alekseev, Student.Oblomov, Student.Vodkin);

    @Test
    public void contains() throws Exception {
        System.out.println(group);
        assertTrue(group.containsStudent(Student.Alekseev));
        assertTrue(group.containsStudent(Student.Ivanov));
        assertTrue(group.containsStudent(Student.Oblomov));
        assertFalse(group.containsStudent(Student.Goncharov));
        assertTrue(group.getCountStudents()==4);

    }

    @Test
    public void remove() throws Exception {
        assertTrue(group.containsStudent(Student.Vodkin));
        group.removeStudent(Student.Vodkin);
        assertFalse(group.containsStudent(Student.Vodkin));

    }

    @Test
    public void setGrade() throws Exception {
        group.setGrade(Student.Vodkin, new Grade(5));

    }

    @Test
    public void getGrades() throws Exception {
        assertNull(group.getGrades(Student.Oblomov));
        group.setGrade(Student.Oblomov, new Grade(5));
        group.setGrade(Student.Oblomov, new Grade(3));
        assertEquals(group.getGrades(Student.Oblomov).get(0).getValue(),5);
        System.out.println(group.getGrades(Student.Oblomov));
    }


}