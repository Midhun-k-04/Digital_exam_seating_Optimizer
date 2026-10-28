public class GreedyAllocator {

    public void allocateSeats(
            StudentLinkedList studentList,
            Seat[] seats) {

        Student currentStudent = studentList.head;

        while (currentStudent != null) {

            boolean seatAssigned = false;

            for (int i = 0; i < seats.length; i++) {

                if (!seats[i].isAvailable()) {
                    continue;
                }

                if (hasConflict(currentStudent, studentList, seats, i)) {
                    continue;
                }

                currentStudent.hall = seats[i].hall;
                currentStudent.seat = seats[i].seatNumber;

                seats[i].assignSeat();

                System.out.println(
                    currentStudent.name +
                    " -> " +
                    seats[i].hall +
                    " -> " +
                    seats[i].seatNumber
                );

                seatAssigned = true;
                break;
            }

            if (!seatAssigned) {
                System.out.println(
                    "No suitable seat available for " +
                    currentStudent.name
                );
            }

            currentStudent = currentStudent.next;
        }
    }

    private boolean hasConflict(
            Student student,
            StudentLinkedList studentList,
            Seat[] seats,
            int seatIndex) {

        // Check previous seat
        if (seatIndex > 0 &&
            seats[seatIndex - 1].occupied) {

            Student previousStudent =
                    findStudentBySeat(
                        studentList,
                        seats[seatIndex - 1]
                    );

            if (previousStudent != null &&
                previousStudent.course.equals(student.course)) {

                return true;
            }
        }

        // Check next seat
        if (seatIndex < seats.length - 1 &&
            seats[seatIndex + 1].occupied) {

            Student nextStudent =
                    findStudentBySeat(
                        studentList,
                        seats[seatIndex + 1]
                    );

            if (nextStudent != null &&
                nextStudent.course.equals(student.course)) {

                return true;
            }
        }

        return false;
    }

    private Student findStudentBySeat(
            StudentLinkedList studentList,
            Seat seat) {

        Student current = studentList.head;

        while (current != null) {

            if (current.hall.equals(seat.hall) &&
                current.seat.equals(seat.seatNumber)) {

                return current;
            }

            current = current.next;
        }

        return null;
    }
}