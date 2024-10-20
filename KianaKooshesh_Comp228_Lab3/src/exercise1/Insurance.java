
package exercise1;


public abstract class Insurance {
    // Responsible for holding the insurance types (either Health or Life):
    protected String  insuranceTypes;

    // Responsible for storing the monthly insurance cost:
    protected double monthlyInsuranceCost;


    // Responsible for initializing the insurance type:
    public Insurance(String insuranceType) {
        this.insuranceTypes = insuranceType;
    }


    // Responsible for returning the insurance type:
    public String getInsuranceType() {
        return insuranceTypes;
    }

    // Responsible for setting the insurance cost (to be implemented by subclasses)
    public double getMonthlyInsuranceCost() {
        return monthlyInsuranceCost;
    }



    // Responsible for setting the insurance cost (which would be implemented by subclasses):
    public abstract void setInsuranceCost(double cost);

    // Responsible for displaying insurance information (which would be determined/implemented by subclasses):
    public abstract void displayInfo();
}
