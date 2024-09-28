
package exercise3;

public class Main {
        public static void main(String[] args) {
            // Method 1:
            String carNameInfo = Formula1Cars.getF1CarsInfo("- Mercedes");
            System.out.println(carNameInfo + "\n");


            // Method 2:
            String[] carDriversInfo = {"Charles Leclerc", "George Russell"};
            String carInfo2 = Formula1Cars.getF1CarsInfo("- Mercedes", carDriversInfo);
            System.out.println(carInfo2);


            // Method 3:
            String carDetails = Formula1Cars.getF1CarsInfo("- Ferrari", "SF21", "hybrid V6\n");
            System.out.println(carDetails);
        }
    }

