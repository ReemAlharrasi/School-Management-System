package entities;

import utils.HelperUtils;

// Second inheritance level: HeadTeacher IS A Teacher IS A Person.
// Building a HeadTeacher chains super(...) up: HeadTeacher -> Teacher -> Person.
public class HeadTeacher extends Teacher {

    private int teachersManaged;
    private boolean adminOfficeAccess;

    // a collection of upcoming meeting dates (no generics: array + counter)
    private String[] meetingDates;
    private int meetingCount;

    public HeadTeacher(Integer id, String firstName, String lastName, String dateOfBirth,
                       String gender, Integer phoneNumber, String email, String address,
                       Integer nationalId, int age, boolean activeStatus,
                       String subject, int experienceYears, double salary, boolean isFormTeacher,
                       int teachersManaged, boolean adminOfficeAccess) {
        // this super(...) calls the Teacher constructor, which calls Person
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address,
                nationalId, age, activeStatus, subject, experienceYears, salary, isFormTeacher);
        setTeachersManaged(teachersManaged);
        this.adminOfficeAccess = adminOfficeAccess;
        this.meetingDates = new String[10];
        this.meetingCount = 0;
    }

    //getters
    public int getTeachersManaged() {return teachersManaged;}
    public boolean hasAdminOfficeAccess() {return adminOfficeAccess;}
    public int getUpcomingCount() {return meetingCount;}

    //setters
    public void setTeachersManaged(int teachersManaged) {
        if (!HelperUtils.isPositive(teachersManaged)) {
            System.out.println("Rejected: teachers managed cannot be negative.");
            return;
        }
        this.teachersManaged = teachersManaged;
    }

    public void setAdminOfficeAccess(boolean access) {this.adminOfficeAccess = access;}

    //methods
    // one more teacher now reports to this head teacher
    public void addManagedTeacher() {
        teachersManaged = teachersManaged + 1;
    }

    public void scheduleMeeting(String date) {
        if (HelperUtils.isEmpty(date)) {
            System.out.println("Rejected: meeting date cannot be empty.");
            return;
        }
        if (meetingCount >= meetingDates.length) {
            System.out.println("Rejected: meeting list is full.");
            return;
        }
        meetingDates[meetingCount] = date;
        meetingCount = meetingCount + 1;
    }

    public void listMeetings() {
        System.out.println("Meetings for " + getFullName() + ":");
        if (meetingCount == 0) {
            System.out.println("  (none)");
            return;
        }
        for (int i = 0; i < meetingCount; i++) {
            System.out.println("  - " + meetingDates[i]);
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("[Head teacher details]");
        System.out.println("Manages      : " + teachersManaged + " teachers");
        System.out.println("Office access: " + adminOfficeAccess);
        System.out.println("Meetings     : " + meetingCount);
    }

    @Override
    public void displaySummary() {
        System.out.println("[Head] " + getId() + " - " + getFullName() + " (" + getSubject() + ")");
    }
}
