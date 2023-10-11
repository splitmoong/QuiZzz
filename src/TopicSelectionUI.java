import javax.swing.*;
import java.awt.*;

public class TopicSelectionUI extends JPanel {

    public TopicSelectionUI(JFrame parentFrame, QuizLogic quizLogic, CardLayout cardLayout) {
        // Use GridBagLayout for main layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Heading label with increased font size
        JLabel headingLabel = new JLabel("Choose your Topic");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Adjust font name and size as desired

        // Position for the heading label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // Some space below the heading
        add(headingLabel, gbc);

        // Create panel for buttons with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Font and size settings for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 18); // Set the desired font and size
        Dimension buttonSize = new Dimension(200, 50); // Adjust width and height as required

        JButton generalKnowledgeButton = createButton("General Knowledge", buttonFont, buttonSize);
        JButton computerScienceButton = createButton("Computer Science", buttonFont, buttonSize);
        JButton gamingButton = createButton("Gaming", buttonFont, buttonSize);

        generalKnowledgeButton.addActionListener(e -> startQuiz("General Knowledge", parentFrame, quizLogic, cardLayout));
        computerScienceButton.addActionListener(e -> startQuiz("Computer Science", parentFrame, quizLogic, cardLayout));
        gamingButton.addActionListener(e -> startQuiz("Gaming", parentFrame, quizLogic, cardLayout));

        // Add buttons to the buttonPanel
        buttonPanel.add(generalKnowledgeButton);
        buttonPanel.add(computerScienceButton);
        buttonPanel.add(gamingButton);

        // Position for the button panel
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(buttonPanel, gbc);

        this.requestFocusInWindow();
    }

    private JButton createButton(String text, Font font, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(size);
        button.setHorizontalAlignment(SwingConstants.CENTER); // This ensures the text is centered
        return button;
    }

    private void startQuiz(String topic, JFrame parentFrame, QuizLogic quizLogic, CardLayout cardLayout) {
        quizLogic.setTopic(topic);
        parentFrame.getContentPane().removeAll();
        QuizUI quizUI = new QuizUI(quizLogic, parentFrame, cardLayout);
        parentFrame.add(quizUI, "QuizUI");
        cardLayout.show(parentFrame.getContentPane(), "QuizUI");
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
