package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private int capacity;
  private int hireFee;
  public String sysDate;
  private LocalDate date_System;

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
    String[]dateSplit = sysDate.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    date_System = LocalDate.of(year, month, day);

  }

  public void printSystemDate() {
    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(sysDate);
    }
  }

  public void makeBooking(String[] options) {



    String date = options[1];
    String[] dateSplit = date.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    LocalDate bookDate = LocalDate.of(year, month, day);



    String Code = options[0];
    String email = options[2];
    String numAttend = options[3];
    String bookRef = BookingReferenceGenerator.generateBookingReference();
    String Venue = null;
    int capacity = 0;

    boolean codeExists = false;
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(Code)){
        Venue = venues.get(i).venueName;
        capacity = venues.get(i).capacity;
        codeExists = true;
      }
    }


    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).date.equals(bookDate) && bookings.get(i).Code.equals(Code)){
          MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(Venue, date);
          return;
        }
      }

    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }else if(venues.size() == 0){
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }else if (!codeExists){
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(Code);
      return;
    }else if (bookDate.isBefore(date_System)) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(date, sysDate);
      return;
    }else {
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookRef, Venue, date, numAttend);
    }

    

    int quarterCapacity = capacity/4;
    if (Integer.valueOf(numAttend) <= (quarterCapacity)){
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(numAttend, String.valueOf(quarterCapacity), String.valueOf(capacity));
      numAttend = String.valueOf(quarterCapacity);
    }else if (Integer.valueOf(numAttend) > capacity){
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(numAttend, String.valueOf(capacity), String.valueOf(capacity));
      numAttend = String.valueOf(capacity);
    }

    bookingstore booking = new bookingstore(bookRef, Code, bookDate, numAttend, email);
    booking.Code = Code;
    booking.date = bookDate;
    booking.email = email;
    booking.numAttend = numAttend;
    booking.bookRef = bookRef;
    bookings.add(booking);

  }

  public void printBookings(String venueCode) {}

  public void addCateringService(String bookingReference, CateringType cateringType) {}

  public void addServiceMusic(String bookingReference) {}

  public void addServiceFloral(String bookingReference, FloralType floralType) {}

  public void viewInvoice(String bookingReference) {}
}
