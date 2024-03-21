package nz.ac.auckland.se281;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venuestore {

  //initializing variables required in the entire class
  public String venueName;
  public String venueCode;
  public int capacity;
  public int hireFee;
  public String strDate = "TODO";

  //creating a date formatter
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  //constructor for the class
  public Venuestore(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  //method to set or change the date of the venue
  public void setstrDate(String strDate) {
    this.strDate = strDate;
  }
  
  //method to get the date of the venue
  public String getstrDate() {
    return strDate;
  }

  //method to get the date of the venue in LocalDate format
  public LocalDate getLocalDate() {
    LocalDate datetypDate;
    String[] dateSplit = strDate.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    datetypDate = LocalDate.of(year, month, day);
    return datetypDate;
  }
}