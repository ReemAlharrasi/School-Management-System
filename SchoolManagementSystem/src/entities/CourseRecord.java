package entities;

import interfaces.Displayable;
import utils.HelperUtils;

// A plain data class holding one student's result for a course.
public class CourseRecord implements Displayable {
    private Integer recordId;
    private Integer studentId;
    private Integer teacherId;
    private String term;
    private char grade;
    private String remarks;
    private String notes;
    private boolean isFinalized;

    private static final String ALLOWED_GRADES = "ABCDEF";

    //constructor
    public CourseRecord(Integer recordId, Integer studentId, Integer teacherId, String term, char grade, String remarks, String notes, boolean isFinalized) {
        setRecordId(recordId);
        setStudentId(studentId);
        setTeacherId(teacherId);
        setTerm(term);
        setGrade(grade);
        this.remarks = remarks;
        this.notes = (notes == null) ? "" : notes;
        this.isFinalized = isFinalized;
    }

    //getters
    public Integer getRecordId() {return recordId;}
    public Integer getStudentId() {return studentId;}
    public Integer getTeacherId() {return teacherId;}
    public String getTerm() {return term;}
    public char getGrade() {return grade;}
    public String getRemarks() {return remarks;}
    public String getNotes() {return notes;}
    public boolean isFinalized() {return isFinalized;}

    //setters (validation lives here)
    public void setRecordId(Integer recordId) {
        if (recordId == null || recordId <= 0) {
            System.out.println("Rejected: record id must be a positive number.");
            return;
        }
        this.recordId = recordId;
    }

    public void setStudentId(Integer studentId) {
        if (studentId == null || studentId <= 0) {
            System.out.println("Rejected: student id must be a positive number.");
            return;
        }
        this.studentId = studentId;
    }

    public void setTeacherId(Integer teacherId) {
        if (teacherId == null || teacherId <= 0) {
            System.out.println("Rejected: teacher id must be a positive number.");
            return;
        }
        this.teacherId = teacherId;
    }

    public void setTerm(String term) {
        if (HelperUtils.isEmpty(term)) {
            System.out.println("Rejected: term cannot be empty.");
            return;
        }
        this.term = term;
    }

    public void setGrade(char grade) {
        if (ALLOWED_GRADES.indexOf(Character.toUpperCase(grade)) < 0) {
            System.out.println("Rejected: grade must be one of A B C D E F.");
            return;
        }
        this.grade = Character.toUpperCase(grade);
    }

    public void setRemarks(String remarks) {this.remarks = remarks;}
    public void setNotes(String notes) {this.notes = (notes == null) ? "" : notes;}
    public void setFinalized(boolean finalized) {isFinalized = finalized;}

    //methods
    @Override
    public void displayInfo(){
        System.out.println("----- Course Record -----");
        System.out.println("Record id : " + recordId);
        System.out.println("Student   : " + studentId);
        System.out.println("Teacher   : " + teacherId);
        System.out.println("Term      : " + term);
        System.out.println("Grade     : " + grade);
        System.out.println("Remarks   : " + remarks);
        System.out.println("Notes     : " + notes);
        System.out.println("Finalized : " + isFinalized);
    }

    @Override
    public void displaySummary(){
        System.out.println(recordId + " - student " + studentId + " - grade " + grade);
    }

    public void appendNote(String note){
        if (HelperUtils.isEmpty(note)) {
            return;
        }
        if (HelperUtils.isEmpty(getNotes())) {
            setNotes(note);
        } else {
            setNotes(getNotes()+" | "+note);
        }
    }

    // overload: the same note with the name of who wrote it
    public void appendNote(String note, String author){
        appendNote("(" + author + ") " + note);
    }

    public void finalizeRecord(){
        setFinalized(true);
    }
}
