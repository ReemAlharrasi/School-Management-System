package entities;

import utils.HelperUtils;

// A Teacher IS A Person, so it extends Person.
public class Teacher extends Person{
    private String subject;
    private int experienceYears;
    private double salary;

    private String[] timeSlots;
    private int slotCount;

    private String[] assignedClassIds;
    private int classCount;

    private boolean isFormTeacher;

    //constructor
    public Teacher(Integer id, String firstName, String lastName, String dateOfBirth,
                   String gender, Integer phoneNumber, String email, String address,
                   Integer nationalId, int age, boolean activeStatus,
                   String subject, int experienceYears, double salary, boolean isFormTeacher) {
        // super(...) fills the parent's part first
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address,
                nationalId, age, activeStatus);
        setSubject(subject);
        setExperienceYears(experienceYears);
        setSalary(salary);
        this.isFormTeacher = isFormTeacher;
        this.timeSlots = new String[10];
        this.slotCount = 0;
        this.assignedClassIds = new String[10];
        this.classCount = 0;
    }

    //setters
    public void setSubject(String subject) {
        if (HelperUtils.isEmpty(subject)) {
            System.out.println("Rejected: subject cannot be empty.");
            return;
        }
        this.subject = subject;
    }

    public void setExperienceYears(int experienceYears) {
        if (!HelperUtils.isPositive(experienceYears)) {
            System.out.println("Rejected: experience years cannot be negative.");
            return;
        }
        this.experienceYears = experienceYears;
    }

    public void setSalary(double salary) {
        if (!HelperUtils.isPositive(salary)) {
            System.out.println("Rejected: salary cannot be negative.");
            return;
        }
        this.salary = salary;
    }

    public void setFormTeacher(boolean formTeacher) {isFormTeacher = formTeacher;}

    //getters
    public String getSubject() {return subject;}
    public int getExperienceYears() {return experienceYears;}
    public double getSalary() {return salary;}
    public String[] getTimeSlots() {return timeSlots;}
    public int getSlotCount() {return slotCount;}
    public String[] getAssignedClassIds() {return assignedClassIds;}
    public int getClassCount() {return classCount;}
    public boolean isFormTeacher() {return isFormTeacher;}

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("[Teacher details]");
        System.out.println("Subject     : " + subject);
        System.out.println("Experience  : " + experienceYears + " years");
        System.out.println("Salary      : " + salary);
        System.out.println("Form teacher: " + isFormTeacher);
        System.out.println("Slots       : " + slotCount + ", classes: " + classCount);
    }

    @Override
    public void displaySummary() {
        System.out.println("[Teacher] " + getId() + " - " + getFullName() + " (" + subject + ")");
    }

    public void addSlot(String slot){
        if (HelperUtils.isEmpty(slot)){
            System.out.println("Rejected: slot cannot be empty.");
            return;
        }
        if (slotCount >= timeSlots.length) {
            System.out.println("Rejected: slot list is full.");
            return;
        }
        timeSlots[slotCount] = slot;
        slotCount = slotCount + 1;
    }

    public void removeSlot(String slot){
        int found = -1;
        for (int i = 0; i < slotCount; i++) {
            if (timeSlots[i].equalsIgnoreCase(slot)) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            System.out.println("Slot not found: " + slot);
            return;
        }
        for (int i = found; i < slotCount - 1; i++) {
            timeSlots[i] = timeSlots[i + 1];
        }
        timeSlots[slotCount - 1] = null;
        slotCount = slotCount - 1;
    }

    public boolean hasSlot(String slot){
        for (int i = 0; i < slotCount; i++) {
            if (timeSlots[i].equalsIgnoreCase(slot)) {
                return true;
            }
        }
        return false;
    }

    public void assignClass(String classId){
        if (HelperUtils.isEmpty(classId)){
            System.out.println("Rejected: class id cannot be empty.");
            return;
        }
        if (classCount >= assignedClassIds.length) {
            System.out.println("Rejected: class list is full.");
            return;
        }
        assignedClassIds[classCount] = classId;
        classCount = classCount + 1;
    }

    public int getClassLoad(){
        return classCount;
    }

    public void raiseSalary(double amount){
        if (!HelperUtils.isPositive(amount)){
            System.out.println("Rejected: raise amount cannot be negative.");
            return;
        }
        setSalary(getSalary()+amount);
    }

    // ---------- update salary (2 overloads = method overloading) ----------

    // salary only
    public void updateSalary(double newSalary) {
        setSalary(newSalary);
    }

    // salary with a reason
    public void updateSalary(double newSalary, String reason) {
        setSalary(newSalary);
        System.out.println("Salary of " + getFullName() + " updated. Reason: " + reason);
    }
}
