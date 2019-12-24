package br.com.cew.api.repository.produto;

import br.com.cew.api.model.Produto;
import br.com.cew.api.model.vo.pagination.PageVO;
import br.com.cew.api.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {

  // public PageVO<Produto> filtrar(ProdutoFilter produtoFilter);
  public PageVO<Produto> filtrar(ProdutoFilter produtoFilter);

}
