package Unit2.Task5studentsGroup;

import Unit2.Task5studentsGroup.Enums.Student;
import Unit2.Task5studentsGroup.Enums.StudySubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StudentGroup {
    private StudySubject.TypeOfGrades typo;
    private StudySubject subject;
    private HashMap<Student, List<Grade>> studentMap = new HashMap<>();


    public StudentGroup(StudySubject subject, StudySubject.TypeOfGrades typo) {
        this.subject=subject;
        this.typo = typo;
    }

    public StudentGroup addStudent  (Student ... students){
       for (Student student : students) {
           if (studentMap.containsKey(student))
               continue;
           else
               this.studentMap.put(student, new ArrayList<Grade>());
       }
       return this;
    }


    public boolean containsStudent (Student student){
        return studentMap.containsKey(student);
    }

    public void removeStudent (Student student){
        studentMap.remove(student);
    }

    /**
     * @param student
     * @param grade value must correspond to the type, chosen for this type of student group
     * @exception IllegalArgumentException if grade value doesn't correspond to the type, chosen for this type of student group
     */
    public  void setGrade  (Student student, Grade grade){
        if (!studentMap.containsKey(student))
            throw new IllegalArgumentException("There is no such a student in the group");
        if (typo == StudySubject.TypeOfGrades.Integer &&
           (grade.getValue() instanceof Double || grade.getValue() instanceof Float))
            throw new IllegalArgumentException("Value of the grade can't be decimal for this type of study subject");

        studentMap.get(student).add(grade);
    }

    public List<Grade> getGrades(Student student){
         if (studentMap.get(student).size()==0)
             return null;

        return studentMap.get(student);
    }

    public void clearGroup (){
        studentMap.clear();
    }

    public int getCountStudents(){
        return studentMap.size();
    }

    public StudySubject getStudySubject(){
        return subject;
    }

    public StudySubject.TypeOfGrades getTypeOfGrades() {
        return typo;
    }

    @Override
    public String toString() {
        return String.format("Subject - %s, group %s",subject, studentMap.keySet());
    }
}
