package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends AddService {

  protected int costPerPerson;

  // constructor for the CateringService class
  public CateringService(CateringType type, String bookRef, int attendees) {
    super("Catering Service", bookRef, String.valueOf(type), attendees);

    this.bookRef = bookRef;
    costPerPerson = type.getCostPerPerson();
  }
}
