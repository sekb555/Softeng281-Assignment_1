package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends AddService {

  protected static int costPP;

  public CateringService(CateringType type, String bookRef, int attendees) {
    super("Catering Service", bookRef, String.valueOf(type), attendees);

    this.bookRef = bookRef;
    costPP = type.getCostPerPerson();
  }

  public static int cateringCost() {
    return costPP;
  }

  public int sumTotCost() {
    return this.totCost + this.caterCost;
  }
}
