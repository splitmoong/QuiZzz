import javax.swing.*;
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

    public QuizUI(QuizLogic quizLogic) {
        this.quizLogic = quizLogic;
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
        group = new ButtonGroup();
        JPanel answerPanel = new JPanel(new GridLayout(4, 1));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            group.add(answerButtons[i]);
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

        JLabel finalScoreLabel = new JLabel("Your Final Score: " + quizLogic.getScore() + "/" + quizLogic.getTotalQuestions(), SwingConstants.CENTER);
        add(finalScoreLabel, BorderLayout.CENTER);

        retakeButton = new JButton("Retake");
        retakeButton.addActionListener(e -> {
            quizLogic = new QuizLogic(); // Reset the logic for a new quiz
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
        group.clearSelection();

        if (quizLogic.hasMoreQuestions()) {
            questionLabel.setText(quizLogic.getCurrentQuestion());
            String[] currentChoices = quizLogic.getCurrentChoices();
            for (int i = 0; i < 4; i++) {
                answerButtons[i].setText(currentChoices[i]);
            }
            scoreLabel.setText("Your Current Score: " + quizLogic.getScore());
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

        quizLogic.answerQuestion(userAnswer);
        loadQuestion();
    }
}
