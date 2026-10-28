public class Main {

    public static void main(String[] args) {

        // ==========================================
        // 1. LOAD STUDENTS FROM MYSQL
        // ==========================================

        StudentDatabase database = new StudentDatabase();

        StudentLinkedList studentList =
                database.getStudents();


        // ==========================================
        // 2. DISPLAY STUDENTS
        // ==========================================

        System.out.println();
        System.out.println("===== STUDENTS FROM MYSQL =====");

        studentList.displayStudents();


        // ==========================================
        // 3. CREATE EXAM SEATS
        // ==========================================

        Seat[] seats = {

            new Seat("Hall A", "A1"),
            new Seat("Hall A", "A2"),
            new Seat("Hall A", "A3"),
            new Seat("Hall A", "A4"),
            new Seat("Hall A", "A5"),

            new Seat("Hall B", "B1"),
            new Seat("Hall B", "B2"),
            new Seat("Hall B", "B3"),
            new Seat("Hall B", "B4"),
            new Seat("Hall B", "B5")
        };


        // ==========================================
        // 4. GREEDY SEAT ALLOCATION
        // ==========================================

        GreedyAllocator allocator =
                new GreedyAllocator();

        System.out.println();
        System.out.println("===== GREEDY SEAT ALLOCATION =====");

        allocator.allocateSeats(
                studentList,
                seats
        );


        // ==========================================
        // 5. SAVE SEATING INTO MYSQL
        // ==========================================

        SeatingDatabase seatingDatabase =
                new SeatingDatabase();

        Student currentStudent =
                studentList.head;

        while (currentStudent != null) {

            seatingDatabase.updateSeating(
                    currentStudent.registerNo,
                    currentStudent.hall,
                    currentStudent.seat
            );

            currentStudent =
                    currentStudent.next;
        }


        // ==========================================
        // 6. DISPLAY FINAL SEATING
        // ==========================================

        System.out.println();
        System.out.println("===== FINAL SEATING ARRANGEMENT =====");

        studentList.displayStudents();


        // ==========================================
        // 7. LINEAR SEARCH
        // ==========================================

        System.out.println();
        System.out.println("===== LINEAR SEARCH =====");

        String searchRegisterNo =
                "26MCAB03";

        Student foundStudent =
                studentList.searchStudent(
                        searchRegisterNo
                );

        if (foundStudent != null) {

            System.out.println("Student Found");

            System.out.println(
                    "Register Number : " +
                    foundStudent.registerNo
            );

            System.out.println(
                    "Name            : " +
                    foundStudent.name
            );

            System.out.println(
                    "Hall            : " +
                    foundStudent.hall
            );

            System.out.println(
                    "Seat            : " +
                    foundStudent.seat
            );

        } else {

            System.out.println("Student not found.");
        }


        // ==========================================
        // 8. BUBBLE SORT
        // ==========================================

        System.out.println();
        System.out.println("===== BEFORE BUBBLE SORT =====");

        studentList.displayStudents();


        System.out.println();
        System.out.println("===== AFTER BUBBLE SORT =====");

        studentList.bubbleSort();

        studentList.displayStudents();
    }
}