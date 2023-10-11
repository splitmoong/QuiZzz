import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame implements ActionListener {

    private JLabel enter_username_prompt;
    private JTextField username;
    private JLabel enter_password_prompt;
    private JTextField password;
    private JButton new_user;
    private JButton existing_user;
    private JPanel panel1;

    public SplashScreen() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("Quiz App");

        enter_username_prompt = new JLabel("Enter Username:");
        username = new JTextField(20);

        enter_password_prompt = new JLabel("Enter Password:");
        password = new JTextField(20);

        new_user = new JButton("New User");
        new_user.addActionListener(this);

        existing_user = new JButton("Existing User");
        existing_user.addActionListener(this);

        JPanel splashPanel = new JPanel(new GridLayout(4, 2));
        splashPanel.add(enter_username_prompt);
        splashPanel.add(username);
        splashPanel.add(enter_password_prompt);
        splashPanel.add(password);
        splashPanel.add(new_user);
        splashPanel.add(existing_user);

        add(splashPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == new_user) {
            String enteredUsername = username.getText();
            String enteredPassword = password.getText();
            QuizLogic logic = new QuizLogic();
            new QuizUI(logic);
            DatabaseConnector.getConnection(enteredUsername, enteredPassword);

        } else if (e.getSource() == existing_user) {
            String enteredUsername = username.getText();
            String enteredPassword = password.getText();
            DatabaseConnector.checkUserValid(enteredUsername, enteredPassword);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.setVisible(true);
        });
    }

}



