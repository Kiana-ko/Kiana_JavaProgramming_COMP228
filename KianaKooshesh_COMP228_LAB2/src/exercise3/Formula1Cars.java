
package exercise3;

public class Formula1Cars {
    //  Method 1: Responsible for displaying Information about a single team:
    public static String getF1CarsInfo(String team) {
        return team + " has a strong new car for the new ferrari season!";
    }


    //  Method 2: Responsible for displaying info about teams and their drivers:
    public static String getF1CarsInfo(String team, String[] drivers) {
        StringBuilder info = new StringBuilder(team + " has the following drivers:\n");

        for (String driver : drivers) {
            info.append(" • ").append(driver).append("\n");
        }
        return info.toString();
    }


    //  Method 3: Responsible for displaying info about a team's car and its engine type:
    public static String getF1CarsInfo(String team, String carModel, String engineType) {
        return team + " uses the " + carModel + " model with a " + engineType + " engine.";
    }
}
