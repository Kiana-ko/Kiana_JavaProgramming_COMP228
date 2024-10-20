
package exercise3;

import  javax.swing.JOptionPane;

public class ProcessMortgage {
    public static void main(String[] args) {
        // Responsible for prompting the user for the current prime interest rate
        String primeRateInput = JOptionPane.showInputDialog("Enter the current prime interest rate (i.e... 0.2 for 2%):");
        double primeRate = Double.parseDouble(primeRateInput);

        // Responsible for creating an array to store 3 mortgage records
        Mortgage[] mortgageRecords = new Mortgage[3];

        // Responsible for looping through to gather mortgage information
        for (int i = 0; i < mortgageRecords.length; i++) {
            String mortgageType = "";
            boolean validMortgageType = false;

            // Responsible for ensuring the mortgage type is valid:
            while (!validMortgageType) {
                mortgageType = JOptionPane.showInputDialog("Enter mortgage type (business/personal):").toLowerCase();

                if (mortgageType.equalsIgnoreCase("business") || mortgageType.equalsIgnoreCase("personal")) {
                    validMortgageType = true;
                } else {
                    JOptionPane.showMessageDialog(null, "" +
                            "Oops.. Invalid mortgage type.. Please enter 'business' or 'personal' ! ");
                }
            }

            // Responsible for gathering mortgage ID, customer name, amount, and term:
            String mortgageIDInput = JOptionPane.showInputDialog("Enter mortgage number (integer only):");
            int mortgageID = Integer.parseInt(mortgageIDInput);

            String customerName = JOptionPane.showInputDialog("Pls enter customer name:");

            String amountInput = JOptionPane.showInputDialog("Pls enter mortgage amount:");
            double amount = Double.parseDouble(amountInput);

            String termInput = JOptionPane.showInputDialog("Pls enter mortgage term (1, 3, or 5 years):");
            int term = Integer.parseInt(termInput);

            // Responsible for creating and storing the appropriate mortgage record based on input type:
            if (mortgageType.equals("business")) {
                mortgageRecords[i] = new BusinessMortgage(mortgageID, customerName, amount, primeRate, term);
            } else {
                mortgageRecords[i] = new PersonalMortgage(mortgageID, customerName, amount, primeRate, term);
            }
        }

        // Responsible for building the output with mortgage details:
        StringBuilder mortgageSummary = new StringBuilder("Mortgage Information:\n");
        for (Mortgage mortgage : mortgageRecords) {
            mortgageSummary.append("==================\n");

            // Responsible for checking if the mortgage object is of type BusinessMortgage or PersonalMortgage
            // using `instanceof`:
            mortgageSummary.append(". Mortgage Type: ").append(mortgage instanceof BusinessMortgage ? "Business" : "Personal").append("\n");

            mortgageSummary.append(". Borrower/Customer Name: ").append(mortgage.borrowerName).append("\n");
            mortgageSummary.append(". Mortgage ID: ").append(mortgage.mortgageID).append("\n");
            mortgageSummary.append(". Loan Amount: $").append(mortgage.loanAmount).append("\n");

            // Responsible for formatting the annual interest rate into percentage:
            mortgageSummary.append(". Annual Interest Rate (as percentage): ").append(String.format("%.2f", mortgage.annualInterestRate * 100)).append("%\n");

            mortgageSummary.append(". Mortgage Term: ").append(mortgage.mortgageDurationTerm).append(" years\n");

            // Responsible for calculating and displaying the total amount owed using the computeTotalAmountOwed method:
            mortgageSummary.append(". Total Amount Owed: $").append(String.format("%.2f", mortgage.computeTotalAmountOwed())).append("\n");
        }

        // Responsible for displaying the final mortgage information
        JOptionPane.showMessageDialog(null, mortgageSummary.toString());
    }
}