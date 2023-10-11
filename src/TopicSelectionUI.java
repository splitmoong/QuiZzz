import javax.swing.*;
import java.awt.*;

public class TopicSelectionUI extends JPanel {

    public TopicSelectionUI(JFrame parentFrame, QuizLogic quizLogic, CardLayout cardLayout) {
        setLayout(new GridLayout(3, 1));

        JButton generalKnowledgeButton = new JButton("General Knowledge");
        JButton computerScienceButton = new JButton("Computer Science");
        JButton gamingButton = new JButton("Gaming");

        generalKnowledgeButton.addActionListener(e -> startQuiz("General Knowledge", parentFrame, quizLogic, cardLayout));
        computerScienceButton.addActionListener(e -> startQuiz("Computer Science", parentFrame, quizLogic, cardLayout));
        gamingButton.addActionListener(e -> startQuiz("Gaming", parentFrame, quizLogic, cardLayout));

        add(generalKnowledgeButton);
        add(computerScienceButton);
        add(gamingButton);
    }


    private void startQuiz(String topic, JFrame parentFrame, QuizLogic quizLogic, CardLayout cardLayout) {
        quizLogic.setTopic(topic);
        parentFrame.getContentPane().removeAll();
        QuizUI quizUI = new QuizUI(quizLogic, parentFrame, cardLayout);  // Added the cardLayout argument
        parentFrame.add(quizUI, "QuizUI");
        cardLayout.show(parentFrame.getContentPane(), "QuizUI");
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}