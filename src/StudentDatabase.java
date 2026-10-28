import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDatabase {

    // Load students from MySQL
    public StudentLinkedList getStudents() {

        StudentLinkedList studentList = new StudentLinkedList();

        String query =
                "SELECT register_no, name, course FROM students";

        try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet result =
                    statement.executeQuery()
        ) {

            while (result.next()) {

                String registerNo =
                        result.getString("register_no");

                String name =
                        result.getString("name");

                String course =
                        result.getString("course");

                Student student =
                        new Student(
                                registerNo,
                                name,
                                course
                        );

                studentList.addStudent(student);
            }

            System.out.println(
                    "Students loaded from MySQL successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error loading students from MySQL."
            );

            e.printStackTrace();
        }

        return studentList;
    }
}