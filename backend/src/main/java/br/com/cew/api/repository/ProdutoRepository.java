package br.com.cew.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cew.api.model.Produto;
import br.com.cew.api.repository.produto.ProdutoRepositoryQuery;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {

}
