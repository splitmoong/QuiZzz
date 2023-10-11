public class QuizLogic {
    private int score = 0;
    private int questionIndex = 0;

    private String[] questions_computer_science = {
            "What is the time complexity of a binary search algorithm when searching for an element in a sorted array of size n?",
            "In object-oriented programming, which principle refers to the ability of different classes to be treated as instances of the same class through inheritance?",
            "Which programming paradigm is characterized by the use of mathematical functions and avoids changing state and mutable data?",
            "What is the term for a type of memory that retains data even when the power is turned off, commonly used in BIOS settings?",
            "What component is primarily responsible for rendering graphics in modern gaming PCs and workstations?",
            "In the context of computer networking, what does DHCP stand for?",
            "Which of the following is a popular version control system commonly used for tracking changes in source code during software development?",
            "Which type of software is designed to simulate a physical machine or environment for testing and development purposes?",
            "In the context of programming languages, what does the acronym \"API\" stand for?",
            "What is the primary function of a BIOS (Basic Input/Output System) in a computer?"
    };

    private String[][] choices_computer_science = {
            {"A) O(1)", "B) O(log n)", "C) O(n)", "D) O(n log n)"},
            {"A) Encapsulation", "B) Polymorphism", "C) Abstraction", "D) Inheritance"},
            {"A) Object Oriented Programming", "B) Procedural Oriented Programming", "C) Functional Programming", "D) Imperative Programming"},
            {"A) RAM", "B) ROM", "C) Cache", "D) Virtual Memory"},
            {"A) CPU", "B) GPU", "C) RAM", "D) Hard Drive"},
            {"A) Dynamic Host Control Protocol", "B) Data Handling and Control Protocol", "C) Dynamic Host Configuration Protocol", "D) Digital Hardware and Communication Protocol"},
            {"A) SVN (Subversion)", "B) Git", "C) Mercurial", "D) CVS (Concurrent Versions System"},
            {"A) Virtualization Software", "B) Antivirus Software", "C) Office Suite Software", "D) Database Management Software"},
            {"A) Application Programming Interface", "B) Application Programming Instruction", "C) Application Process Indicator", "D) Advanced Program Integration"},
            {"A) To control the display resolution", "B) To manage system resources", "C) To initialize hardware components and start the boot process", "D) To facilitate software installation"}
    };

    private String[] correctAnswers = {"B", "D", "C", "B", "B", "C", "B", "A", "A", "C"};

    public String getCurrentQuestion() {
        return questions_computer_science[questionIndex];
    }

    public String[] getCurrentChoices() {
        return choices_computer_science[questionIndex];
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions_computer_science.length;
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
        return questionIndex < questions_computer_science.length;
    }
}

