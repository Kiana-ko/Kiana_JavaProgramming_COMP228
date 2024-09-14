package exercise1;


public class Singers {

    private int singerID;
    private String nameOfSinger;
    private String addressOfSinger;
    private String singerDateOfBirth;
    private int numberOfPublishedAlbums;


    public Singers() {
        this.singerID = 0;
        this.nameOfSinger = "";
        this.addressOfSinger = "";
        this.singerDateOfBirth = "";
        this.numberOfPublishedAlbums = 0;
    }

    public Singers(int singerID) {
        this.singerID = singerID;
        this.nameOfSinger = "";
        this.addressOfSinger = "";
        this.singerDateOfBirth = "";
        this.numberOfPublishedAlbums = 0;
    }

    public Singers(int singerID, String nameOfSinger) {
        this.singerID = singerID;
        this.nameOfSinger = nameOfSinger;
        this.addressOfSinger = "";
        this.singerDateOfBirth = "";
        this.numberOfPublishedAlbums = 0;
    }

    public Singers(int singerID, String nameOfSinger, String addressOfSinger) {
        this.singerID = singerID;
        this.nameOfSinger = nameOfSinger;
        this.addressOfSinger = addressOfSinger;
        this.singerDateOfBirth = "";
        this.numberOfPublishedAlbums = 0;
    }

    public Singers(int singerID, String nameOfSinger, String addressOfSinger, String singerDateOfBirth) {
        this.singerID = singerID;
        this.nameOfSinger = nameOfSinger;
        this.addressOfSinger = addressOfSinger;
        this.singerDateOfBirth = singerDateOfBirth;
        this.numberOfPublishedAlbums = 0;
    }

    public Singers(int singerID, String nameOfSinger, String addressOfSinger, String singerDateOfBirth, int numberOfPublishedAlbums) {
        this.singerID = singerID;
        this.nameOfSinger = nameOfSinger;
        this.addressOfSinger = addressOfSinger;
        this.singerDateOfBirth = singerDateOfBirth;
        this.numberOfPublishedAlbums = numberOfPublishedAlbums;
    }



    public int getSingerID() {
        return singerID;
    }

    public void setSingerID(int singerID) {
        this.singerID = singerID;
    }

    public String getNameOfSinger() {
        return nameOfSinger;
    }

    public void setNameOfSinger(String nameOfSinger) {
        this.nameOfSinger = nameOfSinger;
    }

    public String getAddressOfSinger() {
        return addressOfSinger;
    }

    public void setAddressOfSinger(String addressOfSinger) {
        this.addressOfSinger = addressOfSinger;
    }

    public String getSingerDateOfBirth() {
        return singerDateOfBirth;
    }

    public void setSingerDateOfBirth(String singerDateOfBirth) {
        this.singerDateOfBirth = singerDateOfBirth;
    }

    public int getNumberOfPublishedAlbums() {
        return numberOfPublishedAlbums;
    }

    public void setNumberOfPublishedAlbums(int numberOfPublishedAlbums) {
        this.numberOfPublishedAlbums = numberOfPublishedAlbums;
    }



    public void setSingersInformation(int singerID, String nameOfSinger, String addressOfSinger, String singerDateOfBirth, int numberOfPublishedAlbums) {
        this.singerID = singerID;
        this.nameOfSinger = nameOfSinger;
        this.addressOfSinger = addressOfSinger;
        this.singerDateOfBirth = singerDateOfBirth;
        this.numberOfPublishedAlbums = numberOfPublishedAlbums;
    }

    public void displaySingersInformation() {
        System.out.println("ID: " + singerID);
        System.out.println("Name: " + nameOfSinger);
        System.out.println("Address: " + addressOfSinger);
        System.out.println("Date of Birth: " + singerDateOfBirth);
        System.out.println("Number of Published Albums: " + numberOfPublishedAlbums);
    }
}



