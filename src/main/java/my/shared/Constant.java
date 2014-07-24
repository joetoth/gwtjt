package my.shared;

import java.io.Serializable;

public class Constant<T> implements Serializable {
  long id;
  String localizedName;
  int entityTypeId;
  Long parentId;
  T attributes;

  public Constant() {
    // TODO Auto-generated constructor stub
  }

  public Constant(long id, String localizedName, int entityTypeId,
      Long parentId, T attributes) {
    super();
    this.id = id;
    this.localizedName = localizedName;
    this.entityTypeId = entityTypeId;
    this.parentId = parentId;
    this.attributes = attributes;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLocalizedName() {
    return localizedName;
  }

  public void setLocalizedName(String localizedName) {
    this.localizedName = localizedName;
  }

  public int getEntityTypeId() {
    return entityTypeId;
  }

  public void setEntityTypeId(int entityTypeId) {
    this.entityTypeId = entityTypeId;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public T getAttributes() {
    return attributes;
  }

  public void setAttributes(T attributes) {
    this.attributes = attributes;
  }

}
