public class QuizApp {
    public static void main(String[] args) {
        QuizLogic logic = new QuizLogic();
        new QuizUI(logic);
    }
}