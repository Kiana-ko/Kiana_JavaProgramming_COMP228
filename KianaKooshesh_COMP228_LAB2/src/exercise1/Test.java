
package exercise1;


import javax.swing.JOptionPane;
import java.util.Random;

public class Test {
    // An Array for storing the multiple-choice questions:
    private String[] questions = {
            "1. What is the keyword used to define a function in Python?\nA. define\nB. function\nC. def\nD. func",
            "2. Which of the following is a valid variable name in Python?\nA. 12name\nB. name_lName\nC. @name\nD. name-123",
            "3. How do you start a comment in Python?\nA. <!-- comment -->\nB. #\nC. /*  */ \nD. None of the above",
            "4. Which of the following is not a built-in data type in Python?\nA. int\nB. str\nC. list\nD. character",
            "5. What library is commonly used for data manipulation and analysis in Python?\nA. NumPy\nB. Scikit-learn\nC. Pandas\nD. Matplotlib"
    };


    // An array for storing  the correct answers for each question:
    private char[] correctAnswers = {'C', 'B', 'B', 'D', 'C'};


    // Responsible for counting incorrect and correct answers:
    private int correctQuestionsCount = 0;
    private int incorrectQuestionsCount = 0;


    // A Random object responsible for generating random congratulatory messages:
    private Random randomMessages = new Random();


    // Responsible for starting the test and interaction with the user
    public void inputAnswer() {
        // Responsible for iterating  through the test questions and retrieving user's answers:
        for (int i = 0; i < questions.length; i++) {
            String userAnswer = JOptionPane.showInputDialog(questionSimulator(i));
            checkAnswer(userAnswer.charAt(0), correctAnswers[i]);
        }

        // Responsible for Displaying the final results of the user
        displayTestResults();
    }

    /* Responsible for retrieving a question from the array based on the provided index by user
    and displaying the current question to the user : */

    private String questionSimulator(int index) {
        return questions[index];
    }

    //Responsible for checking the user's answer
    private void checkAnswer(char userAnswer, char correctAnswer) {
        if (Character.toUpperCase(userAnswer) == correctAnswer) {
            correctQuestionsCount++;
            JOptionPane.showMessageDialog(null, generateMessage(true));
        } else {
            incorrectQuestionsCount++;
            JOptionPane.showMessageDialog(null, "Wrong. The correct answer is: " + correctAnswer);
        }
    }


    // Responsible for generating a random message based on whether the answer was correct or incorrect:
    private String generateMessage(boolean isCorrect) {
        // Responsible for generating a random number from 0 to 3:
        int responseIndex = randomMessages.nextInt(4);


        String message;

        switch (responseIndex) {
            case 0:
                message = isCorrect ? "Awesome !" : "Common.. try again!";
                break;
            case 1:
                message = isCorrect ? "Good stuff!" : "Wrong...try once more!";
                break;
            case 2:
                message = isCorrect ? "Keep going!" : "Don't give up.. u can do it!";
                break;
            case 3:
                message = isCorrect ? "Great job!" : "Oops.. don't get disappointed!";
                break;
            default:
                message = "";
        }


        return message;
    }


    // Responsible for  displaying test taker's final results:
    private void displayTestResults() {
        int totalQuestions = questions.length;
        int percentage = (int) ((correctQuestionsCount / (double) totalQuestions) * 100);
        String resultMessage = String.format("You got %d correct and %d incorrect.\nPercentage: %d%%", correctQuestionsCount, incorrectQuestionsCount, percentage);
        JOptionPane.showMessageDialog(null, resultMessage);
    }
}
