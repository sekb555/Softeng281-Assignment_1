package nz.ac.auckland.se281;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  // initializing variables required in the entire class
  private int capacity;
  private int hireFee;
  private String sysDate;
  private LocalDate systemDate;

  // creating arraylists to store venues and bookings
  private ArrayList<VenueStore> venues = new ArrayList<VenueStore>();
  private ArrayList<BookingStore> bookings = new ArrayList<BookingStore>();
  private ArrayList<AddService> services = new ArrayList<AddService>();

  // creating a date formatter
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  // constructor for the class
  public VenueHireSystem() {}

  // method to print all of the venues currently in the system
  public void printVenues() {

    // creating an arraylist to store the numbers from 0 to 9 in string format(5 =
    // "five" etc.)
    int listSize = venues.size();
    ArrayList<String> nums = new ArrayList<String>();
    Collections.addAll(
        nums, "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    // printing the venues in the system with the appropriate message based on the
    // number of venues
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

  // method to create a new venue using input from the user
  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    // making sure that there is an input name
    if (venueName == null || venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage("venue");
      return;
    }

    // making sure that the venue code is unique
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueName);
        return;
      }
    }

    // making sure that the capacity and hire fee are actually integers
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

    // creating a new venue and adding input values to it
    VenueStore venue = new VenueStore(venueName, venueCode, capacity, hireFee);
    venues.add(venue);
    // setting the date of the venue to the system date if it is set
    if (sysDate != null && !sysDate.isEmpty()) {
      venue.setstrDate(sysDate);
    }

    // printing a success message
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  // method to set the system date
  public void setSystemDate(String dateInput) {

    // setting the system date and seperating it into day, month and year and
    // storing it in a
    // LocalDate object
    sysDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
    String[] dateSplit = sysDate.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    systemDate = LocalDate.of(year, month, day);

    // setting the date for all venues
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).getstrDate().equals("TODO")
          || venues.get(i).getstrDate().isEmpty()
          || venues.get(i).getLocalDate().isBefore(systemDate)) {
        venues.get(i).setstrDate(sysDate);
      }
    }
  }

  // method to print the system date
  public void printSystemDate() {
    // printing the system date but checking is it is empty or not first
    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(sysDate);
    }
  }

  // method to make a booking using input from the user
  public void makeBooking(String[] options) {

    // seperating the input into the venue code, date, email and number of attendees
    // storing the date in a LocalDate object and a as a String
    String date = options[1];
    String[] dateSplit = date.split("/");
    int day = Integer.valueOf(dateSplit[0]);
    int month = Integer.valueOf(dateSplit[1]);
    int year = Integer.valueOf(dateSplit[2]);
    LocalDate bookDate = LocalDate.of(year, month, day);

    String bookVenCode = options[0];
    String email = options[2];
    String numAttend = options[3];
    String bookRef = BookingReferenceGenerator.generateBookingReference();
    String strVenName = null;
    int bookVenCost = 0;
    int capacity = 0;

    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(bookVenCode)) {
        bookVenCost = venues.get(i).hireFee;
      }
    }

    // adding one day to the booking date for the next available date
    LocalDate bookDateplus1 = bookDate.plusDays(1);
    LocalDate bookDateminus1 = bookDate.minusDays(1);
    String nextDate;
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(bookVenCode)) {
        if (venues.get(i).getLocalDate().isBefore(bookDateminus1)) {
        } else {
          nextDate = bookDateplus1.format(formatter);
          venues.get(i).setstrDate(nextDate);
        }
      }
    }

    boolean codeExists = false;
    for (int i = 0; i < venues.size(); i++) {
      if (venues.get(i).venueCode.equals(bookVenCode)) {
        strVenName = venues.get(i).venueName;
        capacity = venues.get(i).capacity;
        codeExists = true;
      }
    }

    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).bookDate.equals(bookDate)
          && bookings.get(i).venCode.equals(bookVenCode)) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(strVenName, date);
        return;
      }
    }

    // checking if the number of attendees is less than a quarter of the venue
    // capacity or more than
    // the venue capacity and changing it accordingly
    int quarterCapacity = capacity / 4;
    if (Integer.valueOf(numAttend) <= (quarterCapacity)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          numAttend, String.valueOf(quarterCapacity), String.valueOf(capacity));
      numAttend = String.valueOf(quarterCapacity);
    } else if (Integer.valueOf(numAttend) > capacity) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          numAttend, String.valueOf(capacity), String.valueOf(capacity));
      numAttend = String.valueOf(capacity);
    } else {
    }

    // checking if the date is set, the date is in the past, there are venues in the
    // system and the
    // venue code exists
    if (sysDate == null || sysDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (bookDate.isBefore(systemDate)) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(date, sysDate);
      return;
    } else if (venues.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    } else if (!codeExists) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(bookVenCode);
      return;
    } else {
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookRef, strVenName, date, numAttend);
    }

    // creating a new booking and adding input values to it
    BookingStore booking =
        new BookingStore(bookRef, bookVenCode, bookDate, numAttend, email, bookVenCost, systemDate);
    bookings.add(booking);
  }

  // method to print all of the bookings for a specific venue
  public void printBookings(String venueCode) {
    // for loop goes through each value in venues arraylist
    for (int i = 0; i < venues.size(); i++) {
      // if the venue code is found in the arraylist print the title of the venue
      if (venues.get(i).venueCode.equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venues.get(i).venueName);
        int count = 0;
        // for loop goes through each value in bookings arraylist and prints the booking
        // reference
        // and date for each booking
        if (bookings.size() == 0) {
          MessageCli.PRINT_BOOKINGS_NONE.printMessage(venues.get(i).venueName);
          return;
        }
        for (int j = 0; j < bookings.size(); j++) {
          if (bookings.get(j).venCode.equals(venueCode)) {
            MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
                bookings.get(j).bookRef, bookings.get(j).bookDate.format(formatter));
            count++;
          } else if (count == 0 && j == bookings.size() - 1) {
            MessageCli.PRINT_BOOKINGS_NONE.printMessage(venues.get(i).venueName);
          }
        }
        return;
      }
    }
    MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
  }

  // method to add a catering service to a booking
  public void addCateringService(String bookingReference, CateringType cateringType) {
    String currentService = "Catering";
    if (bookings.size() == 0) {
      AddService.noBookRef(currentService, bookingReference);
      return;
    }
    // for loop goes through each value in bookings arraylist and add the catering
    // service to the
    // specified booking
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).bookRef.equals(bookingReference)) {
        AddService service =
            new CateringService(
                cateringType, bookingReference, Integer.valueOf(bookings.get(i).numAttend));
        services.add(service);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
            "Catering (" + cateringType.getName() + ")", bookingReference);
      } else if (i == bookings.size() - 1) {
        AddService.noBookRef(currentService, bookingReference);
      }
    }
  }

  public void addServiceMusic(String bookingReference) {
    String currentService = "Music";
    if (bookings.size() == 0) {
      AddService.noBookRef(currentService, bookingReference);
      return;
    }
    // for loop goes through each value in bookings arraylist and add the music
    // service to the
    // specified booking
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).bookRef.equals(bookingReference)) {
        AddService service = new MusicService(bookingReference);
        services.add(service);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
      } else if (i == bookings.size() - 1) {
        AddService.noBookRef(currentService, bookingReference);
      }
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    if (bookings.size() == 0) {
      AddService.noBookRef("Floral", bookingReference);
      return;
    }

    // for loop checks if the booking reference is in the bookings arraylist and
    // adds the floaral service to the specified booking
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).bookRef.equals(bookingReference)) {
        AddService service = new FloralService(bookingReference, floralType);
        services.add(service);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
            "Floral (" + floralType.getName() + ")", bookingReference);
        return;
      } else if (i == bookings.size() - 1) {
        AddService.noBookRef("Floral", bookingReference);
      }
    }
  }

  // method to view the invoice for a specific booking
  public void viewInvoice(String bookingReference) {
    if (bookings.size() == 0) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }

    // for loop for going through each value in bookings arraylist and services
    // arraylist until the booking reference given is found
    for (int i = 0; i < bookings.size(); i++) {
      if (bookings.get(i).bookRef.equals(bookingReference)) {
        // prints the top half of the invoice
        MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage();
        // prints the venue hire cost for the specified booking
        MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(
            String.valueOf(bookings.get(i).venueCost));
        for (int j = 0; j < services.size(); j++) {
          if (services.get(j).bookRef.equals(bookingReference)) {
            if (services.get(j).service.equals("Catering Service")) {
              // prints the catering cost for the specified booking
              MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
                  services.get(j).getType(), String.valueOf(services.get(j).caterCost));
            } else if (services.get(j).service.equals("Music Service")) {
              // prints the music fee for the specified booking
              MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(
                  String.valueOf(services.get(j).musicCost));
            } else if (services.get(j).service.equals("Floral Service")) {
              // prints the floral cost for the specified booking
              MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
                  services.get(j).getType(), String.valueOf(services.get(j).floralCost));
            }
          }
        }
        // prints the bottom half of the invoice for the specified booking
        MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(
            String.valueOf(sumTotCost(bookingReference)));
        return;
      }
    }
  }

  public int sumTotCost(String bookingReference) {
    int totCost = 0;
    // for loop goes through each value in bookings arraylist and adds the cost of
    // the venue to the total cost
    for (int j = 0; j < bookings.size(); j++) {
      if (bookings.get(j).bookRef.equals(bookingReference)) {
        totCost += bookings.get(j).venueCost;
      }
    }

    // for loop goes through each value in services arraylist and adds the cost of
    // each service for the given booking reference
    for (int i = 0; i < services.size(); i++) {
      if (services.get(i).bookRef.equals(bookingReference)) {
        totCost +=
            services.get(i).caterCost + services.get(i).musicCost + services.get(i).floralCost;
      }
    }
    return totCost;
  }
}
