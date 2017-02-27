package Unit2.Task5studentsGroup.Enums;

public enum Student {


    Ivanov ("Pavel"),
    Petrov ("Andrey"),
    Vodkin ("Vasiliy"),
    Alekseev ("Stepan"),
    Goncharov ("Igor"),
    Oblomov ("Egor");

   private String firstName;

    Student(String firstName){
        this.firstName=firstName;
    }

    @Override
    public String toString() {
        return super.toString() +" " + firstName;
    }
}
