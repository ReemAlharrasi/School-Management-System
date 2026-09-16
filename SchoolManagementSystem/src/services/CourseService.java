package services;

import entities.CourseRecord;
import interfaces.Manageable;
import interfaces.Searchable;
import utils.HelperUtils;

// Stores course records in a plain array (no generics).
public class CourseService implements Manageable, Searchable {

    private CourseRecord[] records;
    private int count;

    public CourseService() {
        records = new CourseRecord[100];
        count = 0;
    }

    // ---------- Manageable ----------

    @Override
    public void add(Object item) {
        if (!(item instanceof CourseRecord)) {
            System.out.println("Rejected: not a course record.");
            return;
        }
        if (count >= records.length) {
            System.out.println("Rejected: course store is full.");
            return;
        }
        records[count] = (CourseRecord) item;
        count = count + 1;
    }

    @Override
    public boolean removeById(String id) {
        int found = -1;
        for (int i = 0; i < count; i++) {
            if (HelperUtils.sameId(records[i].getRecordId(), id)) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            return false;
        }
        for (int i = found; i < count - 1; i++) {
            records[i] = records[i + 1];
        }
        records[count - 1] = null;
        count = count - 1;
        return true;
    }

    @Override
    public Object[] getAll() {
        Object[] result = new Object[count];
        for (int i = 0; i < count; i++) {
            result[i] = records[i];
        }
        return result;
    }

    // ---------- Searchable ----------

    @Override
    public Object[] search(String keyword) {
        int matches = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(records[i], keyword)) {
                matches = matches + 1;
            }
        }
        Object[] result = new Object[matches];
        int pos = 0;
        for (int i = 0; i < count; i++) {
            if (matchesKeyword(records[i], keyword)) {
                result[pos] = records[i];
                pos = pos + 1;
            }
        }
        return result;
    }

    private boolean matchesKeyword(CourseRecord r, String keyword) {
        if (HelperUtils.isEmpty(keyword)) {
            return false;
        }
        String k = keyword.toLowerCase();
        return String.valueOf(r.getRecordId()).contains(k)
                || String.valueOf(r.getStudentId()).contains(k)
                || r.getTerm().toLowerCase().contains(k);
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (HelperUtils.sameId(records[i].getRecordId(), id)) {
                return records[i];
            }
        }
        return null;
    }

    // ---------- service-specific ----------

    // print every record in a given term
    public void listByTerm(String term) {
        System.out.println("--- Records for term " + term + " ---");
        boolean any = false;
        for (int i = 0; i < count; i++) {
            if (records[i].getTerm().equalsIgnoreCase(term)) {
                records[i].displaySummary();
                any = true;
            }
        }
        if (!any) {
            System.out.println("(none)");
        }
    }

    // print every record that belongs to one student
    public void listByStudent(String studentId) {
        System.out.println("--- Records for student " + studentId + " ---");
        boolean any = false;
        for (int i = 0; i < count; i++) {
            if (HelperUtils.sameId(records[i].getStudentId(), studentId)) {
                records[i].displaySummary();
                any = true;
            }
        }
        if (!any) {
            System.out.println("(none)");
        }
    }

    // count how many records are finalized
    public int countFinalized() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (records[i].isFinalized()) {
                total = total + 1;
            }
        }
        return total;
    }

    public int getCount() {
        return count;
    }
}
