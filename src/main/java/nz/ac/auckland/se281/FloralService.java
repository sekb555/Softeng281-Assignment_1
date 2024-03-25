package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class FloralService extends AddService {

    // constructor for the FloralService class
    public FloralService(String bookRef, FloralType type) {
        super("Floral Service", bookRef, String.valueOf(type), 1);
        this.bookRef = bookRef;

    }

}