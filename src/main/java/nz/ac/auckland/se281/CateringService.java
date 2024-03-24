package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends AddService {

  public CateringService(CateringType type, String bookRef) {
    super("Catering Service", type, bookRef);
  }
}
