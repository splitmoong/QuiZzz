import javax.swing.*;

public class QuizApp { // pull check
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.setVisible(true);
        });

    }
}