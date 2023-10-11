import javax.swing.*;

public class QuizApp { // pull check
    public String enteredUsername;
    public String enteredPassword;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.setVisible(true);
        });

    }
}