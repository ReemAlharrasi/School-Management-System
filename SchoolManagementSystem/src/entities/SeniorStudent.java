package entities;

import utils.HelperUtils;

// Second inheritance level: SeniorStudent IS A Student IS A Person.
// Building a SeniorStudent chains super(...) all the way up:
//   SeniorStudent -> Student -> Person
public class SeniorStudent extends Student {

    private String major;
    private double gpa;
    private String graduationDate;
    private int creditsEarned;

    public SeniorStudent(Integer id, String firstName, String lastName, String dateOfBirth,
                         String gender, Integer phoneNumber, String email, String address,
                         Integer nationalID, Integer age, boolean activeStatus,
                         String gradeLevel, String enrollmentDate, double feeBalance, boolean isScholarship,
                         String major, double gpa, String graduationDate, int creditsEarned) {
        // this super(...) calls the Student constructor, which itself calls Person
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address,
                nationalID, age, activeStatus, gradeLevel, enrollmentDate, feeBalance, isScholarship);
        setMajor(major);
        setGpa(gpa);
        setGraduationDate(graduationDate);
        setCreditsEarned(creditsEarned);
    }

    //getters
    public String getMajor() {return major;}
    public double getGpa() {return gpa;}
    public String getGraduationDate() {return graduationDate;}
    public int getCreditsEarned() {return creditsEarned;}

    //setters
    public void setMajor(String major) {
        if (HelperUtils.isEmpty(major)) {
            System.out.println("Rejected: major cannot be empty.");
            return;
        }
        this.major = major;
    }

    public void setGpa(double gpa) {
        if (!HelperUtils.inRange(gpa, 0.0, 4.0)) {
            System.out.println("Rejected: gpa must be between 0.0 and 4.0.");
            return;
        }
        this.gpa = gpa;
    }

    public void setGraduationDate(String graduationDate) {
        if (HelperUtils.isEmpty(graduationDate)) {
            System.out.println("Rejected: graduation date cannot be empty.");
            return;
        }
        this.graduationDate = graduationDate;
    }

    public void setCreditsEarned(int creditsEarned) {
        if (!HelperUtils.isPositive(creditsEarned)) {
            System.out.println("Rejected: credits cannot be negative.");
            return;
        }
        this.creditsEarned = creditsEarned;
    }

    //methods
    // move into a new major
    public void promote(String newMajor) {
        setMajor(newMajor);
        System.out.println(getFullName() + " promoted into major: " + newMajor);
    }

    // graduate: mark them and turn the active status off
    public void graduate() {
        setActiveStatus(false);
        System.out.println(getFullName() + " has graduated with GPA " + gpa);
    }

    // add the credits of one term to the running total
    public int totalCredits(int termCredits) {
        if (HelperUtils.isPositive(termCredits)) {
            creditsEarned = creditsEarned + termCredits;
        }
        return creditsEarned;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("[Senior student details]");
        System.out.println("Major       : " + major);
        System.out.println("GPA         : " + gpa);
        System.out.println("Graduates on: " + graduationDate);
        System.out.println("Credits     : " + creditsEarned);
    }

    @Override
    public void displaySummary() {
        System.out.println("[Senior] " + getId() + " - " + getFullName() + " (" + major + ", GPA " + gpa + ")");
    }
}
