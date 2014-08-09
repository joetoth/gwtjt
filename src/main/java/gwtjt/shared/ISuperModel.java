package gwtjt.shared;

import java.util.List;
import java.util.Map;

public interface ISuperModel {

  public abstract Integer getId();

  public abstract void setId(Integer id);

  public abstract String getName();

  public abstract void setName(String name);

  public abstract List<String> getFavoriteColors();

  public abstract void setFavoriteColors(List<String> favoriteColors);

  public abstract Map<String, Boolean> getOsRatings();

  public abstract void setOsRatings(Map<String, Boolean> osRatings);

}