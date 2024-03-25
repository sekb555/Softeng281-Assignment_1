package nz.ac.auckland.se281;

public abstract class AddService {

  protected String service;
  protected String bookRef;
  protected String strType;
  protected int caterCost = 0;
  protected int musicCost = 0;
  protected int floralCost = 0;
  protected int attendees = 0;

  // constructor for the AddService class that creates the different services
  public AddService(String service, String bookRef, String type, int attendees) {
    this.service = service;
    this.bookRef = bookRef;
    if (service.equals("Catering Service")) {
      this.strType = Types.CateringType.valueOf(type).getName();
      this.caterCost = attendees * Types.CateringType.valueOf(type).getCostPerPerson();
    } else if (service.equals("Music Service")) {
      this.musicCost = 500;
    } else if (service.equals("Floral Service")) {
      this.strType = Types.FloralType.valueOf(type).getName();
      this.floralCost = Types.FloralType.valueOf(type).getCost();
    }
  }

  // returns the typw of the service(only for catering and floral services)
  public String getType() {
    return this.strType;
  }

  // prints a message if the booking reference is not found
  public static void noBookRef(String service, String bookRef) {
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(service, bookRef);
  }
}
