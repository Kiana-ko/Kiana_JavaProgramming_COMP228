
package exercise1;

import  javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // An array for storing various types of insurance policies:
        Insurance[] insurancePolicies = new Insurance[2];

        for (int i = 0; i < insurancePolicies.length; i++) {
            // For prompting the user for an insurance type :
            String typeOfInsurance = JOptionPane.showInputDialog(null,
                    "Pls enter the insurance type (Health or Life):");

            // For prompting the user for the monthly insurance cost :
            double cost = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Pls enter the monthly cost of the chosen insurance type:"));


            // Responsible for creating an appropriate insurance policy (Health or Life) based on user input:
            if (typeOfInsurance.toLowerCase().equals("health")) {
                insurancePolicies[i] = new Health();
            } else if (typeOfInsurance.toLowerCase().equals("life")) {
                insurancePolicies[i] = new Life();
            }

            // For setting the monthly cost of the insurance:
            insurancePolicies[i].setInsuranceCost(cost);

        }


        /* Responsible for providing the user with a summary of insurance policies by iterating through each policy
        and extracting details about each one of them: */
        String insuranceDetails = "Insurance Details:\n";
        for (Insurance insurance : insurancePolicies) {
            insuranceDetails += "==================\n";
            insuranceDetails += "Insurance Type: " + insurance.getInsuranceType() + "\n";
            insuranceDetails += "Monthly Cost of " + insurance.getInsuranceType() + ": $" +
                    insurance.getMonthlyInsuranceCost() + "\n";
        }

        // Responsible for displaying the insurance details in a dialog box :
        JOptionPane.showMessageDialog(null, insuranceDetails);
    }
}

