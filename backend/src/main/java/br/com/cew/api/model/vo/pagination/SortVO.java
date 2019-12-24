package br.com.cew.api.model.vo.pagination;

public class SortVO {

  private String property;
  private Direction direction = Direction.ASC;

  private boolean ignoreCase = false;
  private NullHandling nullHandling = NullHandling.NATIVE;

  private boolean ascending;
  private boolean descending;

  public SortVO() {
    super();
  }

  private SortVO(final String property, final Direction direction, final boolean ignoreCase,
      final NullHandling nullHandling) {
    this();

    setProperty(property);
    setDirection(direction);
    setIgnoreCase(ignoreCase);
    setNullHandling(nullHandling);
    ascending = direction == Direction.ASC;
    descending = direction == Direction.DESC;
  }

  public SortVO(final String property, final Direction direction) {
    this(property, direction, false, NullHandling.NATIVE);
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(final String property) {
    this.property = property;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(final Direction direction) {
    this.direction = direction;
  }

  public boolean isIgnoreCase() {
    return ignoreCase;
  }

  public void setIgnoreCase(final boolean ignoreCase) {
    this.ignoreCase = ignoreCase;
  }

  public NullHandling getNullHandling() {
    return nullHandling;
  }

  public void setNullHandling(final NullHandling nullHandling) {
    this.nullHandling = nullHandling;
  }

  public boolean isDescending() {
    return descending;
  }

  public final void setDescending(final boolean descending) {
    this.descending = descending;
  }

  public boolean isAscending() {
    return ascending;
  }

  public void setAscending(final boolean ascending) {
    this.ascending = ascending;
  }

  public enum Direction {
    ASC, DESC
  }

  public enum NullHandling {
    NATIVE
  }

}
