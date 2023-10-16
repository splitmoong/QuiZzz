import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setTitle("QuiZzz");
        ImageIcon image = new ImageIcon("design/quizzzicon.png");
        setIconImage(image.getImage());

        enter_username_prompt = new JLabel("Enter Username:");
        username = new JTextField(20);
        enter_username_prompt.setFont(new Font("Ariel", Font.PLAIN, 16));
        enter_username_prompt.setBorder(new EmptyBorder(0, 10, 0, 0));



        enter_password_prompt = new JLabel("Enter Password:");
        password = new JTextField(20);
        enter_password_prompt.setFont(new Font("Ariel", Font.PLAIN, 16));
        enter_password_prompt.setBorder(new EmptyBorder(0, 10, 0, 0));



        new_user = new JButton("New User");
        new_user.addActionListener(this);
        new_user.setFont(new Font("Ariel", Font.TRUETYPE_FONT, 18));
        new_user.setBackground(Color.PINK);


        existing_user = new JButton("Existing User");
        existing_user.addActionListener(this);
        existing_user.setFont(new Font("Ariel", Font.TRUETYPE_FONT, 18));
        existing_user.setBackground(Color.PINK);

        username.setFont(new Font("Ariel", Font.PLAIN, 17));
        username.setBorder(new EmptyBorder(0, 10, 0, 0));
        password.setFont(new Font("Ariel", Font.PLAIN, 17));
        password.setBorder(new EmptyBorder(0, 10, 0, 0));


        JPanel splashPanel = new JPanel(new GridLayout(4, 2));
        splashPanel.add(enter_username_prompt);
        splashPanel.add(username);
        splashPanel.add(enter_password_prompt);
        splashPanel.add(password);

        add(splashPanel);

        JPanel buttonPanel =  new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Make the "Retake" button expand to fill the entire space
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;

        buttonPanel.add(new_user, constraints);

        // Reset constraints for the "Quit" button
        constraints.weightx = 0.5;

        buttonPanel.add(existing_user, constraints);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == new_user) {
            String enteredUsername = username.getText();
            String enteredPassword = password.getText();
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