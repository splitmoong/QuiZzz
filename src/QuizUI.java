import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizUI extends JPanel implements ActionListener {
    private JLabel questionLabel, scoreLabel;
    private JRadioButton[] answerButtons = new JRadioButton[4];
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JButton submitButton, retakeButton, quitButton;

    private String[] questions;
    private String[][] choices;
    private String[] correctAnswers;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private CardLayout cardLayout;


    public QuizUI(QuizLogic quizLogic, JFrame parentFrame, CardLayout cardLayout) {
        this.cardLayout = cardLayout;

        // First, set the questions, choices, and correctAnswers from quizLogic
        this.questions = quizLogic.getQuestions();
        this.choices = quizLogic.getChoices();
        this.correctAnswers = quizLogic.getAnswers();

        // Then, initialize the UI components
        initializeUIComponents(parentFrame, cardLayout);
    }


    public void initializeUIComponents(JFrame parentFrame, CardLayout cardLayout) {
        setLayout(new BorderLayout());

        scoreLabel = new JLabel("Score: 0", JLabel.CENTER); // Aligned to center
        add(scoreLabel, BorderLayout.NORTH);

        // Use a new JPanel to hold both the question and the choices vertically
        JPanel centerPanel = new JPanel(new BorderLayout());

        questionLabel = new JLabel(questions[currentQuestionIndex]);
        JPanel questionPanel = new JPanel();
        questionPanel.add(questionLabel);
        centerPanel.add(questionPanel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            buttonGroup.add(answerButtons[i]);
            answersPanel.add(answerButtons[i]);
        }
        updateChoices();
        centerPanel.add(answersPanel, BorderLayout.CENTER); // Adjusted to center

        add(centerPanel, BorderLayout.CENTER); // Adding centerPanel to the main layout

        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(this);

        retakeButton = new JButton("Retake Quiz");
        quitButton = new JButton("Quit");
        retakeButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.add(new TopicSelectionUI(parentFrame, new QuizLogic(), this.cardLayout), "TopicSelection");
            this.cardLayout.show(parentFrame.getContentPane(), "TopicSelection");
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        quitButton.addActionListener(e -> System.exit(0));

        JPanel southPanel = new JPanel();
        southPanel.add(submitButton);

        add(southPanel, BorderLayout.SOUTH);
    }


    private void updateChoices() {
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(choices[currentQuestionIndex][i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String userAnswer = "";
            for (int i = 0; i < 4; i++) {
                if (answerButtons[i].isSelected()) {
                    userAnswer = answerButtons[i].getText().substring(0, 1);
                }
            }

            if (userAnswer.equals(correctAnswers[currentQuestionIndex])) {
                score++;
                scoreLabel.setText("Score: " + score);
            }

            currentQuestionIndex++;
            buttonGroup.clearSelection();

            if (currentQuestionIndex < questions.length) {
                questionLabel.setText(questions[currentQuestionIndex]);
                updateChoices();
            } else {
                displayResults();
            }
        }
    }

    private void displayResults() {
        // Hide the scoreLabel from the top
        scoreLabel.setVisible(false);

        // Set the final score as text of questionLabel
        questionLabel.setText("Final Score: " + score);

        // Hide the radio buttons
        for (JRadioButton btn : answerButtons) {
            btn.setVisible(false);
        }

        // Hide the submit button
        submitButton.setVisible(false);

        // Create a new panel to hold centered components and set its layout to GridBagLayout
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add the final score label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(questionLabel, gbc);

        // Create a new panel for the buttons and add it below the score label
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(retakeButton);
        buttonPanel.add(quitButton);

        gbc.gridy = 1;
        centerPanel.add(buttonPanel, gbc);

        // Remove all components from the main panel and add the centerPanel
        this.removeAll();
        add(centerPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Quiz App");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        QuizLogic logic = new QuizLogic();
        frame.add(new TopicSelectionUI(frame, logic, cardLayout), "TopicSelection");

        frame.setVisible(true);
    }
}