package services;

import entities.Teacher;
import entities.HeadTeacher;
import interfaces.Manageable;
import interfaces.Searchable;
import utils.HelperUtils;

// Stores teachers in a plain array (no generics).
public class TeacherService implements Manageable, Searchable {

    private Teacher[] teachers;
    private int count;

    public TeacherService() {
        teachers = new Teacher[100];
        count = 0;
    }

    // ---------- Manageable ----------

    @Override
    public void add(Object item) {
        if (!(item instanceof Teacher)) {
            System.out.println("Rejected: not a teacher.");
            return;
        }
        if (count >= teachers.length) {
            System.out.println("Rejected: teacher store is full.");
            return;
        }
        teachers[count] = (Teacher) item;
        count = count + 1;
    }

    @Override
    public boolean removeById(String id) {
        int found = -1;
        for (int i = 0; i < count; i++) {
            if (HelperUtils.sameId(teachers[i].getId(), id)) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            return false;
        }
        for (int i = found; i < count - 1; i++) {
            teachers[i] = teachers[i + 1];
        }
        teachers[count - 1] = null;
        count = count - 1;
        return true;
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[count];
        for (int i = 0; i < count; i++) {
            result[i] = teachers[i];
        }
        return result;
    }

    // ---------- Searchable ----------

    @Override
    public Object[] search(String keyword) {
        int matches = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(teachers[i], keyword)) {
                matches = matches + 1;
            }
        }
        Object[] result = new Object[matches];
        int pos = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(teachers[i], keyword)) {
                result[pos] = teachers[i];
                pos = pos + 1;
            }
        }
        return result;
    }

    private boolean matchesKeyword(Teacher t, String keyword) {
        if (HelperUtils.isEmpty(keyword)) {
            return false;
        }
        String k = keyword.toLowerCase();
        return t.getFullName().toLowerCase().contains(k)
                || String.valueOf(t.getId()).contains(k)
                || t.getSubject().toLowerCase().contains(k);
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (HelperUtils.sameId(teachers[i].getId(), id)) {
                return teachers[i];
            }
        }
        return null;
    }

    // ---------- service-specific ----------

    // a HeadTeacher IS A Teacher, so it goes in the same store
    public void addHeadTeacher(HeadTeacher head) {
        add(head);
    }

    // give a class to the teacher with this id
    public void assignClass(String teacherId, String classId) {
        Object found = searchById(teacherId);
        if (found == null) {
            System.out.println("No teacher with id " + teacherId);
            return;
        }
        Teacher t = (Teacher) found;
        t.assignClass(classId);
    }

    // print every teacher who teaches a given subject
    public void listBySubject(String subject) {
        System.out.println("--- Teachers of " + subject + " ---");
        boolean any = false;
        for (int i = 0; i < count; i++) {
            if (teachers[i].getSubject().equalsIgnoreCase(subject)) {
                teachers[i].displaySummary();
                any = true;
            }
        }
        if (!any) {
            System.out.println("(none)");
        }
    }

    // print teachers who still have room for more classes (fewer than 5)
    public void availableTeachers() {
        System.out.println("--- Available teachers (class load < 5) ---");
        boolean any = false;
        for (int i = 0; i < count; i++) {
            if (teachers[i].getClassLoad() < 5) {
                teachers[i].displaySummary();
                any = true;
            }
        }
        if (!any) {
            System.out.println("(none)");
        }
    }

    public int getCount() {
        return count;
    }
}
