
package exercise2;

import javax.swing.JOptionPane;
import java.util.Random;

public class Lotto {
    // For storing the lotto numbers:
    private final Integer[] lottoNumbers;

    // Responsible for generating random numbers:
    private final Random randomLottoNumberGenerator;


    // For initializing the array and the random number generator:
    public Lotto() {
        this.lottoNumbers = new Integer[3];
        this.randomLottoNumberGenerator = new Random();
        generateRandomLottoNumbers();
    }


    // Responsible for generating a random lotto numbers (1 to 9):
    private void generateRandomLottoNumbers() {
        for (int i = 0; i < lottoNumbers.length; i++) {
            lottoNumbers[i] = randomLottoNumberGenerator.nextInt(9) + 1; // For generating a number from 1 to 9
        }
    }


    // Responsible for returning  the lotto numbers:
    public Integer[] getLottoNumbers() {
        return  lottoNumbers;
    }


    // Responsible for calculating the sum of the lotto numbers:
    public int getSum() {
        int sumOfLottoNumbers = 0;
        for (int lottoNumber : lottoNumbers) {
            sumOfLottoNumbers += lottoNumber;
        }

        return sumOfLottoNumbers;
    }


    // Responsible for starting and managing the lotto game:
    public static void playLottoGame() {
        // Responsible for getting the user input:
        Integer userNumberInput = getPlayerInput();

        // Would exit the game if input is invalid:
        if (userNumberInput == null) return;

        boolean userWon = playRounds(userNumberInput);
        if (!userWon) {
            JOptionPane.showMessageDialog(null, "Computer wins! Try again..", "Computer Win!", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    // Responsible for getting the user input and validate it:
    private static Integer getPlayerInput() {
        String userInput = JOptionPane.showInputDialog(null, "Please enter a number between 3 and 27", "Lotto", JOptionPane.QUESTION_MESSAGE);
        if (userInput == null) return null; // Would exit if user cancels input

        try {
            Integer userNumber = Integer.parseInt(userInput);
            if (userNumber < 3 || userNumber > 27) {
                showError("Invalid input .. choose a number between 3 and 27!");
                return null;
            }
            return userNumber;
        } catch (NumberFormatException e) {
            showError("OOPS, invalid input again... Please enter a valid number!");
            return null;
        }
    }

    // For displaying an error message:
    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // For user to be able to play the game to 5 rounds:
    private static boolean playRounds(Integer userNumber) {
        for (int i = 1; i <= 5; i++) {
            Lotto lottoGame = new Lotto();

            // Responsible for getting the sum of the lotto numbers:
            int lottoSum = lottoGame.getSum();

            // Prepare and display the current round information
            displayCurrentRoundInfo(lottoGame.getLottoNumbers(), lottoSum, i);

            if (userNumber.equals(lottoSum)) {
                JOptionPane.showMessageDialog(null, "Congrats.. u won the Lotto game in " + i + " rolls!", "Winning message!", JOptionPane.INFORMATION_MESSAGE);
                return true; // Would end the game if the user wins!
            }
        }
        // Would return false if the user doesn't win in 5 rolls:
        return false;
    }

    // Responsible display information for the current round:
    private static void displayCurrentRoundInfo(Integer[] lottoNumbers, int lottoSum, int round) {
        String lottoNumbersString = String.format("Lotto numbers: %d, %d, %d\nSum: %d", lottoNumbers[0], lottoNumbers[1], lottoNumbers[2], lottoSum);
        JOptionPane.showMessageDialog(null, lottoNumbersString, "Lotto Roll " + round, JOptionPane.INFORMATION_MESSAGE);
    }
}