package entities;

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
    public void setSubject(String subject) {this.subject = subject;}
    public void setExperienceYears(int experienceYears) {this.experienceYears = experienceYears;}
    public void setSalary(double salary) {this.salary = salary;}
    public void setTimeSlots(String[] timeSlots) {this.timeSlots = timeSlots;}
    public void setSlotCount(int slotCount) {this.slotCount = slotCount;}
    public void setAssignedClassIds(String[] assignedClassIds) {this.assignedClassIds = assignedClassIds;}
    public void setClassCount(int classCount) {this.classCount = classCount;}
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

    public void addSlot(String slot){
        if (slot.isBlank()){
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
        if (classId.isBlank()){
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
        if (amount<0){
            System.out.println("Rejected: raise amount cannot be negative.");
            return;
        }
        setSalary(getSalary()+amount);
    }
}
