import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // MySQL database URL
    private static final String URL =
            "jdbc:mysql://localhost:3306/digital_exam_seating";

    // MySQL username
    private static final String USER = "root";

    // MySQL password
    private static final String PASSWORD = "Midhun@2004";

    // Connect to database
    public static Connection getConnection() {

        try {

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("Database connected successfully.");

            return connection;

        } catch (SQLException e) {

            System.out.println("Database connection failed.");
            e.printStackTrace();

            return null;
        }
    }
}