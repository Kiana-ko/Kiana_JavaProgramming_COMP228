
package exercise2;

public class FullTimeGameTester extends GameTester {
    //  Responsible for initializing the full-time game tester
    public FullTimeGameTester(String fullTimeTesterName) {
        super(fullTimeTesterName, true);
    }

    // Responsible for determining the salary of a full-time game testers ( with a fixed salary of $3000) :
    @Override
    public double determineGameTesterSalary() {  // Method name matches the abstract method in GameTester
        return 3000.00;
    }
}
