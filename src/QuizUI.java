import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizUI extends JFrame implements ActionListener {
    private QuizLogic quizLogic;
    private JLabel questionLabel;
    private JLabel scoreLabel;
    private JRadioButton[] answerButtons;
    private ButtonGroup group;
    private JButton submitButton;
    private JButton retakeButton;
    private JButton quitButton;
    private JPanel panel1;

    public QuizUI(QuizLogic quizLogic) {
        this.quizLogic = quizLogic;
        setupQuizUI();
        ImageIcon image = new ImageIcon("design/quizzzicon.png");
        setIconImage(image.getImage());
    }

    private void setupQuizUI() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setTitle("Quiz App");

        scoreLabel = new JLabel("Your Current Score: 0", SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.NORTH);
        scoreLabel.setFont(new Font("Ariel", Font.PLAIN, 15));


        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Ariel", Font.PLAIN, 16));
        questionLabel.setBorder(new EmptyBorder(0, 20, 0, 0));

        answerButtons = new JRadioButton[4];
        group = new ButtonGroup();
        JPanel answerPanel = new JPanel(new GridLayout(4, 1));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            group.add(answerButtons[i]);
            answerPanel.add(answerButtons[i]);

            // Set the preferred size for the radio buttons
            Dimension largerSize = new Dimension(500, 40); // Adjust the size as needed
            answerButtons[i].setPreferredSize(largerSize);
        }

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(questionLabel);
        centerPanel.add(answerPanel);
        add(centerPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);
        submitButton.setFont(new Font("Ariel", Font.TRUETYPE_FONT, 18));
        submitButton.setBackground(Color.PINK);
        this.add(submitButton, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    private void setupFinalUI() {

        getContentPane().removeAll();
        int current_score = quizLogic.getScore();
        DatabaseConnector.addUserScore(current_score);
        int high_score = DatabaseConnector.getHighScore();

        JTextField highScoreField = new JTextField(10); // A text field to display high score
        highScoreField.setEditable(false); // Make it read-only
        highScoreField.setHorizontalAlignment(JTextField.CENTER);

        highScoreField.setText("Your Personal Best: " + SharedData.personal_best + "        High Score is: " + high_score);
        highScoreField.setFont(new Font("Ariel", Font.PLAIN,14)); // Adjust the font size (20 in this example)


        System.out.println(current_score);
        System.out.println(high_score);

        add(highScoreField, BorderLayout.NORTH);

        JLabel finalScoreLabel = new JLabel("Your Final Score: " + quizLogic.getScore() + "/" + quizLogic.getTotalQuestions(), SwingConstants.CENTER);
        finalScoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10)); // Adjust the values for desired spacing
        add(finalScoreLabel, BorderLayout.CENTER);
        finalScoreLabel.setFont(new Font("Ariel", Font.PLAIN, 24)); // Adjust the font size (20 in this example)


        retakeButton = new JButton("Retake");
        retakeButton.addActionListener(e -> {
            quizLogic = new QuizLogic(); // Reset the logic for a new quiz
            getContentPane().removeAll();
            setupQuizUI();
            revalidate();
            repaint();
        });
        retakeButton.setFont(new Font("Ariel", Font.BOLD, 16));
        retakeButton.setBackground(Color.PINK);
        retakeButton.setFont(new Font("Ariel", Font.TRUETYPE_FONT, 18)); // Adjust the font style and size
        this.add(retakeButton, BorderLayout.SOUTH);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setFont(new Font("Ariel", Font.TRUETYPE_FONT, 18));
        quitButton.setBackground(Color.PINK);
        this.add(quitButton, BorderLayout.SOUTH);


        JPanel buttonPanel =  new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Make the "Retake" button expand to fill the entire space
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;

        buttonPanel.add(retakeButton, constraints);

        // Reset constraints for the "Quit" button
        constraints.weightx = 0.5;

        buttonPanel.add(quitButton, constraints);

        add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void loadQuestion() {
        group.clearSelection();

        if (quizLogic.hasMoreQuestions()) {
            questionLabel.setText("<html>" + quizLogic.getCurrentQuestion().toString() + "</html>");
            String[] currentChoices = quizLogic.getCurrentChoices();
            for (int i = 0; i < 4; i++) {
                answerButtons[i].setText(currentChoices[i]);
                answerButtons[i].setFont(new Font("Ariel", Font.PLAIN, 16));
            }
            scoreLabel.setText("Your Current Score: " + quizLogic.getScore());
        } else {
            SwingUtilities.invokeLater(this::setupFinalUI);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizUI(new QuizLogic()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userAnswer = "";
        for (int i = 0; i < 4; i++) {
            if (answerButtons[i].isSelected()) {
                userAnswer = answerButtons[i].getText().substring(0, 1);
            }
        }
        ImageIcon logo = new ImageIcon("quizzzicon.png");

        quizLogic.answerQuestion(userAnswer);
        loadQuestion();
    }
}




