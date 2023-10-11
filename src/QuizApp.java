public class QuizApp { // pull check
    public static void main(String[] args) {
        DatabaseConnector.getConnection();
        QuizLogic logic = new QuizLogic();
        new QuizUI(logic);
    }
}