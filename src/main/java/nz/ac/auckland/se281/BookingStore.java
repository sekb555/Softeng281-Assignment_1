package nz.ac.auckland.se281;

import java.time.LocalDate;

public class BookingStore {

  // initializing variables required in the entire class
  protected String bookRef, venCode, numAttend, email;
  protected LocalDate sysDate, bookDate;
  protected int venueCost;
  protected int totalCost;

  // constructor for the class
  public BookingStore(
      String bookRef,
      String venueCode,
      LocalDate date,
      String numAttend,
      String email,
      int venueCost,
      LocalDate sysDate) {

    this.bookRef = bookRef;
    this.venCode = venueCode;
    this.bookDate = date;
    this.numAttend = numAttend;
    this.email = email;
    this.venueCost = venueCost;
    this.sysDate = sysDate;
  }

  public void setTotalCost(int totalCost) {
    this.totalCost = totalCost;
  }

  public int getTotalCost() {
    return totalCost;
  }
}
