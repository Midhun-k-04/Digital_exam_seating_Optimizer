import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("JDBC connection is working.");
        } else {
            System.out.println("JDBC connection failed.");
        }
    }
}