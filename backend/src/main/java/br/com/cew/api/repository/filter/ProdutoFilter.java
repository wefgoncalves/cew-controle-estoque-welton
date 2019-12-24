package br.com.cew.api.repository.filter;

import br.com.cew.api.model.vo.pagination.PageInputVO;

public class ProdutoFilter {

  private String nome;
  private PageInputVO paginacao;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public PageInputVO getPaginacao() {
    return paginacao;
  }

  public void setPaginacao(PageInputVO paginacao) {
    this.paginacao = paginacao;
  }

}
