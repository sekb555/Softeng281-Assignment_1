package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;

  private ArrayList<Venuestore> venues = new ArrayList<Venuestore>();

  public VenueHireSystem() {
    // TODO implement this method

  }

  public void printVenues() {
    // TODO implement this method
    if (venues.size() > 0) {
    } else if (venues.size() <= 0) {
      MessageCli.NO_VENUES.printMessage();
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    if(venueName == null || venueName.isEmpty()){
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage("venue");
      return;
    }

    try {
      capacity = Integer.valueOf(capacityInput);
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "integer");
      return;
    }
    try {
      hireFee = Integer.valueOf(hireFeeInput);
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hirefee", "integer");
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
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
