package nz.ac.auckland.se281;

import java.time.LocalDate;

public class bookingstore {

  //initializing variables required in the entire class
  public String bookRef;
  public String Code;
  public LocalDate date;
  public String numAttend;
  public String email;

  //constructor for the class
  public bookingstore(String bookRef, String venueCode, LocalDate date, String numAttend, String email) {
    this.bookRef = bookRef;
    this.Code = venueCode;
    this.date = date;
    this.numAttend = numAttend;
    this.email = email;
  }

}
