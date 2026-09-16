package entities;

import java.util.Objects;

import interfaces.Displayable;
import utils.HelperUtils;

// The base class for everyone in the school.
// Student and Teacher extend this class.
public class Person implements Displayable {
    private Integer id;
    private String firstName, lastName, dateOfBirth, gender;
    private Integer phoneNumber;
    private String email, address;
    private Integer nationalID, age;
    private boolean activeStatus;

    private static final String[] ALLOWED_GENDER = { "Male", "Female", "Other" };

    //constructors
    public Person(Integer id, String firstName, String lastName, String dateOfBirth, String gender, Integer phoneNumber, String email, String address, Integer nationalID, Integer age, boolean activeStatus) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setAddress(address);
        setNationalID(nationalID);
        setAge(age);
        setActiveStatus(activeStatus);
    }

    //overloaded constructor: only id + first + last name
    public Person(Integer id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        this.age = 0;            // so isAdult() never breaks
        this.activeStatus = true; // a new person is active by default
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

    //setters (validation lives here - encapsulation)
    public void setId(Integer id) {
        if (id == null || id <= 0) {
            System.out.println("Rejected: id must be a positive number.");
            return;
        }
        this.id = id;
    }

    public void setFirstName(String firstName) {
        if (HelperUtils.isEmpty(firstName)) {
            System.out.println("Rejected: first name cannot be empty.");
            return;
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (HelperUtils.isEmpty(lastName)) {
            System.out.println("Rejected: last name cannot be empty.");
            return;
        }
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (HelperUtils.isEmpty(dateOfBirth)) {
            System.out.println("Rejected: date of birth cannot be empty.");
            return;
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        if (!HelperUtils.isOneOf(gender, ALLOWED_GENDER)) {
            System.out.println("Rejected: gender must be Male, Female or Other.");
            return;
        }
        this.gender = gender;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        if (!HelperUtils.isValidPhone(phoneNumber)) {
            System.out.println("Rejected: phone number is not valid.");
            return;
        }
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        if (HelperUtils.isEmpty(email)) {
            System.out.println("Rejected: email cannot be empty.");
            return;
        }
        this.email = email;
    }

    public void setAddress(String address) {
        if (HelperUtils.isEmpty(address)) {
            System.out.println("Rejected: address cannot be empty.");
            return;
        }
        this.address = address;
    }

    public void setNationalID(Integer nationalID) {
        if (nationalID == null || nationalID <= 0) {
            System.out.println("Rejected: national id must be a positive number.");
            return;
        }
        this.nationalID = nationalID;
    }

    public void setAge(Integer age) {
        if (!HelperUtils.isValidAge(age)) {
            System.out.println("Rejected: age must be between 0 and 120.");
            return;
        }
        this.age = age;
    }

    public void setActiveStatus(boolean activeStatus) {this.activeStatus = activeStatus;}

    //methods
    @Override
    public void displayInfo(){
        System.out.println("----- Person -----");
        System.out.println("ID: "+getId());
        System.out.println("Name: "+getFullName());
        System.out.println("Date of birth: "+getDateOfBirth());
        System.out.println("Gender: "+getGender());
        System.out.println("Phone number: "+getPhoneNumber());
        System.out.println("Email: "+getEmail());
        System.out.println("Address: "+getAddress());
        System.out.println("National ID: "+getNationalID());
        System.out.println("Age: "+getAge());
        System.out.println("Active: "+isActiveStatus());
    }

    @Override
    public void displaySummary(){
        System.out.println(getId()+" - "+getFullName());
    }

    public String getFullName(){
        return getFirstName()+" "+getLastName();
    }

    public boolean isAdult(){
        return age != null && age >= 18;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", nationalID=" + nationalID +
                ", age=" + age +
                ", activeStatus=" + activeStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
