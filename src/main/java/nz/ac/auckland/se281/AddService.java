package nz.ac.auckland.se281;

public abstract class AddService {

  protected String service, bookRef, strType;
  protected int caterCost = 0;
  protected int musicCost = 0;
  protected int totCost = 0;
  protected int attendees = 0;

  public AddService(String service, String bookRef, String type, int attendees) {
    this.service = service;
    this.bookRef = bookRef;
    if (service.equals("Catering Service")) {
      this.strType = Types.CateringType.valueOf(type).getName();
      this.caterCost = attendees * Types.CateringType.valueOf(type).getCostPerPerson();
    }else if (service.equals("Music Service")) {
      this.musicCost = 500;
    }
  }

  

  public String getType() {
    return this.strType;
  }


  public static void noBookRef(String service, String bookRef) {
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(service, bookRef);
  }
}
