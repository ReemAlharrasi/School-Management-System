package entities;

import utils.HelperUtils;

// A Student IS A Person, so it extends Person.
// Building a Student calls super(...) first to fill the Person part.
public class Student extends Person{
    private String gradeLevel,enrollmentDate;
    String[] enrolledSubjects, recordIDs;
    private double feeBalance;
    private boolean isScholarship;
    private int subjectCount,recordCount;

    //constructor
    public Student(Integer id, String firstName, String lastName, String dateOfBirth, String gender, Integer phoneNumber, String email, String address, Integer nationalID, Integer age, boolean activeStatus, String gradeLevel, String enrollmentDate, double feeBalance, boolean isScholarship) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, nationalID, age, activeStatus);
        enrolledSubjects = new String[10];
        recordIDs = new String[10];
        subjectCount=0;
        recordCount=0;
        setGradeLevel(gradeLevel);
        setEnrollmentDate(enrollmentDate);
        setFeeBalance(feeBalance);
        setScholarship(isScholarship);
    }

    //getters
    public String getGradeLevel() {return gradeLevel;}
    public String getEnrollmentDate() {return enrollmentDate;}
    public String[] getEnrolledSubjects() {return enrolledSubjects;}
    public String[] getRecordIDs() {return recordIDs;}
    public double getFeeBalance() {return feeBalance;}
    public boolean isScholarship() {return isScholarship;}
    public int getSubjectCount(){return subjectCount;}
    public int getRecordCount(){return recordCount;}

    //setters
    public void setGradeLevel(String gradeLevel) {
        if (HelperUtils.isEmpty(gradeLevel)) {
            System.out.println("Rejected: grade level cannot be empty.");
            return;
        }
        this.gradeLevel = gradeLevel;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        if (HelperUtils.isEmpty(enrollmentDate)) {
            System.out.println("Rejected: enrollment date cannot be empty.");
            return;
        }
        this.enrollmentDate = enrollmentDate;
    }

    public void setEnrolledSubjects(String[] enrolledSubjects) {this.enrolledSubjects = enrolledSubjects;}
    public void setRecordIDs(String[] recordIDs) {this.recordIDs = recordIDs;}

    public void setFeeBalance(double feeBalance) {
        if (!HelperUtils.isPositive(feeBalance)) {
            System.out.println("Rejected: fee balance cannot be negative.");
            return;
        }
        this.feeBalance = feeBalance;
    }

    public void setScholarship(boolean scholarship) {isScholarship = scholarship;}
    public void setRecordCount(int recordCount) {this.recordCount = recordCount;}

    @Override
    public void displayInfo() {
        super.displayInfo(); // print the Person part first
        System.out.println("[Student details]");
        System.out.println("Grade level : " + gradeLevel);
        System.out.println("Enrolled on : " + enrollmentDate);
        System.out.println("Fee balance : " + feeBalance);
        System.out.println("Scholarship : " + isScholarship);
        System.out.println("Subjects    : " + subjectCount + ", records: " + recordCount);
    }

    @Override
    public void displaySummary() {
        System.out.println("[Student] " + getId() + " - " + getFullName() + " (grade " + gradeLevel + ")");
    }

    public void addSubject(String subject){
        if (HelperUtils.isEmpty(subject)){
            System.out.println("Rejected: subject cannot be empty.");
            return;
        }
        if (subjectCount >= enrolledSubjects.length) {
            System.out.println("Rejected: subject list is full.");
            return;
        }
        enrolledSubjects[subjectCount] = subject;
        subjectCount = subjectCount + 1;
    }

    // was comparing subject to itself before, so it always returned true
    public boolean hasSubject(String subject){
        for (int i = 0; i < subjectCount; i++) {
            if (enrolledSubjects[i].equalsIgnoreCase(subject)) {
                return true;
            }
        }
        return false;
    }

    public void listSubjects(){
        System.out.println("Subjects for " + getFullName() + ":");
        if (subjectCount == 0) {
            System.out.println("  (none)");
            return;
        }
        for (int i = 0; i < subjectCount; i++) {
            System.out.println("  - " + enrolledSubjects[i]);
        }
    }

    public void addRecordId(String recordID){
        if (HelperUtils.isEmpty(recordID)){
            System.out.println("Rejected: record id cannot be empty.");
            return;
        }
        if (recordCount >= recordIDs.length) {
            System.out.println("Rejected: record list is full.");
            return;
        }
        recordIDs[recordCount] = recordID;
        recordCount = recordCount + 1;
    }

    public void addToBalance(double amount){
        if (!HelperUtils.isPositive(amount)){
            System.out.println("Rejected: amount to add cannot be negative.");
            return;
        }
        setFeeBalance(getFeeBalance()+amount);
    }

    public void clearBalance(){
        setFeeBalance(0);
        System.out.println("Fee balance cleared!");
    }

    // ---------- update contact (2 overloads = method overloading) ----------

    // phone only
    public void updateContact(Integer phone) {
        setPhoneNumber(phone);
    }

    // phone and email
    public void updateContact(Integer phone, String email) {
        setPhoneNumber(phone);
        setEmail(email);
    }
}
