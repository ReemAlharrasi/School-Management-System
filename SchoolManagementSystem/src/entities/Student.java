package entities;

public class Student extends Person{
    private String gradeLevel,enrollmentDate;
    String[] enrolledSubjects, recordIDs;
    private double feeBalance;
    private boolean isScholarship;
    private int subjectCount,recordCount;

    //constructor
    public Student(Integer id, String firstName, String lastName, String dateOfBirth, String gender, Integer phoneNumber, String email, String address, Integer nationalID, Integer age, boolean activeStatus, String gradeLevel, String enrollmentDate, double feeBalance, boolean isScholarship) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, nationalID, age, activeStatus);
        setGradeLevel(gradeLevel);
        setEnrollmentDate(enrollmentDate);
        enrolledSubjects = new String[10];
        recordIDs = new String[10];
        setFeeBalance(feeBalance);
        setScholarship(isScholarship);
        subjectCount=0;
        recordCount=0;
    }

    //getters
    public String getGradeLevel() {return gradeLevel;}
    public String getEnrollmentDate() {return enrollmentDate;}
    public String[] getEnrolledSubjects() {return enrolledSubjects;}
    public String[] getRecordIDs() {return recordIDs;}
    public double getFeeBalance() {return feeBalance;}
    public boolean isScholarship() {return isScholarship;}
    public int getRecordCount(){return recordCount;}

    //setters
    public void setGradeLevel(String gradeLevel) {this.gradeLevel = gradeLevel;}
    public void setEnrollmentDate(String enrollmentDate) {this.enrollmentDate = enrollmentDate;}
    public void setEnrolledSubjects(String[] enrolledSubjects) {this.enrolledSubjects = enrolledSubjects;}
    public void setRecordIDs(String[] recordIDs) {this.recordIDs = recordIDs;}
    public void setFeeBalance(double feeBalance) {this.feeBalance = feeBalance;}
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

    public void addSubject(String subject){
        if (subject.strip().isEmpty()){
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


    public boolean hasSubject(String subject){
        for (String sub:enrolledSubjects)
            if (subject.equalsIgnoreCase(subject))
                return true;
        return false;
    }

    public void addRecordId(String recordID){
        if (recordID.strip().isEmpty()){
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
        if (amount<0){
            System.out.println("Rejected: amount to add cannot be negative.");
            return;
        }
        setFeeBalance(getFeeBalance()+amount);
    }

    public void clearBalance(){
        setFeeBalance(0);
        System.out.println("Fee balance cleared!");
    }
}
