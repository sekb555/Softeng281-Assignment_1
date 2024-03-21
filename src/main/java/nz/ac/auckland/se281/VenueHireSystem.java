package nz.ac.auckland.se281;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  //initializing variables required in the entire class
  private int capacity;
  private int hireFee;
  private String sysDate;
  private LocalDate date_System;
 //creating arraylists to store venues and bookings
  private ArrayList<Venuestore> venues = new ArrayList<Venuestore>();
  private ArrayList<bookingstore> bookings = new ArrayList<bookingstore>();

  //creating a date formatter
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


  //constructor for the class
  public VenueHireSystem() {}

  //method to print all of the venues currently in the system
  public void printVenues() {

    //creating an arraylist to store the numbers from 0 to 9 in string format(5 = "five" etc.)
    int listSize = venues.size();
    ArrayList<String> nums = new ArrayList<String>();
    Collections.addAll(
        nums, "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    //printing the venues in the system with the appropriate message based on the number of venues
    if (listSize == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", nums.get(1), "");
      for (int i = 0; i < listSize; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venues.get(i).venueName,
            venues.get(i).venueCode,
            String.valueOf(venues.get(i).capacity),
            String.valueOf(venues.get(i).hireFee),
            venues.get(i).getstrDate());
      }
    } else if (listSize > 1 && listSize <= 9) {
      MessageCli.NUMBER_VENUES.printMessage("are", nums.get(listSize), "s");
      for (int i = 0; i < listSize; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venues.get(i).venueName,
            venues.get(i).venueCode,
            String.valueOf(venues.get(i).capacity),
            String.valueOf(venues.get(i).hireFee),
            venues.get(i).getstrDate());
      }
    } else if (listSize >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(listSize), "s");
      for (int i = 0; i < listSize; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venues.get(i).venueName,
            venues.get(i).venueCode,
            String.valueOf(venues.get(i).capacity),
            String.valueOf(venues.get(i).hireFee),
            venues.get(i).getstrDate());
      }
    } else if (listSize <= 0) {
      MessageCli.NO_VENUES.printMessage();
    }
  }

  //method to create a new venue using input from the user
  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    //making sure that there is an input name
    if (venueName == null || venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage("venue");
      return;
    }

    //making sure that the venue code is unique
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueName);
        return;
      }
    }

    //making sure that the capacity and hire fee are actually integers
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

    //creating a new venue and adding input values to it 
    Venuestore venue = new Venuestore(venueName, venueCode, capacity, hireFee);
    venue.venueName = venueName;
    venue.venueCode = venueCode;
    venue.capacity = capacity;
    venue.hireFee = hireFee;
    //setting the date of the venue to the system date if it is set
    if (sysDate != null && !sysDate.isEmpty()) {
      venue.setstrDate(sysDate);
    }
    venues.add(venue);
    //printing a success message
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  //method to set the system date
  public void setSystemDate(String dateInput) {

    //setting the system date and seperating it into day, month and year and storing it in a LocalDate object
    sysDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
    String[] dateSplit = sysDate.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    date_System = LocalDate.of(year, month, day);
  

      //setting the date for all venues
      for (int i = 0; i < venues.size(); i++) {
        if (venues.get(i).getstrDate().equals("TODO") || venues.get(i).getstrDate().isEmpty()|| venues.get(i).getLocalDate().isBefore(date_System)) {
          venues.get(i).setstrDate(sysDate);
        }
      }
    }

  //method to print the system date
  public void printSystemDate() {
    //printing the system date but checking is it is empty or not first
    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(sysDate);
    }
  }

  //method to make a booking using input from the user
  public void makeBooking(String[] options) {

    //seperating the input into the venue code, date, email and number of attendees
    //storing the date in a LocalDate object and a as a String
    String date = options[1];
    String[] dateSplit = date.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    LocalDate bookDate = LocalDate.of(year, month, day);

    //adding one day to the booking date for the next available date
    LocalDate bookDateplus1 = bookDate.plusDays(1);
    String nextDate = bookDateplus1.format(formatter);

    String Code = options[0];
    String email = options[2];
    String numAttend = options[3];
    String bookRef = BookingReferenceGenerator.generateBookingReference();
    String Venue = null;
    int capacity = 0;

    boolean codeExists = false;
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(Code)) {
        Venue = venues.get(i).venueName;
        capacity = venues.get(i).capacity;
        venues.get(i).setstrDate(nextDate);
        codeExists = true;
      }
    }

    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).date.equals(bookDate) && bookings.get(i).Code.equals(Code)) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(Venue, date);
        return;
      }
    }

    //checking if the number of attendees is less than a quarter of the venue capacity or more than the venue capacity and changing it accordingly
    int quarterCapacity = capacity / 4;
    if (Integer.valueOf(numAttend) <= (quarterCapacity)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          numAttend, String.valueOf(quarterCapacity), String.valueOf(capacity));
      numAttend = String.valueOf(quarterCapacity);
    } else if (Integer.valueOf(numAttend) > capacity) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          numAttend, String.valueOf(capacity), String.valueOf(capacity));
      numAttend = String.valueOf(capacity);
    } else{}

    //checking if the date is set, the date is in the past, there are venues in the system and the venue code exists
    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (bookDate.getYear() < date_System.getYear()
        || bookDate.getMonthValue() < date_System.getMonthValue()
        || bookDate.getDayOfMonth() < date_System.getDayOfMonth()) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(date, sysDate);
      return;
    } else if (venues.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    } else if (!codeExists) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(Code);
      return;
    } else {
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookRef, Venue, date, numAttend);
    }

    //creating a new booking and adding input values to it
    bookingstore booking = new bookingstore(bookRef, Code, bookDate, numAttend, email);
    booking.Code = Code;
    booking.date = bookDate;
    booking.email = email;
    booking.numAttend = numAttend;
    booking.bookRef = bookRef;
    bookings.add(booking);
  }

  //method to print all of the bookings for a specific venue
  public void printBookings(String venueCode) {
    //for loop goes through each value in venues arraylist
    for(int i = 0; i < venues.size(); i++) {
      //if the venue code is found in the arraylist print the title of the venue
      if(venues.get(i).venueCode.equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venues.get(i).venueName);
        //for loop goes through each value in bookings arraylist and prints the booking reference and date for each booking
        for(int j = 0; j < bookings.size(); j++) {
          if(bookings.get(j).Code.equals(venueCode)) {
            MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(bookings.get(j).bookRef, bookings.get(j).date.format(formatter));
          }
        }
        return;
      }
    }
    MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {}

  public void addServiceMusic(String bookingReference) {}

  public void addServiceFloral(String bookingReference, FloralType floralType) {}

  public void viewInvoice(String bookingReference) {}
}
