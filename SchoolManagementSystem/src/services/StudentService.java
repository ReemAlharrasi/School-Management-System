package services;

import entities.Student;
import entities.SeniorStudent;
import interfaces.Manageable;
import interfaces.Searchable;
import utils.HelperUtils;

// Stores students in a plain array (no generics) and lets us manage + search them.
public class StudentService implements Manageable, Searchable {

    private Student[] students;
    private int count;

    public StudentService() {
        students = new Student[100];
        count = 0;
    }

    // ---------- addStudent (3 overloads = method overloading) ----------

    // 1) basic details only: we fill sensible defaults for the rest
    public Student addStudent(String firstName, String lastName, int age) {
        int id = HelperUtils.generateNumericId();
        Student s = new Student(id, firstName, lastName, "2000-01-01", "Other",
                90000000, "unknown@school.test", "unknown", 999999, age, true,
                "1", "2024-09-01", 0.0, false);
        add(s);
        return s;
    }

    // 2) details + grade level
    public Student addStudent(String firstName, String lastName, int age, String gradeLevel) {
        Student s = addStudent(firstName, lastName, age);
        s.setGradeLevel(gradeLevel);
        return s;
    }

    // 3) an already-built Student object
    public Student addStudent(Student student) {
        add(student);
        return student;
    }

    // ---------- Manageable ----------

    @Override
    public void add(Object item) {
        if (!(item instanceof Student)) {
            System.out.println("Rejected: not a student.");
            return;
        }
        if (count >= students.length) {
            System.out.println("Rejected: student store is full.");
            return;
        }
        students[count] = (Student) item;
        count = count + 1;
    }

    @Override
    public boolean removeById(String id) {
        int found = -1;
        for (int i = 0; i < count; i++) {
            if (HelperUtils.sameId(students[i].getId(), id)) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            return false;
        }
        for (int i = found; i < count - 1; i++) {
            students[i] = students[i + 1];
        }
        students[count - 1] = null;
        count = count - 1;
        return true;
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[count];
        for (int i = 0; i < count; i++) {
            result[i] = students[i];
        }
        return result;
    }

    // ---------- Searchable ----------

    @Override
    public Object[] search(String keyword) {
        // first count the matches, then fill an array of exactly that size
        int matches = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(students[i], keyword)) {
                matches = matches + 1;
            }
        }
        Object[] result = new Object[matches];
        int pos = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(students[i], keyword)) {
                result[pos] = students[i];
                pos = pos + 1;
            }
        }
        return result;
    }

    private boolean matchesKeyword(Student s, String keyword) {
        if (HelperUtils.isEmpty(keyword)) {
            return false;
        }
        String k = keyword.toLowerCase();
        return s.getFullName().toLowerCase().contains(k)
                || String.valueOf(s.getId()).contains(k);
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (HelperUtils.sameId(students[i].getId(), id)) {
                return students[i];
            }
        }
        return null;
    }

    // ---------- service-specific ----------

    // update a student's contact using the id
    public void updateContact(String id, Integer phone, String email) {
        Object found = searchById(id);
        if (found == null) {
            System.out.println("No student with id " + id);
            return;
        }
        Student s = (Student) found;
        s.updateContact(phone, email); // the 2-argument overload
    }

    // list only the SeniorStudent objects
    public void listSeniors() {
        System.out.println("--- Senior students ---");
        boolean any = false;
        for (int i = 0; i < count; i++) {
            if (students[i] instanceof SeniorStudent) {
                students[i].displaySummary();
                any = true;
            }
        }
        if (!any) {
            System.out.println("(none)");
        }
    }

    // add up every student's fee balance
    public double totalOutstanding() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total = total + students[i].getFeeBalance();
        }
        return total;
    }

    public int getCount() {
        return count;
    }
}
