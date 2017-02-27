package Unit2.Task5studentsGroup;

import Unit2.Task5studentsGroup.Enums.Student;
import Unit2.Task5studentsGroup.Enums.StudySubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceGroup {
    private List<StudentGroup> list = new ArrayList<>();

    public ServiceGroup addGroup (StudentGroup group){
        list.add(group);
        return this;
    }

    public void removeGroup (StudentGroup group){
        list.remove(group);
    }

    public List<StudentGroup> findListOfGroups(Student student) {

        return list.stream().filter(x -> x.containsStudent(student)).collect(Collectors.toList());

    }


}
