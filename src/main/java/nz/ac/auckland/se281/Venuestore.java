package nz.ac.auckland.se281;

public class Venuestore {

  public String venueName;
  public String venueCode;
  public int capacity;
  public int hireFee;

  public Venuestore(String venueName, String venueCode, int capacity, int hireFee){
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }
}
