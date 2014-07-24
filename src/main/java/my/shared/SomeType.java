package my.shared;


public enum SomeType {

  @DefaultMessage("250x250 leaderboard")
  AD_SIZE_250X250(1, false, 250, 250);

  long id;
  boolean isText;
  int width;
  int height;

  private SomeType(long id, boolean isText, int width, int height) {
    this.id = id;
    this.isText = isText;
    this.width = width;
    this.height = height;
  }

  public long getId() {
    return id;
  }

  public boolean isText() {
    return isText;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

}
