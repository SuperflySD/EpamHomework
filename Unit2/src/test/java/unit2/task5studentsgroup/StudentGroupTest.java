package unit2.task5studentsgroup;

import unit2.task5studentsgroup.enums.Student;
import unit2.task5studentsgroup.enums.StudySubject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StudentGroupTest {
    StudentGroup groupAstronomy = new StudentGroup(StudySubject.Astronomy).
            addStudent(Student.Alekseev, Student.Ivanov, Student.Alekseev, Student.Oblomov, Student.Vodkin);
    StudentGroup groupHistory = new StudentGroup(StudySubject.History).
            addStudent(Student.Alekseev, Student.Ivanov, Student.Alekseev, Student.Oblomov, Student.Vodkin);

    @Test
    public void contains() throws Exception {
        System.out.println(groupAstronomy);
        assertTrue(groupAstronomy.containsStudent(Student.Alekseev));
        assertTrue(groupAstronomy.containsStudent(Student.Ivanov));
        assertTrue(groupAstronomy.containsStudent(Student.Oblomov));
        assertFalse(groupAstronomy.containsStudent(Student.Goncharov));
        assertTrue(groupAstronomy.getCountStudents()==4);

    }

    @Test
    public void remove() throws Exception {
        assertTrue(groupAstronomy.containsStudent(Student.Vodkin));
        groupAstronomy.removeStudent(Student.Vodkin);
        assertFalse(groupAstronomy.containsStudent(Student.Vodkin));

    }

    @Test
    public void setGrade() throws Exception {
        groupAstronomy.setGrade(Student.Vodkin, new Grade(5));

    }

    @Test
    public void getAllStudents() throws Exception {
        List<Student> list = groupAstronomy.getAllStudents();
        System.out.println(list);

    }
    @Test
    public void getGrades() throws Exception {
        assertNull(groupAstronomy.getGradesForStudent(Student.Oblomov));

        groupAstronomy.setGrade(Student.Oblomov, new Grade(5));
        groupAstronomy.setGrade(Student.Oblomov, new Grade(4));
        groupAstronomy.setGrade(Student.Oblomov, new Grade(2));
        assertEquals(groupAstronomy.getGradesForStudent(Student.Oblomov).get(0).getValue(),5);
        System.out.println(groupAstronomy.getGradesForStudent(Student.Oblomov));


        assertEquals((groupAstronomy.getAverageGradeForStudent(Student.Oblomov)),3.66d, 0.01d);

    }




}