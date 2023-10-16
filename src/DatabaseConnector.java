import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static String user = "";
    private static int personal_best;
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
                    return conn; // Return the co nnection without inserting
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
            user = user_username;
            System.out.println(user);
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
            String selectSql = "SELECT id, player_username FROM players WHERE player_username = ? AND player_password = ?";

            PreparedStatement selectStatement = conn.prepareStatement(selectSql);
            selectStatement.setString(1, user_username);
            selectStatement.setString(2, user_password);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                user = resultSet.getString("player_username");
                System.out.println(id);
                System.out.println(user);
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
        String query = "SELECT MAX(high_score) AS highscore FROM players";
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
    public static Connection addUserScore(int current_score){
        String url = "jdbc:mysql://localhost:3306/moong";
        String username = "root";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String query_toGetHighScore = "SELECT MAX(high_score) AS high_score FROM players WHERE player_username = ?";
            PreparedStatement updateStatement01 = conn.prepareStatement(query_toGetHighScore);
            updateStatement01.setString(1, user);
            ResultSet resultSet01 = updateStatement01.executeQuery();
            if (resultSet01.next()) {
                personal_best = resultSet01.getInt("high_score");
                System.out.println(personal_best);
            }
            SharedData.personal_best = personal_best;

            String updateScoreSql = "UPDATE players SET high_score = ? WHERE player_username = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateScoreSql);
            updateStatement.setInt(1, current_score);
            updateStatement.setString(2, user);

            if (current_score > personal_best) {
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    SharedData.personal_best = current_score;
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

}





