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

    //getters
    public Integer getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getDateOfBirth() {return dateOfBirth;}
    public String getGender() {return gender;}
    public Integer getPhoneNumber() {return phoneNumber;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}
    public Integer getNationalID() {return nationalID;}
    public Integer getAge() {return age;}
    public boolean isActiveStatus() {return activeStatus;}

    //setters
    public void setId(Integer id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}
    public void setGender(String gender) {this.gender = gender;}
    public void setPhoneNumber(Integer phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setEmail(String email) {this.email = email;}
    public void setAddress(String address) {this.address = address;}
    public void setNationalID(Integer nationalID) {this.nationalID = nationalID;}
    public void setAge(Integer age) {this.age = age;}
    public void setActiveStatus(boolean activeStatus) {this.activeStatus = activeStatus;}
    

}
