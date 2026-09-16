package main;

import entities.CourseRecord;
import entities.Enrollment;
import entities.HeadTeacher;
import entities.Person;
import entities.SeniorStudent;
import entities.Student;
import entities.Teacher;
import interfaces.Displayable;
import services.CourseService;
import services.EnrollmentService;
import services.StudentService;
import services.TeacherService;
import utils.HelperUtils;
import utils.InputHandler;

// The entry point and console menu of the School Management System.
// main() only starts the app; all the real work is in small methods.
public class SchoolApp {

    // the four services that store our data
    private StudentService studentService = new StudentService();
    private TeacherService teacherService = new TeacherService();
    private CourseService courseService = new CourseService();
    private EnrollmentService enrollmentService = new EnrollmentService();

    // reads keyboard input for us
    private InputHandler input = new InputHandler();

    public static void main(String[] args) {
        SchoolApp app = new SchoolApp();
        app.seedSampleData();  // put some example data in
        app.run();             // then show the menu
    }

    // ---------- the menu loop ----------

    private void run() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== School Management System =====");
            System.out.println("1. Students");
            System.out.println("2. Teachers");
            System.out.println("3. Courses");
            System.out.println("4. Enrollments");
            System.out.println("5. Reports");
            System.out.println("6. Exit");
            int choice = input.readInt("Choose an option", 1, 6);

