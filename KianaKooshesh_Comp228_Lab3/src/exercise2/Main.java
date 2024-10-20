package exercise2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Responsible for capturing the user input:
        Scanner scanner = new Scanner(System.in);


        // Responsible for prompting the user to enter the tester's name:
        System.out.print("Enter the game tester's name: ");
        String nameOfTheTester = scanner.nextLine();

        // Responsible for prompting the user to choose either full-time or part-time tester option:
        System.out.print("Is the tester full-time? (yes/no): ");
        String isFullTimeTester = scanner.nextLine();

        // Declaring a GameTester object (can be either full-time or part-time) :
        GameTester testerProfile;

        if (isFullTimeTester.equalsIgnoreCase("yes")) {
            // For creating a full-time game tester:
            testerProfile = new FullTimeGameTester(nameOfTheTester);
        } else {
            // Fpr prompting the user to enter the number of hours worked for part-time testers
            System.out.print("Enter the number of hours worked: ");
            int hoursWorked = scanner.nextInt();
            testerProfile = new PartTimeGameTester(nameOfTheTester, hoursWorked);
        }

        // Responsible for outputting the game tester's name and salary:
        System.out.println(". Game Tester's name is: " + testerProfile.getGameTesterName());
        System.out.println(". " + testerProfile.getGameTesterName() + "'s Salary: $" + testerProfile.determineGameTesterSalary());


        // Responsible for checking if the testerProfile is a full-time or part-time tester using `instanceof`
        // `instanceof` checks if the object `testerProfile` is an instance of the specified class
        // (FullTimeGameTester or PartTimeGameTester)
        if (testerProfile instanceof FullTimeGameTester) {
            System.out.println(testerProfile.getGameTesterName() + " is a full-time game tester!");
        } else if (testerProfile instanceof PartTimeGameTester) {
            System.out.println(testerProfile.getGameTesterName() + " is a part-time game tester!");
        }
    }
}

