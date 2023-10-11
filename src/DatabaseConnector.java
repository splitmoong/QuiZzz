import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/moong";
        String username = "root";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO players (player_username, player_password, score_topic01, score_topic02, score_topic03, score_topic04)" + "VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "akshay");
            preparedStatement.setString(2, "password123");
            preparedStatement.setInt(3,0);
            preparedStatement.setInt(4,7);
            preparedStatement.setInt(5,0);
            preparedStatement.setInt(6,0);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted.");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}




