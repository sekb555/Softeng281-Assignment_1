package nz.ac.auckland.se281;

public class MusicService extends AddService {

  // constructor for the MusicService class
  public MusicService(String bookRef) {
    super("Music Service", bookRef, "Music", 1);
    this.bookRef = bookRef;
  }

}
