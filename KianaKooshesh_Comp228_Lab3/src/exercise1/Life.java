
package exercise1;

// For the Life class to extend Insurance, and to inherit its properties and methods:
public class Life extends Insurance {

    public Life() {
        // Responsible for setting the type of insurance to life insurance :
        super("Life Insurance");
    }


    // Responsible for overriding setInsuranceCost method to set the monthly cost for Health insurance:
    @Override
    public void setInsuranceCost(double cost) {
        // Responsible for Setting the cost of the life insurance:
        this.monthlyInsuranceCost = cost;
    }


    // Responsible for Displaying the  insurance type and monthly cost of that specific insurance which would be:
    // - either life insurance  (the case for this class)
    // - or health insurance
    @Override
    public void displayInfo() {
        System.out.println("=== ==  Life Insurance Details:  == ===");
        System.out.println(". Insurance Type: " + getInsuranceType());
        System.out.println(". Monthly Cost of " + getInsuranceType() + " : $" + getMonthlyInsuranceCost());
    }
}
