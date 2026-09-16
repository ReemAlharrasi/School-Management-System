package entities;

import interfaces.Displayable;
import utils.HelperUtils;

// A plain data class linking a student to a course.
public class Enrollment implements Displayable {
    private String enrollmentId;
    private String studentId;
    private String courseId;
    private String enrollDate;
    private String status;
    private String reason;
    private boolean isRepeat;

    private static final String[] ALLOWED_STATUS = { "Active", "Completed", "Cancelled" };

    public Enrollment(String enrollmentId, String studentId, String courseId, String enrollDate,
                      String status, String reason, boolean isRepeat) {
        setEnrollmentId(enrollmentId);
        setStudentId(studentId);
        setCourseId(courseId);
        setEnrollDate(enrollDate);
        setStatus(status);
        this.reason = (reason == null) ? "" : reason;
        this.isRepeat = isRepeat;
    }

    //getters
    public String getEnrollmentId() {return enrollmentId;}
    public String getStudentId() {return studentId;}
    public String getCourseId() {return courseId;}
    public String getEnrollDate() {return enrollDate;}
    public String getStatus() {return status;}
    public String getReason() {return reason;}
    public boolean isRepeat() {return isRepeat;}

    //setters (validation lives here)
    public void setEnrollmentId(String enrollmentId) {
        if (HelperUtils.isEmpty(enrollmentId)) {
            System.out.println("Rejected: enrollment id cannot be empty.");
            return;
        }
        this.enrollmentId = enrollmentId;
    }

    public void setStudentId(String studentId) {
        if (HelperUtils.isEmpty(studentId)) {
            System.out.println("Rejected: student id cannot be empty.");
            return;
        }
        this.studentId = studentId;
    }

    public void setCourseId(String courseId) {
        if (HelperUtils.isEmpty(courseId)) {
            System.out.println("Rejected: course id cannot be empty.");
            return;
        }
        this.courseId = courseId;
    }

    public void setEnrollDate(String enrollDate) {
        if (HelperUtils.isEmpty(enrollDate)) {
            System.out.println("Rejected: enroll date cannot be empty.");
            return;
        }
        this.enrollDate = enrollDate;
    }

    public void setStatus(String status) {
        if (!HelperUtils.isOneOf(status, ALLOWED_STATUS)) {
            System.out.println("Rejected: status must be Active, Completed or Cancelled.");
            return;
        }
        this.status = status;
    }

    public void setReason(String reason) {this.reason = (reason == null) ? "" : reason;}
    public void setRepeat(boolean repeat) {isRepeat = repeat;}

    //methods
    @Override
    public void displayInfo(){
        System.out.println("----- Enrollment -----");
        System.out.println("Enrollment id: " + enrollmentId);
        System.out.println("Student      : " + studentId);
        System.out.println("Course       : " + courseId);
        System.out.println("Date         : " + enrollDate);
        System.out.println("Status       : " + status);
        System.out.println("Reason       : " + reason);
        System.out.println("Repeat       : " + isRepeat);
    }

    @Override
    public void displaySummary(){
        System.out.println(enrollmentId + " - student " + studentId + " - " + status);
    }

    public void cancel(){
        setStatus("Cancelled");
    }

    public void complete(){
        setStatus("Completed");
    }

    public void transfer(String newCourseId, String newDate){
        setCourseId(newCourseId);
        setEnrollDate(newDate);
        setStatus("Active");
    }

    // ---------- add notes to the reason (2 overloads = method overloading) ----------

    public void addNotes(String notes){
        if (HelperUtils.isEmpty(notes)) {
            return;
        }
        if (HelperUtils.isEmpty(reason)) {
            reason = notes;
        } else {
            reason = reason + " | " + notes;
        }
    }

    public void addNotes(String notes, String author){
        addNotes("(" + author + ") " + notes);
    }

    public boolean isPast(String otherDate){
        if (HelperUtils.isEmpty(otherDate) || HelperUtils.isEmpty(enrollDate)) return false;
        return enrollDate.compareTo(otherDate) < 0;
    }
}
