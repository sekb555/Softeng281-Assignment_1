package nz.ac.auckland.se281;

public class bookingstore {

  public String bookRef;
  public String Code;
  public String date;
  public String numAttend;
  public String email;

  public bookingstore(String bookRef, String venueCode, String date, String numAttend, String email) {
    this.bookRef = bookRef;
    this.Code = venueCode;
    this.date = date;
    this.numAttend = numAttend;
    this.email = email;
  }

}
