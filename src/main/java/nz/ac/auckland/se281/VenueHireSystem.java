package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  public String sysDate;

  private ArrayList<Venuestore> venues = new ArrayList<Venuestore>();
  private ArrayList<bookingstore> bookings = new ArrayList<bookingstore>();

  public VenueHireSystem() {}

  public void printVenues() {

    int listSize = venues.size();
    ArrayList<String> nums = new ArrayList<String>();
    Collections.addAll(
        nums, "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    if (listSize == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", nums.get(1), "");
      for (int i = 0; i < listSize; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venues.get(i).venueName,
            venues.get(i).venueCode,
            String.valueOf(venues.get(i).capacity),
            String.valueOf(venues.get(i).hireFee));
      }
    } else if (listSize > 1 && listSize <= 9) {
      MessageCli.NUMBER_VENUES.printMessage("are", nums.get(listSize), "s");
      for (int i = 0; i < listSize; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venues.get(i).venueName,
            venues.get(i).venueCode,
            String.valueOf(venues.get(i).capacity),
            String.valueOf(venues.get(i).hireFee));
      }
    } else if (listSize >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(listSize), "s");
      for (int i = 0; i < listSize; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venues.get(i).venueName,
            venues.get(i).venueCode,
            String.valueOf(venues.get(i).capacity),
            String.valueOf(venues.get(i).hireFee));
      }
    } else if (listSize <= 0) {
      MessageCli.NO_VENUES.printMessage();
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    if (venueName == null || venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage("venue");
      return;
    }

    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueName);
        return;
      }
    }

    try {
      capacity = Integer.valueOf(capacityInput);
      if (capacity <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }
    try {
      hireFee = Integer.valueOf(hireFeeInput);
      if (hireFee <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }
    Venuestore venue = new Venuestore(venueName, venueCode, capacity, hireFee);
    venue.venueName = venueName;
    venue.venueCode = venueCode;
    venue.capacity = capacity;
    venue.hireFee = hireFee;
    venues.add(venue);

    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    sysDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(sysDate);
    }
  }

  public void makeBooking(String[] options) {

    String Code = options[0];
    String date = options[1];
    String email = options[2];
    String numAttend = options[3];
    String bookRef = BookingReferenceGenerator.generateBookingReference();
    String Venue = null;

    bookingstore booking = new bookingstore(bookRef, Code, date, numAttend, email);
    booking.Code = Code;
    booking.date = date;
    booking.email = email;
    booking.numAttend = numAttend;
    booking.bookRef = bookRef;

    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(Code)){
        Venue = venues.get(i).venueName;
      }
    }

    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }else if(venues.size() == 0){
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }else {
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookRef, Venue, date, numAttend);
    }

  
    

  }

  public void printBookings(String venueCode) {}

  public void addCateringService(String bookingReference, CateringType cateringType) {}

  public void addServiceMusic(String bookingReference) {}

  public void addServiceFloral(String bookingReference, FloralType floralType) {}

  public void viewInvoice(String bookingReference) {}
}
