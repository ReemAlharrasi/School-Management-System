package entities;

public class Enrollment {
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
        this.reason = reason;
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

    //setters
    public void setEnrollmentId(String enrollmentId) {this.enrollmentId = enrollmentId;}
    public void setStudentId(String studentId) {this.studentId = studentId;}
    public void setCourseId(String courseId) {this.courseId = courseId;}
    public void setEnrollDate(String enrollDate) {this.enrollDate = enrollDate;}
    public void setStatus(String status) {this.status = status;}
    public void setReason(String reason) {this.reason = reason;}
    public void setRepeat(boolean repeat) {isRepeat = repeat;}

    //methods
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

    public void cancel(){
        setStatus("Cancelled");
    }
    public void complete(){
        setStatus("Completed");
    }
}
