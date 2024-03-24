package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public abstract class AddService {

  protected String service;
  protected CateringType type;
  protected String bookRef;

  public AddService(String service, CateringType type, String bookRef) {
    this.service = service;
    this.type = type;
    this.bookRef = bookRef;
  }
}
