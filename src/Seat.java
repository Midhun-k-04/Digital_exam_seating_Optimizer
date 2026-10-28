public class Seat {

    // Seat details
    String hall;
    String seatNumber;
    boolean occupied;

    // Constructor
    public Seat(String hall, String seatNumber) {
        this.hall = hall;
        this.seatNumber = seatNumber;
        this.occupied = false;
    }

    // Check whether the seat is available
    public boolean isAvailable() {
        return !occupied;
    }

    // Occupy the seat
    public void assignSeat() {
        occupied = true;
    }

    // Display seat information
    public void displaySeat() {
        System.out.println(
            "Hall: " + hall +
            " | Seat: " + seatNumber +
            " | Status: " + (occupied ? "Occupied" : "Available")
        );
    }
}