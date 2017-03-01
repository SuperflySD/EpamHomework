package unit2.task5studentsgroup.enums;

public enum StudySubject {
    Math (Integer.class),
    Physics (Double.class),
    Astronomy (Integer.class),
    History (Double.class);


    public Class typeOfGrades;

   StudySubject(Class clazz){

       this.typeOfGrades=clazz;
   }

}
