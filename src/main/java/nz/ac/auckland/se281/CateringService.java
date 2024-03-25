package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends AddService {

  protected int costPP;

  // constructor for the CateringService class
  public CateringService(CateringType type, String bookRef, int attendees) {
    super("Catering Service", bookRef, String.valueOf(type), attendees);

    this.bookRef = bookRef;
    costPP = type.getCostPerPerson();
  }

  // returns the cost per person for the specific catering service
  public int cateringCost() {
    return costPP;
  }

}
