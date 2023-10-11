public class QuizLogic {
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

    public String getCurrentQuestion() {
        return questions[questionIndex];
    }

    public String[] getCurrentChoices() {
        return choices[questionIndex];
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.length;
    }

    public boolean answerQuestion(String userAnswer) {
        boolean isCorrect = userAnswer.equals(correctAnswers[questionIndex]);
        if (isCorrect) {
            score++;
        }
        questionIndex++;
        return isCorrect;
    }

    public boolean hasMoreQuestions() {
        return questionIndex < questions.length;
    }
}

