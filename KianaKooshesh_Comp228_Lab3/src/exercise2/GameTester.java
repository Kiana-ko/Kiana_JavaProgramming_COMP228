package exercise2;

public abstract class GameTester {
    // Responsible for holding the name of the game tester:
    protected String gameTesterName;
    // Responsible for indicating whether the tester is full-time or part-time:
    protected boolean isGameTesterFullTime;

    // Responsible for initializing name and employment status of the game tester (weather its full-time or part-time):
    public GameTester(String testerName, boolean isFullTimeTester) {
        this.gameTesterName = testerName;
        this.isGameTesterFullTime = isFullTimeTester;
    }

    // Responsible for returning the name of the tester:
    public String getGameTesterName() {
        return gameTesterName;
    }

    // Responsible for checking if the Game Tester is a full-time tester or not:
    public boolean isFullTimeTester() {
        return isGameTesterFullTime;
    }

    // Responsible for determining the salary of the game tester (which would be  implemented by subclasses later):
    public abstract double determineGameTesterSalary();
}
