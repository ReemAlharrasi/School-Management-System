package services;

import entities.CourseRecord;
import entities.Enrollment;
import entities.Student;
import interfaces.Manageable;
import interfaces.Searchable;
import utils.HelperUtils;

// Stores enrollments in a plain array (no generics).
public class EnrollmentService implements Manageable, Searchable {

    private Enrollment[] enrollments;
    private int count;

    public EnrollmentService() {
        enrollments = new Enrollment[100];
        count = 0;
    }

    // ---------- enroll (3 overloads = method overloading) ----------

    // 1) ids + date
    public Enrollment enroll(String studentId, String courseId, String date) {
        String id = HelperUtils.generateId("ENR");
        Enrollment e = new Enrollment(id, studentId, courseId, date, "Active", "", false);
        add(e);
        return e;
    }

    // 2) ids + date + term (the term is kept inside the reason text)
    public Enrollment enroll(String studentId, String courseId, String date, String term) {
        Enrollment e = enroll(studentId, courseId, date);
        e.addNotes("Term: " + term);
        return e;
    }

    // 3) full objects + a reason
    public Enrollment enroll(Student student, CourseRecord course, String reason) {
        Enrollment e = enroll(String.valueOf(student.getId()),
                String.valueOf(course.getRecordId()),
                course.getTerm());
        e.addNotes(reason);
        return e;
    }

    // ---------- Manageable ----------

    @Override
    public void add(Object item) {
        if (!(item instanceof Enrollment)) {
            System.out.println("Rejected: not an enrollment.");
            return;
        }
        if (count >= enrollments.length) {
            System.out.println("Rejected: enrollment store is full.");
            return;
        }
        enrollments[count] = (Enrollment) item;
        count = count + 1;
    }

    @Override
    public boolean removeById(String id) {
        int found = -1;
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getEnrollmentId().equals(id)) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            return false;
        }
        for (int i = found; i < count - 1; i++) {
            enrollments[i] = enrollments[i + 1];
        }
        enrollments[count - 1] = null;
        count = count - 1;
        return true;
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[count];
        for (int i = 0; i < count; i++) {
            result[i] = enrollments[i];
        }
        return result;
    }

    // ---------- Searchable ----------

    @Override
    public Object[] search(String keyword) {
        int matches = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(enrollments[i], keyword)) {
                matches = matches + 1;
            }
        }
        Object[] result = new Object[matches];
        int pos = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(enrollments[i], keyword)) {
                result[pos] = enrollments[i];
                pos = pos + 1;
            }
        }
        return result;
    }

    private boolean matchesKeyword(Enrollment e, String keyword) {
        if (HelperUtils.isEmpty(keyword)) {
            return false;
        }
        String k = keyword.toLowerCase();
        return e.getEnrollmentId().toLowerCase().contains(k)
                || e.getStudentId().toLowerCase().contains(k)
                || e.getCourseId().toLowerCase().contains(k);
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getEnrollmentId().equals(id)) {
                return enrollments[i];
            }
        }
        return null;
    }

    // ---------- service-specific ----------

    public void cancel(String enrollmentId) {
        Enrollment e = find(enrollmentId);
        if (e != null) {
            e.cancel();
            System.out.println("Cancelled.");
        }
    }

    public void complete(String enrollmentId) {
        Enrollment e = find(enrollmentId);
        if (e != null) {
            e.complete();
            System.out.println("Completed.");
        }
    }

    public void transfer(String enrollmentId, String newCourseId, String newDate) {
        Enrollment e = find(enrollmentId);
        if (e != null) {
            e.transfer(newCourseId, newDate);
            System.out.println("Transferred.");
        }
    }

    // print every enrollment with a given status
    public void listByStatus(String status) {
        System.out.println("--- Enrollments with status " + status + " ---");
        boolean any = false;
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStatus().equalsIgnoreCase(status)) {
                enrollments[i].displaySummary();
                any = true;
            }
        }
        if (!any) {
            System.out.println("(none)");
        }
    }

    // print every enrollment belonging to a given student
    public void listByStudent(String studentId) {
        System.out.println("--- Enrollments for student " + studentId + " ---");
        boolean any = false;
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudentId().equals(studentId)) {
                enrollments[i].displaySummary();
                any = true;
            }
        }
        if (!any) {
            System.out.println("(none)");
        }
    }

    // small private helper to find an enrollment by id
    private Enrollment find(String enrollmentId) {
        Object found = searchById(enrollmentId);
        if (found == null) {
            System.out.println("No enrollment with id " + enrollmentId);
            return null;
        }
        return (Enrollment) found;
    }

    public int getCount() {
        return count;
    }
}
