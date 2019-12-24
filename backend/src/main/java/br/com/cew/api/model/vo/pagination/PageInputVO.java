package br.com.cew.api.model.vo.pagination;

public class PageInputVO {

  public static int DEFAULT_SIZE = 10;

  private int pageNumber;
  private int size;

  private long totalElements;

  private boolean changedQuery;

  private SortVO[] sort;

  public PageInputVO() {
    super();
  }

  private PageInputVO(int pageNumber, int size, SortVO... sort) {
    this();

    setPageNumber(pageNumber);
    setSize(size);
    setSort(sort);
  }

  public PageInputVO(int pageNumber, int size, long totalElements, boolean changedQuery, SortVO... sort) {
    this(pageNumber, size, sort);

    setTotalElements(totalElements);
    setChangedQuery(changedQuery);
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public SortVO[] getSort() {
    return sort;
  }

  public void setSort(SortVO[] sort) {
    this.sort = sort;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public boolean isChangedQuery() {
    return changedQuery;
  }

  public void setChangedQuery(boolean changedQuery) {
    this.changedQuery = changedQuery;
  }

}
