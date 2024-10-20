
package exercise3;


public class BusinessMortgage extends Mortgage {
    // Responsible for setting the interest rate to 1% over the current prime rate:
    public BusinessMortgage(int mortgageNumber, String customerName, double mortgageAmount, double currentPrimeRate, int term) {
        // For calling the constructor of the superclass (Mortgage) and adding  1% to the current prime rate:
        super(mortgageNumber, customerName, mortgageAmount, currentPrimeRate + 0.01, term);
    }


    // Responsible for displaying mortgage info for business mortgages:
    @Override
    public void getMortgageInfo() {
        System.out.println("=== === == Business Mortgage: === === == ");
        System.out.println(". Borrower/ customer Name: " + borrowerName);
        System.out.println(". Mortgage ID: " + mortgageID);
        System.out.println(". Loan Amount: $" + loanAmount);
        System.out.println(". Annual Interest Rate: " + (annualInterestRate * 100) + "%");
        System.out.println(". Term: " + mortgageDurationTerm + " years");
        System.out.println("=== === === === === === === === === === ===");
    }
}
