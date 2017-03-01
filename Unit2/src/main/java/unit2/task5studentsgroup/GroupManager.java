package unit2.task5studentsgroup;

import unit2.task5studentsgroup.enums.Student;

import java.util.*;
import java.util.stream.Collectors;

public class GroupManager {
    private List<StudentGroup> list = new ArrayList<>();

    public GroupManager addGroup (StudentGroup group){
        list.add(group);
        return this;
    }

    public void removeGroup (StudentGroup group){

        list.remove(group);
    }

    public List<StudentGroup> findListOfGroupsForStudent(Student student) {
        return list.stream().filter(x -> x.containsStudent(student)).collect(Collectors.toList());
    }

    public List<Map.Entry<Student, Double>> sortStudentsByAverageGrade() {
        HashMap<Student, Double> map = new HashMap<>();

        for (Student student : Student.values()) {
            int counter = 0;
            for (StudentGroup sGroup : list) {
                if (sGroup.containsStudent(student)) {
                    Double avr = sGroup.getAverageGradeForStudent(student);
                    if (avr!=0.0d)
                        counter++;
                    if (map.containsKey(student))
                        map.put(student, map.get(student) + avr);
                    else
                        map.put(student, avr);
                }
                else
                    continue;
            }
            map.put(student, map.get(student)/(counter==0 ? 1:counter));
        }
        return map.entrySet().stream().sorted((x,y) -> y.getValue().intValue()-x.getValue().intValue()).collect(Collectors.toList());
    }
}
