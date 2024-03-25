package nz.ac.auckland.se281;

import java.time.LocalDate;

public class VenueStore {

  // initializing variables required in the entire class
  protected String venueName;
  protected String venueCode;
  protected int capacity;
  protected int hireFee;
  private String strDate = "TODO";

  // constructor for the class
  public VenueStore(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  // method to set or change the date of the venue
  public void setstrDate(String strDate) {
    this.strDate = strDate;
  }

  // method to get the date of the venue
  public String getstrDate() {
    return strDate;
  }

  // method to get the date of the venue in LocalDate format
  public LocalDate getLocalDate() {
    LocalDate datetypDate;
    // splitting the date into day, month and year as interger values and combining
    // them to form a
    // LocalDate
    String[] dateSplit = strDate.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    datetypDate = LocalDate.of(year, month, day);
    return datetypDate;
  }
}
