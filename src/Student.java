public class Student {

    // Student details
    String registerNo;
    String name;
    String course;

    // Seating details
    String hall;
    String seat;

    // Link to the next student
    Student next;

    // Constructor
    public Student(String registerNo, String name, String course) {
        this.registerNo = registerNo;
        this.name = name;
        this.course = course;

        this.hall = "";
        this.seat = "";

        // Initially there is no next student
        this.next = null;
    }

    // Display student details
    public void displayStudent() {

        System.out.println("Register Number : " + registerNo);
        System.out.println("Name            : " + name);
        System.out.println("Course          : " + course);
        System.out.println("Hall            : " + hall);
        System.out.println("Seat            : " + seat);
        System.out.println("----------------------------");
    }
}