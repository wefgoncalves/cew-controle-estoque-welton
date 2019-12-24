package br.com.cew.api.model.vo.pagination;

import java.io.Serializable;
import java.util.List;

public class PageVO<E extends Serializable> extends PageInputVO {

  private List<E> content;

  private int numberOfElements;
  private int totalPages;
  private boolean first;
  private boolean last;

  public PageVO() {
    super();
  }

  public PageVO(int pageNumber, int size, List<E> content, long totalElements, boolean changedQuery,
      SortVO... sort) {
    super(pageNumber, size, totalElements, changedQuery, sort);

    init(content);
  }

  private void init(List<E> content) {
    setContent(content);

    if (getContent() == null || getContent().isEmpty())
      setNumberOfElements(0);
    else
      setNumberOfElements(getContent().size());

    if (getTotalElements() <= getSize())
      setTotalPages(1);
    else {
      int totalPages = (int) (getTotalElements() / getSize());

      if (getTotalElements() % getSize() == 0)
        setTotalPages(totalPages);
      else
        setTotalPages(totalPages + 1);
    }

    setFirst(getPageNumber() == 0);
    setLast(getPageNumber() == (getTotalPages() - 1));
  }

  public List<E> getContent() {
    return content;
  }

  public void setContent(List<E> content) {
    this.content = content;
  }

  public int getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(int numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public boolean isFirst() {
    return first;
  }

  public void setFirst(boolean first) {
    this.first = first;
  }

  public boolean isLast() {
    return last;
  }

  public void setLast(boolean last) {
    this.last = last;
  }

}
