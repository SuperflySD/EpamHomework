package unit2.task5studentsgroup;

import unit2.task5studentsgroup.enums.Student;
import unit2.task5studentsgroup.enums.StudySubject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StudentGroup {
    private StudySubject subject;
    private HashMap<Student, List<Grade>> studentMap = new HashMap<>();


    public StudentGroup(StudySubject subject) {
        this.subject=subject;
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

    public List<Student> getAllStudents(){
        return new ArrayList<>(studentMap.keySet());
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
        if (subject.typeOfGrades == Integer.class &&
           (grade.getClass().equals(Double.class) || grade.getClass().equals(Float.class)))
            throw new IllegalArgumentException("Value of the grade can't be decimal for this type of study subject");

        studentMap.get(student).add(grade);
    }

    public List<Grade> getGradesForStudent(Student student){
         if (studentMap.get(student).size()==0)
             return null;

        return new ArrayList<>(studentMap.get(student));
    }

    public Double getAverageGradeForStudent(Student student) {
        if (studentMap.get(student).size() == 0)
            return 0.0d;
        List<Grade> list = studentMap.get(student);
        return new BigDecimal(list.stream().mapToDouble(x -> x.getValue().doubleValue()).sum() / list.size())
                .setScale(2, RoundingMode.UP).doubleValue();
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

    public Class getTypeOfGrades() {
        return subject.typeOfGrades;
    }

    @Override
    public String toString() {
        return String.format("Subject - %s, group %s",subject, studentMap.keySet());
    }
}
