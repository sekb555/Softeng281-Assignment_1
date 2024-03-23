package nz.ac.auckland.se281;

import java.time.LocalDate;

public class BookingStore {

  // initializing variables required in the entire class
  protected String bookRef;
  protected String VenCode;
  protected LocalDate date;
  protected String numAttend;
  protected String email;

  // constructor for the class
  public BookingStore(
      String bookRef, String venueCode, LocalDate date, String numAttend, String email) {
    this.bookRef = bookRef;
    this.VenCode = venueCode;
    this.date = date;
    this.numAttend = numAttend;
    this.email = email;
  }
}
