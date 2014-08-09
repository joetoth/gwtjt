package gwtjt.client.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Options implements Serializable {
  List<Location> locations = new ArrayList<Location>();

  public List<Location> getLocations() {
    return locations;
  }
}
