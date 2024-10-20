
package exercise1;

// For the Health class to: extends Insurance, and to inherit its properties and methods:
public class Health extends Insurance {

    public Health() {
        // Responsible for setting the insurance type to "Health":
        super("Health");
    }


    // Responsible for overriding setInsuranceCost method to set the monthly cost for Health insurance:
    @Override
    public void setInsuranceCost(double healthInsuranceCost) {
        // Responsible for Setting the cost of the health insurance:
        this.monthlyInsuranceCost = healthInsuranceCost;

    }


    // Responsible for Displaying the  insurance type and monthly cost of that specific insurance which would be:
    // - either health insurance (the case for this class)
    // - or life insurance
    @Override
    public void displayInfo() {
        
        System.out.println("=== ==  Health Insurance Details: == ===");
        System.out.println(" . Insurance Type: " + getInsuranceType());
        System.out.println(". Monthly Cost of " + getInsuranceType() + " : $" + getMonthlyInsuranceCost());
    }
}
