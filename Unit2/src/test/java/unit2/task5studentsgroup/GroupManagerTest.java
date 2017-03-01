package unit2.task5studentsgroup;

import unit2.task5studentsgroup.enums.Student;
import unit2.task5studentsgroup.enums.StudySubject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GroupManagerTest {
    StudentGroup groupAstra = new StudentGroup(StudySubject.Astronomy).
            addStudent(Student.Alekseev, Student.Ivanov, Student.Oblomov, Student.Vodkin);
    StudentGroup groupHistory = new StudentGroup(StudySubject.History).
            addStudent(Student.Alekseev, Student.Oblomov, Student.Petrov, Student.Goncharov);

    GroupManager groupManager =  new GroupManager().addGroup(groupAstra).addGroup(groupHistory);

    @Test
    public void findListOfGroups() throws Exception {
        assertEquals((groupManager.findListOfGroupsForStudent(Student.Ivanov)).get(0).getStudySubject(),StudySubject.Astronomy);
        groupAstra.setGrade(Student.Ivanov, new Grade(7));
        groupAstra.setGrade(Student.Ivanov, new Grade(6.6));

        System.out.println(groupManager.findListOfGroupsForStudent(Student.Ivanov).get(0).getGradesForStudent(Student.Ivanov));

    }

    @Test
    public void sortStudentsByAverageGrade() throws Exception {
        groupAstra.setGrade(Student.Alekseev, new Grade(4));
        groupHistory.setGrade(Student.Alekseev, new Grade(5));
        groupAstra.setGrade(Student.Vodkin, new Grade(3));
        groupAstra.setGrade(Student.Vodkin, new Grade(8));
        groupAstra.setGrade(Student.Vodkin, new Grade(2));
        groupHistory.setGrade(Student.Oblomov, new Grade(7));

       System.out.println(groupManager.sortStudentsByAverageGrade());
       assertEquals(groupManager.sortStudentsByAverageGrade().get(0).getKey(),Student.Oblomov);
       assertEquals(groupManager.sortStudentsByAverageGrade().get(2).getValue(),4.33, 0.01d);

    }



}