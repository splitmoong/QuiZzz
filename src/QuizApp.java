import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//hello from me
class QuizApp extends JFrame implements ActionListener {
    private int score = 0;
    private int questionIndex = 0;

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest mammal in the world?"
    };

    private String[][] choices = {
            {"A) London", "B) Berlin", "C) Paris", "D) Madrid"},
            {"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"},
            {"A) Elephant", "B) Giraffe", "C) Blue Whale", "D) Polar Bear"}
    };

    private String[] correctAnswers = {"C", "B", "C"};

    private JLabel questionLabel;
    private JLabel scoreLabel;
    private JRadioButton[] answerButtons;
    private ButtonGroup group;  // Declare ButtonGroup instance variable
    private JButton submitButton;
    private JButton retakeButton;
    private JButton quitButton;

    public QuizApp() {
        setupQuizUI();
    }

    private void setupQuizUI() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("Quiz App");

        scoreLabel = new JLabel("Your Current Score: 0", SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.NORTH);

        questionLabel = new JLabel("", SwingConstants.CENTER);

        answerButtons = new JRadioButton[4];
        group = new ButtonGroup();  // Initialize ButtonGroup
        JPanel answerPanel = new JPanel(new GridLayout(4, 1));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            group.add(answerButtons[i]);  // Add each radio button to the group
            answerPanel.add(answerButtons[i]);
        }

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(questionLabel);
        centerPanel.add(answerPanel);
        add(centerPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    private void setupFinalUI() {
        getContentPane().removeAll();

        JLabel finalScoreLabel = new JLabel("Your Final Score: " + score + "/" + questions.length, SwingConstants.CENTER);
        add(finalScoreLabel, BorderLayout.CENTER);

        retakeButton = new JButton("Retake");
        retakeButton.addActionListener(e -> {
            score = 0;
            questionIndex = 0;
            getContentPane().removeAll();
            setupQuizUI();
            revalidate();
            repaint();
        });

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(retakeButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void loadQuestion() {
        group.clearSelection();  // Clear the selected radio button

        if (questionIndex < questions.length) {
            questionLabel.setText(questions[questionIndex]);
            for (int i = 0; i < 4; i++) {
                answerButtons[i].setText(choices[questionIndex][i]);
            }
            scoreLabel.setText("Your Current Score: " + score);
        } else {
            SwingUtilities.invokeLater(this::setupFinalUI);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userAnswer = "";
        for (int i = 0; i < 4; i++) {
            if (answerButtons[i].isSelected()) {
                userAnswer = answerButtons[i].getText().substring(0, 1);
            }
        }

        if (userAnswer.equals(correctAnswers[questionIndex])) {
            score++;
        }

        questionIndex++;
        loadQuestion();
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
