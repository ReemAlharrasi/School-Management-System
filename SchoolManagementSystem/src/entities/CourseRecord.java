package entities;

public class CourseRecord {
    private Integer recordId;
    private Integer studentId;
    private Integer teacherId;
    private String term;
    private char grade;
    private String remarks;
    private String notes;
    private boolean isFinalized;

    //constructor

    public CourseRecord(Integer recordId, Integer studentId, Integer teacherId, String term, char grade, String remarks, String notes, boolean isFinalized) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.term = term;
        this.grade = grade;
        this.remarks = remarks;
        this.notes = notes;
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

    //setters
    public void setRecordId(Integer recordId) {this.recordId = recordId;}
    public void setStudentId(Integer studentId) {this.studentId = studentId;}
    public void setTeacherId(Integer teacherId) {this.teacherId = teacherId;}
    public void setTerm(String term) {this.term = term;}
    public void setGrade(char grade) {this.grade = grade;}
    public void setRemarks(String remarks) {this.remarks = remarks;}
    public void setNotes(String notes) {this.notes = notes;}
    public void setFinalized(boolean finalized) {isFinalized = finalized;}

    //methods
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

    public void appendNote(String note){
        if (note.isBlank()) {
            return;
        }
        if (getNotes().isEmpty()) {
            setNotes(note);
        } else {
            setNotes(getNotes()+" | "+note);
        }
    }

    public void finalizeRecord(){
        setFinalized(true);
    }

}
