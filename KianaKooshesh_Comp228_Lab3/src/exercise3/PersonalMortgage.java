
package exercise3;

public class PersonalMortgage extends Mortgage {
    public PersonalMortgage(int mortgageNumber, String customerName, double mortgageAmount, double currentPrimeRate, int term) {
        // Responsible for Adding 0.02% to the current prime rate:
        super(mortgageNumber, customerName, mortgageAmount, currentPrimeRate + 0.02, term);
    }

    // Responsible for displaying mortgage info for personal mortgages:
    @Override
    public void getMortgageInfo() {
        System.out.println("=== === == Personal Mortgage: == === ===");
        System.out.println(". Borrower Name: " + borrowerName);
        System.out.println(". Mortgage ID: " + mortgageID);
        System.out.println(". Loan Amount: $" + loanAmount);
        System.out.println(". Annual Interest Rate: " + (annualInterestRate * 100) + "%");
        System.out.println(". Term: " + mortgageDurationTerm + " years");
        System.out.println("=== === === === === === === === === === ===");
    }

}
