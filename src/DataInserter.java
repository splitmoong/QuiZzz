import java.sql.*;
public class DataInserter {
    public static void insertData(String data1, String data2) {
        String sql = "INSERT INTO your_table (column1, column2) VALUES (?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, data1);
            preparedStatement.setString(2, data2);

            preparedStatement.executeUpdate(); // Execute the INSERT statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
