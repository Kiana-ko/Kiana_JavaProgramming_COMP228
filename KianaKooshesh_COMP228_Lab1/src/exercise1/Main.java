package exercise1;

import exercise1.Singers;

public class Main {

    public static void main(String[] args) {

        Singers singer1 = new Singers();

        System.out.println("== Default Values of Singers Before Being Updated ==");
        singer1.displaySingersInformation();


        singer1.setSingerID(1);
        singer1.setNameOfSinger("Daemon Vex");
        singer1.setAddressOfSinger("229 Shepperd Ave");
        singer1.setSingerDateOfBirth("1994-02-04");
        singer1.setNumberOfPublishedAlbums(12);

        System.out.println("\n== Singers' Records With Updated values: ==");
        singer1.displaySingersInformation();

    }
}
