public class QuizLogic {
    private String[] questions;
    private String[][] choices;
    private String[] answers;

    public QuizLogic() {
        // Default to General Knowledge if no topic is provided
        setTopic("General Knowledge");
    }

    public void setTopic(String topic) {
        switch(topic) {
            case "General Knowledge":
                this.questions = QuestionBank.GENERAL_KNOWLEDGE_QUESTIONS;
                this.choices = QuestionBank.GENERAL_KNOWLEDGE_CHOICES;
                this.answers = QuestionBank.GENERAL_KNOWLEDGE_ANSWERS;
                break;
            case "Computer Science":
                this.questions = QuestionBank.COMPUTER_SCIENCE_QUESTIONS;
                this.choices = QuestionBank.COMPUTER_SCIENCE_CHOICES;
                this.answers = QuestionBank.COMPUTER_SCIENCE_ANSWERS;
                break;
            case "Gaming":
                this.questions = QuestionBank.GAMING_QUESTIONS;
                this.choices = QuestionBank.GAMING_CHOICES;
                this.answers = QuestionBank.GAMING_ANSWERS;
                break;
        }
    }

    public String[] getQuestions() {
        return this.questions;
    }

    public String[][] getChoices() {
        return this.choices;
    }

    public String[] getAnswers() {
        return this.answers;
    }
}
