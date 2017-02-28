package Unit2.Task5studentsGroup.Enums;

public enum StudySubject {
    Math (TypeOfGrades.Integer),
    Physics (TypeOfGrades.Decimal),
    Astronomy (TypeOfGrades.Decimal),
    History (TypeOfGrades.Integer);

   public enum TypeOfGrades{
        Integer,
        Decimal
    }

    public TypeOfGrades typeOfGrades;

   StudySubject(TypeOfGrades typo){
       this.typeOfGrades=typo;
   }


}
