import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection(String user_username, String user_password) {
        String url = "jdbc:mysql://localhost:3306/moong";
        String username = "root";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String checkSql = "SELECT COUNT(*) FROM players WHERE player_username = ?";
            PreparedStatement checkStatement = conn.prepareStatement(checkSql);
            checkStatement.setString(1, user_username);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    System.out.println("Username already exists.");
                    return conn; // Return the connection without inserting
                }
            }


            String sql = "INSERT INTO players (player_username, player_password)" + "VALUES (?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user_username);
            preparedStatement.setString(2, user_password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted.");
            }
            QuizLogic logic = new QuizLogic();
            new QuizUI(logic);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Connection checkUserValid(String user_username, String user_password) {
        String url = "jdbc:mysql://localhost:3306/moong";
        String username = "root";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            // Replace 'your_table' with the actual table name where user information is stored
            String selectSql = "SELECT player_password FROM players WHERE player_username = ? AND player_password = ?";

            PreparedStatement selectStatement = conn.prepareStatement(selectSql);
            selectStatement.setString(1, user_username);
            selectStatement.setString(2, user_password);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("inside");
                QuizLogic logic = new QuizLogic();
                new QuizUI(logic);
                return conn;
            } else {
                conn.close(); // Close the connection
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Handle the exception and return null
        }
    }

    public static int getHighScore() {
        String query = "SELECT MAX(score) AS highscore FROM players";
        String url = "jdbc:mysql://localhost:3306/moong";
        String username = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int highScore = resultSet.getInt("highscore");
                return highScore;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static Connection addUserScore(int user_score){
        String url = "jdbc:mysql://localhost:3306/moong";
        String username = "root";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String selectLastIdSql = "SELECT id FROM players ORDER BY id DESC LIMIT 1";
            PreparedStatement selectStatement = conn.prepareStatement(selectLastIdSql);
            ResultSet resultSet = selectStatement.executeQuery();
            int lastAddedId = 0;

            if (resultSet.next()) {
                lastAddedId = resultSet.getInt("id");
            }
            System.out.println(lastAddedId);

            // Step 2: Insert the score with the retrieved ID
            String insertScoreSql = "UPDATE players SET score = ? WHERE id = ?";
            PreparedStatement insertStatement = conn.prepareStatement(insertScoreSql);
            insertStatement.setInt(1, user_score);
            insertStatement.setInt(2, lastAddedId);
            int rowsInserted = insertStatement.executeUpdate();

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





