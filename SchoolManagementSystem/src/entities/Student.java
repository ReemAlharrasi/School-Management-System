package entities;

public class Student extends Person{
    private String gradeLevel,enrollmentDate;
    String[] enrolledSubjects, recordIDs;
    private double feeBalance;
    private boolean isScholarship;

    //constructor
    public Student(Integer id, String firstName, String lastName, String dateOfBirth, String gender, Integer phoneNumber, String email, String address, Integer nationalID, Integer age, boolean activeStatus, String gradeLevel, String enrollmentDate, String[] enrolledSubjects, String[] recordIDs, double feeBalance, boolean isScholarship) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, nationalID, age, activeStatus);
        this.gradeLevel = gradeLevel;
        this.enrollmentDate = enrollmentDate;
        this.enrolledSubjects = enrolledSubjects;
        this.recordIDs = recordIDs;
        this.feeBalance = feeBalance;
        this.isScholarship = isScholarship;
    }

    //getters
    public String getGradeLevel() {return gradeLevel;}
    public String getEnrollmentDate() {return enrollmentDate;}
    public String[] getEnrolledSubjects() {return enrolledSubjects;}
    public String[] getRecordIDs() {return recordIDs;}
    public double getFeeBalance() {return feeBalance;}
    public boolean isScholarship() {return isScholarship;}

    //setters
    public void setGradeLevel(String gradeLevel) {this.gradeLevel = gradeLevel;}
    public void setEnrollmentDate(String enrollmentDate) {this.enrollmentDate = enrollmentDate;}
    public void setEnrolledSubjects(String[] enrolledSubjects) {this.enrolledSubjects = enrolledSubjects;}
    public void setRecordIDs(String[] recordIDs) {this.recordIDs = recordIDs;}
    public void setFeeBalance(double feeBalance) {this.feeBalance = feeBalance;}
    public void setScholarship(boolean scholarship) {isScholarship = scholarship;}

    @Override
    public void displayInfo() {
        System.out.println("Display Student info -----");
        System.out.println("ID: "+getId());
        System.out.println("Name: "+getFullName());
        System.out.println("Date of birth: "+getDateOfBirth());
        System.out.println("Gender: "+getGender());
        System.out.println("Phone number: "+getPhoneNumber());
        System.out.println("Email: "+getEmail());
        System.out.println("Address: "+getAddress());
        System.out.println("Natonal ID: "+getNationalID());
        System.out.println("Age: "+getAge());
        System.out.println("Active: "+isActiveStatus());
        System.out.println("\n-----\nGrade Level : "+getGradeLevel());
        System.out.println("Enrollment date: "+getEnrollmentDate());
        System.out.println("past courses records: "+getRecordIDs());
        System.out.println("Fee balance: "+getFeeBalance());
        System.out.println("Scholarship: "+isScholarship());
    }
}