            if (choice == 1) {
                studentMenu();
            } else if (choice == 2) {
                teacherMenu();
            } else if (choice == 3) {
                courseMenu();
            } else if (choice == 4) {
                enrollmentMenu();
            } else if (choice == 5) {
                reportsMenu();
            } else if (choice == 6) {
                running = false;
                System.out.println("Goodbye!");
            }
        }
    }

    // small shared helper: print a list of Displayable entities using displayInfo
    private void printEntities(Object[] items) {
        if (items.length == 0) {
            System.out.println("(nothing to show)");
            return;
        }
        for (int i = 0; i < items.length; i++) {
            Displayable d = (Displayable) items[i]; // every entity implements Displayable
            d.displayInfo();
            System.out.println();
        }
    }

    // ---------- students ----------

    private void studentMenu() {
        System.out.println("--- Students ---");
        System.out.println("1. Add   2. View all   3. Search   4. Update contact   5. Remove   6. List seniors");
        int choice = input.readInt("Choose", 1, 6);

        if (choice == 1) {
            String first = input.readText("First name");
            String last = input.readText("Last name");
            int age = input.readInt("Age", 0, 120);
            String grade = input.readText("Grade level");
            // uses the (details + grade level) overload of addStudent
            Student s = studentService.addStudent(first, last, age, grade);
            System.out.println("Added student with id " + s.getId());
        } else if (choice == 2) {
            printEntities(studentService.getAll());
        } else if (choice == 3) {
            String keyword = input.readText("Search keyword");
            printEntities(studentService.search(keyword));
        } else if (choice == 4) {
            String id = input.readText("Student id");
            int phone = input.readInt("New phone");
            String email = input.readText("New email");
            studentService.updateContact(id, phone, email);
        } else if (choice == 5) {
            String id = input.readText("Student id to remove");
            boolean removed = studentService.removeById(id);
            System.out.println(removed ? "Removed." : "Not found.");
        } else if (choice == 6) {
            studentService.listSeniors();
        }
    }

    // ---------- teachers ----------

    private void teacherMenu() {
        System.out.println("--- Teachers ---");
        System.out.println("1. Add   2. View all   3. Search   4. Assign class   5. Remove   6. By subject   7. Available");
        int choice = input.readInt("Choose", 1, 7);

        if (choice == 1) {
            String first = input.readText("First name");
            String last = input.readText("Last name");
            int age = input.readInt("Age", 18, 120);
            String subject = input.readText("Subject");
            int years = input.readInt("Experience years", 0, 60);
            double salary = input.readDouble("Salary");
            Teacher t = new Teacher(HelperUtils.generateNumericId(), first, last, "1990-01-01",
                    "Other", 90000000, "teacher@school.test", "unknown", 999999, age, true,
                    subject, years, salary, false);
            teacherService.add(t);
            System.out.println("Added teacher with id " + t.getId());
        } else if (choice == 2) {
            printEntities(teacherService.getAll());
        } else if (choice == 3) {
            String keyword = input.readText("Search keyword");
            printEntities(teacherService.search(keyword));
        } else if (choice == 4) {
            String id = input.readText("Teacher id");
            String classId = input.readText("Class id");
            teacherService.assignClass(id, classId);
        } else if (choice == 5) {
            String id = input.readText("Teacher id to remove");
            boolean removed = teacherService.removeById(id);
            System.out.println(removed ? "Removed." : "Not found.");
        } else if (choice == 6) {
            String subject = input.readText("Subject");
            teacherService.listBySubject(subject);
        } else if (choice == 7) {
            teacherService.availableTeachers();
        }
    }

    // ---------- courses ----------

    private void courseMenu() {
        System.out.println("--- Courses ---");
        System.out.println("1. Add   2. View all   3. Search   4. Finalize   5. Remove   6. By term   7. Count finalized");
        int choice = input.readInt("Choose", 1, 7);

        if (choice == 1) {
            int studentId = input.readInt("Student id");
            int teacherId = input.readInt("Teacher id");
            String term = input.readText("Term");
            char grade = input.readChar("Grade (A-F)");
            CourseRecord r = new CourseRecord(HelperUtils.generateNumericId(),
                    studentId, teacherId, term, grade, "", "", false);
            courseService.add(r);
            System.out.println("Added course record with id " + r.getRecordId());
        } else if (choice == 2) {
            printEntities(courseService.getAll());
        } else if (choice == 3) {
            String keyword = input.readText("Search keyword");
            printEntities(courseService.search(keyword));
        } else if (choice == 4) {
            String id = input.readText("Record id to finalize");
            Object found = courseService.searchById(id);
            if (found == null) {
                System.out.println("Not found.");
            } else {
                ((CourseRecord) found).finalizeRecord();
                System.out.println("Finalized.");
            }
        } else if (choice == 5) {
            String id = input.readText("Record id to remove");
            boolean removed = courseService.removeById(id);
            System.out.println(removed ? "Removed." : "Not found.");
        } else if (choice == 6) {
            String term = input.readText("Term");
            courseService.listByTerm(term);
        } else if (choice == 7) {
            System.out.println("Finalized records: " + courseService.countFinalized());
        }
    }

    // ---------- enrollments ----------

    private void enrollmentMenu() {
        System.out.println("--- Enrollments ---");
        System.out.println("1. Add   2. View all   3. Search   4. Cancel   5. Complete   6. Transfer   7. By status   8. By student");
        int choice = input.readInt("Choose", 1, 8);

        if (choice == 1) {
            String studentId = input.readText("Student id");
            String courseId = input.readText("Course id");
            String date = input.readText("Enroll date (YYYY-MM-DD)");
            Enrollment e = enrollmentService.enroll(studentId, courseId, date);
            System.out.println("Added enrollment with id " + e.getEnrollmentId());
        } else if (choice == 2) {
            printEntities(enrollmentService.getAll());
        } else if (choice == 3) {
            String keyword = input.readText("Search keyword");
            printEntities(enrollmentService.search(keyword));
        } else if (choice == 4) {
            String id = input.readText("Enrollment id to cancel");
            enrollmentService.cancel(id);
        } else if (choice == 5) {
            String id = input.readText("Enrollment id to complete");
            enrollmentService.complete(id);
        } else if (choice == 6) {
            String id = input.readText("Enrollment id to transfer");
            String newCourse = input.readText("New course id");
            String newDate = input.readText("New date");
            enrollmentService.transfer(id, newCourse, newDate);
        } else if (choice == 7) {
            String[] allowed = { "Active", "Completed", "Cancelled" };
            String status = input.readOneOf("Status", allowed);
            enrollmentService.listByStatus(status);
        } else if (choice == 8) {
            String id = input.readText("Student id");
            enrollmentService.listByStudent(id);
        }
    }

    // ---------- polymorphism helpers ----------

    // gather every student and teacher into ONE Person[] (no generics)
    private Person[] buildAllPeople() {
        Object[] students = studentService.getAll();
        Object[] teachers = teacherService.getAll();
        Person[] people = new Person[students.length + teachers.length];
        int pos = 0;
        for (int i = 0; i < students.length; i++) {
            people[pos] = (Person) students[i];
            pos = pos + 1;
        }
        for (int i = 0; i < teachers.length; i++) {
            people[pos] = (Person) teachers[i];
            pos = pos + 1;
        }
        return people;
    }

    // POLYMORPHISM: one loop over the parent type, each object shows itself
    private void printAll(Person[] people, int count) {
        for (int i = 0; i < count; i++) {
            people[i].displayInfo(); // the right displayInfo runs for each type
            System.out.println();
        }
    }

    // count how many are of each type (most specific type first)
    private void countByType(Person[] people, int count) {
        int seniors = 0;
        int students = 0;
        int heads = 0;
        int teachers = 0;
        for (int i = 0; i < count; i++) {
            Person p = people[i];
            if (p instanceof SeniorStudent) {
                seniors = seniors + 1;   // a SeniorStudent is also a Student, so check it first
            } else if (p instanceof Student) {
                students = students + 1;
            } else if (p instanceof HeadTeacher) {
                heads = heads + 1;
            } else if (p instanceof Teacher) {
                teachers = teachers + 1;
            }
        }
        System.out.println("Senior students : " + seniors);
        System.out.println("Regular students: " + students);
        System.out.println("Head teachers   : " + heads);
        System.out.println("Regular teachers: " + teachers);
    }

    // walk the collection and return the person with the highest age
    private Person findOldest(Person[] people, int count) {
        if (count == 0) {
            return null;
        }
        Person oldest = people[0];
        for (int i = 1; i < count; i++) {
            if (people[i].getAge() > oldest.getAge()) {
                oldest = people[i];
            }
        }
        return oldest;
    }

    // ---------- reports (menu option 5) ----------

    private void reportsMenu() {
        System.out.println("========== REPORTS ==========");

        // build ONE Person collection holding students AND teachers (all types)
        Person[] people = buildAllPeople();
        int count = people.length;

        System.out.println();
        System.out.println("[All people - polymorphic displayInfo]");
        printAll(people, count);

        System.out.println("[Counts by type]");
        countByType(people, count);

        System.out.println();
        System.out.println("[Oldest person]");
        Person oldest = findOldest(people, count);
        if (oldest != null) {
            oldest.displaySummary();
            System.out.println("Age: " + oldest.getAge());
        }

        System.out.println();
        System.out.println("[Totals]");
        System.out.println("Total outstanding fees  : " + studentService.totalOutstanding());
        System.out.println("Finalized course records: " + courseService.countFinalized());
        System.out.println("Students   : " + studentService.getCount());
        System.out.println("Teachers   : " + teacherService.getCount());
        System.out.println("Courses    : " + courseService.getCount());
        System.out.println("Enrollments: " + enrollmentService.getCount());
    }

    // ---------- sample data ----------

    private void seedSampleData() {
        System.out.println("Seeding sample data...");

        // --- 6 students: 4 regular + 2 senior ---
        Student s1 = new Student(HelperUtils.generateNumericId(), "Aisha", "Khan", "2010-03-11",
                "Female", 91234501, "aisha@school.test", "Muscat", 100001, 15, true,
                "9", "2023-09-01", 120.50, false);
        s1.addSubject("Math");
        s1.addSubject("Science");
        studentService.addStudent(s1); // addStudent(Student) overload

        Student s2 = new Student(HelperUtils.generateNumericId(), "Omar", "Ali", "2011-06-20",
                "Male", 91234502, "omar@school.test", "Muscat", 100002, 14, true,
                "8", "2023-09-01", 0.0, true);
        s2.addSubject("English");
        studentService.addStudent(s2);

        studentService.addStudent("Sara", "Yusuf", 13);            // basic-details overload
        studentService.addStudent("Bilal", "Noor", 16, "11");      // details + grade overload

        SeniorStudent se1 = new SeniorStudent(HelperUtils.generateNumericId(), "Layla", "Hassan",
                "2006-02-01", "Female", 91234503, "layla@school.test", "Salalah", 100005, 18, true,
                "12", "2020-09-01", 300.0, false, "Computer Science", 3.7, "2025-06-30", 90);
        se1.addSubject("Programming");
        studentService.addStudent(se1);

        SeniorStudent se2 = new SeniorStudent(HelperUtils.generateNumericId(), "Yusuf", "Said",
                "2005-11-15", "Male", 91234504, "yusuf@school.test", "Sohar", 100006, 19, true,
                "12", "2019-09-01", 0.0, true, "Physics", 3.9, "2024-06-30", 110);
        studentService.addStudent(se2);

        // --- 4 teachers: 3 regular + 1 head ---
        Teacher t1 = new Teacher(HelperUtils.generateNumericId(), "Fatima", "Rashid", "1985-05-05",
                "Female", 99900101, "fatima@school.test", "Muscat", 200001, 40, true,
                "Math", 15, 1200.0, true);
        t1.addSlot("Mon-9am");
        t1.assignClass("CLASS-9A");
        teacherService.add(t1);

        Teacher t2 = new Teacher(HelperUtils.generateNumericId(), "Khalid", "Amir", "1990-08-08",
                "Male", 99900102, "khalid@school.test", "Muscat", 200002, 35, true,
                "Science", 10, 1100.0, false);
        teacherService.add(t2);

        Teacher t3 = new Teacher(HelperUtils.generateNumericId(), "Mona", "Salim", "1992-01-01",
                "Female", 99900103, "mona@school.test", "Muscat", 200003, 33, true,
                "English", 8, 1000.0, false);
        teacherService.add(t3);

        HeadTeacher head = new HeadTeacher(HelperUtils.generateNumericId(), "Nabil", "Zayed",
                "1975-12-12", "Male", 99900104, "nabil@school.test", "Muscat", 200004, 50, true,
                "Administration", 25, 2000.0, true, 3, true);
        head.scheduleMeeting("2025-09-15");
        head.addManagedTeacher();
        teacherService.addHeadTeacher(head);

        // --- 5 course records ---
        CourseRecord c1 = new CourseRecord(HelperUtils.generateNumericId(), s1.getId(), t1.getId(),
                "2024-Term1", 'A', "Great work", "", true);
        courseService.add(c1);
        courseService.add(new CourseRecord(HelperUtils.generateNumericId(), s2.getId(), t3.getId(),
                "2024-Term1", 'B', "Good", "", true));
        courseService.add(new CourseRecord(HelperUtils.generateNumericId(), se1.getId(), t2.getId(),
                "2024-Term1", 'A', "Solid", "", false));
        courseService.add(new CourseRecord(HelperUtils.generateNumericId(), se2.getId(), t1.getId(),
                "2024-Term2", 'A', "Excellent", "", true));
        courseService.add(new CourseRecord(HelperUtils.generateNumericId(), s1.getId(), t2.getId(),
                "2024-Term2", 'B', "Improving", "", false));

        // --- 6 enrollments, using all three enroll overloads ---
        enrollmentService.enroll(String.valueOf(s1.getId()), "COURSE-MATH", "2024-09-01");
        enrollmentService.enroll(String.valueOf(s2.getId()), "COURSE-ENG", "2024-09-01", "2024-Term1");
        enrollmentService.enroll(String.valueOf(se1.getId()), "COURSE-CS", "2024-09-01", "2024-Term1");
        enrollmentService.enroll(se2, c1, "Repeating for a higher grade"); // objects + reason overload
        Enrollment e5 = enrollmentService.enroll(String.valueOf(s1.getId()), "COURSE-SCI", "2024-09-05");
        e5.complete(); // exercise a status change
        enrollmentService.enroll(String.valueOf(se1.getId()), "COURSE-ART", "2024-09-10");

        System.out.println("Sample data ready.");
        System.out.println();
    }
}
