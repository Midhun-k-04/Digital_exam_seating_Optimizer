import java.sql.Connection;
import java.sql.PreparedStatement;

public class SeatingDatabase {

    // Save hall and seat for a student
    public void updateSeating(
            String registerNo,
            String hall,
            String seat) {

        String query =
                "UPDATE students SET hall = ?, seat = ? " +
                "WHERE register_no = ?";

        try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query)
        ) {

            statement.setString(1, hall);
            statement.setString(2, seat);
            statement.setString(3, registerNo);

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Seating saved for " + registerNo
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error saving seating."
            );

            e.printStackTrace();
        }
    }
}