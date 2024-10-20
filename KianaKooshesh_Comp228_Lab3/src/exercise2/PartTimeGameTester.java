package exercise2;

public class PartTimeGameTester extends GameTester{
    // Responsible for tracking the number of the hours game tester has worked:
    private int gameTesterWorkHours;

    // Responsible for initializing the part-time game tester and the hours they've worked:
    public PartTimeGameTester(String partTimeTesterName, int partTimHoursWorked) {
        super(partTimeTesterName, false);
        this.gameTesterWorkHours = partTimHoursWorked;
    }

    // Responsible for calculating  the salary for part-time game testers at a rate of $20 per hour:
    @Override
    public double determineGameTesterSalary() {
        return gameTesterWorkHours * 20.00;
    }
}
