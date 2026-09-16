package interfaces;

// Every entity (Person, Student, Teacher, CourseRecord, Enrollment) can show
// itself two ways: a full display, and a short one-line summary.
public interface Displayable {

    void displayInfo();     // print all the details

    void displaySummary();  // print a short line (id + name)
}
