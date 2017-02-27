package Unit2.Task5studentsGroup;

import Unit2.Task5studentsGroup.Enums.Student;
import Unit2.Task5studentsGroup.Enums.StudySubject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ServiceGroupTest {
    StudentGroup groupAstra = new StudentGroup(StudySubject.Astronomy, StudySubject.TypeOfGrades.Decimal).
            addStudent(Student.Alekseev, Student.Ivanov, Student.Oblomov, Student.Vodkin);
    StudentGroup groupHistory = new StudentGroup(StudySubject.History, StudySubject.TypeOfGrades.Integer).
            addStudent(Student.Oblomov, Student.Petrov, Student.Goncharov);

    ServiceGroup serviceGroup =  new ServiceGroup().addGroup(groupAstra).addGroup(groupHistory);

    @Test
    public void findListOfGroups() throws Exception {
        assertEquals((serviceGroup.findListOfGroups(Student.Ivanov)).get(0).getStudySubject(),StudySubject.Astronomy);
        groupAstra.setGrade(Student.Ivanov, new Grade(7));
        groupAstra.setGrade(Student.Ivanov, new Grade(6.6));
        System.out.println(serviceGroup.findListOfGroups(Student.Ivanov).get(0).getGrades(Student.Ivanov));

    }

    }