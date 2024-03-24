package nz.ac.auckland.se281;


public abstract class AddService {

  protected String service;
  protected String bookRef;
  protected String strType;
  protected int totCost;


  
  public AddService(String service, String bookRef, String type, int attendees) {
    this.service = service;
    this.bookRef = bookRef;
    this.strType = Types.CateringType.valueOf(type).getName();
    this.totCost = attendees * Types.CateringType.valueOf(type).getCostPerPerson();
  }
  
  public String getType() {
    return this.strType;
  }

  public static void noBookRef(String service, String bookRef) {
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(service, bookRef);
  }
}
