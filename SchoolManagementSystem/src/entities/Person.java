package entities;

public class Person {
    private Integer id;
    private String firstName, lastName,dateOfBirth, gender;
    private Integer phoneNumber;
    private String email, address;
    private Integer nationalID,age;
    private boolean activeStatus;

    //constructors
    public Person(Integer id, String firstName, String lastName, String dateOfBirth, String gender, Integer phoneNumber, String email, String address, Integer nationalID, Integer age, boolean activeStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.nationalID = nationalID;
        this.age = age;
        this.activeStatus = activeStatus;
    }

    public Person(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
