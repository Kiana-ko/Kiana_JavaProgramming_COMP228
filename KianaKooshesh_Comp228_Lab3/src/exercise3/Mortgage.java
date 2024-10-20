
package exercise3;

public  abstract class Mortgage implements  MortgageConstants {
    protected int mortgageID;              // Unique identifier for the mortgage
    protected String borrowerName;         // Name of the borrower (customer)
    protected double loanAmount;           // Total loan amount (mortgage)
    protected double annualInterestRate;   // Annual interest rate for the mortgage
    protected int mortgageDurationTerm;    // Term of the mortgage in years (1, 3, or 5 years)

    // Responsible for initializing the mortgage:
    public Mortgage(int mortgageID, String borrowerName, double loanAmount, double annualInterestRate, int term) {
        this.mortgageID = mortgageID;
        this.borrowerName = borrowerName;


        // Responsible for ensuring that the loan amount doesn't exceed the maximum limit:
        if (loanAmount > MAX_MORTGAGE_AMOUNT) {
            this.loanAmount = MAX_MORTGAGE_AMOUNT;
        } else {
            this.loanAmount = loanAmount;
        }

        this.annualInterestRate = annualInterestRate;



        // Responsible for setting the mortgage term; default to short-term (1 year) if invalid
        if (term == SHORT_TERM_MORTGAGE || term == MEDIUM_TERM_MORTGAGE || term == LONG_TERM_MORTGAGE) {
            this.mortgageDurationTerm = term;
        } else {
            this.mortgageDurationTerm = SHORT_TERM_MORTGAGE;
        }
    }

    // Responsible for displaying mortgage info:
    public abstract void getMortgageInfo();

    // Responsible for calculating the total amount owed (principal + interest):
    public double computeTotalAmountOwed() {
        return loanAmount + (loanAmount * annualInterestRate * mortgageDurationTerm); // interest over the term
    }
}
