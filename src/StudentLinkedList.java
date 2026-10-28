public class StudentLinkedList {

    // First student in the list
    Student head;

    // Add a student to the list
    public void addStudent(Student student) {

        if (head == null) {
            head = student;
            return;
        }

        Student current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = student;
    }

    // Display all students
    public void displayStudents() {

        if (head == null) {
            System.out.println("No students available.");
            return;
        }

        Student current = head;

        while (current != null) {
            current.displayStudent();
            current = current.next;
        }
    }

    // Linear Search
    public Student searchStudent(String registerNo) {

        Student current = head;

        while (current != null) {

            if (current.registerNo.equals(registerNo)) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    // Delete a student
    public boolean deleteStudent(String registerNo) {

        if (head == null) {
            return false;
        }

        // Delete first student
        if (head.registerNo.equals(registerNo)) {
            head = head.next;
            return true;
        }

        Student current = head;

        while (current.next != null) {

            if (current.next.registerNo.equals(registerNo)) {
                current.next = current.next.next;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // Bubble Sort by Register Number
    public void bubbleSort() {

        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;

        do {

            swapped = false;

            Student current = head;

            while (current.next != null) {

                if (current.registerNo.compareTo(
                        current.next.registerNo) > 0) {

                    // Store current student details
                    String tempRegisterNo = current.registerNo;
                    String tempName = current.name;
                    String tempCourse = current.course;
                    String tempHall = current.hall;
                    String tempSeat = current.seat;

                    // Copy next student's details to current
                    current.registerNo = current.next.registerNo;
                    current.name = current.next.name;
                    current.course = current.next.course;
                    current.hall = current.next.hall;
                    current.seat = current.next.seat;

                    // Copy old current details to next
                    current.next.registerNo = tempRegisterNo;
                    current.next.name = tempName;
                    current.next.course = tempCourse;
                    current.next.hall = tempHall;
                    current.next.seat = tempSeat;

                    swapped = true;
                }

                current = current.next;
            }

        } while (swapped);
    }
}