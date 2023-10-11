import javax.swing.*;
import java.awt.CardLayout;
public class QuizApp {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("KBC");
        mainFrame.setSize(800, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        mainFrame.setLayout(cardLayout);

        QuizLogic logic = new QuizLogic();
        TopicSelectionUI topicUI = new TopicSelectionUI(mainFrame, logic, cardLayout);

        mainFrame.add(topicUI, "topicUI");
        mainFrame.setVisible(true);
    }
}
