package my.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperModel implements Serializable, ISuperModel {
  String name;
  List<String> favoriteColors = new ArrayList<>();
  Map<String, Boolean> osRatings = new HashMap<>();
  Integer id;
  List<Child> children = new ArrayList<>();

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#getId()
   */
  @Override
  public Integer getId() {
    return id;
  }

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#setId(java.lang.Integer)
   */
  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#setName(java.lang.String)
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#getFavoriteColors()
   */
  @Override
  public List<String> getFavoriteColors() {
    return favoriteColors;
  }

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#setFavoriteColors(java.util.List)
   */
  @Override
  public void setFavoriteColors(List<String> favoriteColors) {
    this.favoriteColors = favoriteColors;
  }

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#getOsRatings()
   */
  @Override
  public Map<String, Boolean> getOsRatings() {
    return osRatings;
  }

  /* (non-Javadoc)
   * @see my.shared.ISuperModel#setOsRatings(java.util.Map)
   */
  @Override
  public void setOsRatings(Map<String, Boolean> osRatings) {
    this.osRatings = osRatings;
  }

  public List<Child> getChildren() {
    return children;
  }

  public void setChildren(List<Child> children) {
    this.children = children;
  }

}
