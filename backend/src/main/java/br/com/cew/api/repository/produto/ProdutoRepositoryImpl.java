package br.com.cew.api.repository.produto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.StringUtils;
import br.com.cew.api.model.Produto;
import br.com.cew.api.model.vo.pagination.PageInputVO;
import br.com.cew.api.model.vo.pagination.PageVO;
import br.com.cew.api.repository.filter.ProdutoFilter;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public PageVO<Produto> filtrar(ProdutoFilter produtoFilter) {
    List<Produto> listaProduto = new ArrayList<>();
    PageInputVO paginacao = produtoFilter.getPaginacao();
    Long totalElements = paginacao.getTotalElements();
    final boolean changedQuery = paginacao.isChangedQuery();

    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
    Root<Produto> root = criteria.from(Produto.class);

    Predicate[] predicates = criarResticoes(produtoFilter, builder, root);
    criteria.where(predicates);

    TypedQuery<Produto> query = manager.createQuery(criteria);

    if (changedQuery) {
      totalElements = (long) query.getResultList().size();
    }

    query.setFirstResult(paginacao.getPageNumber() * paginacao.getSize());
    query.setMaxResults(paginacao.getSize());
    listaProduto = query.getResultList();

    return new PageVO<Produto>(paginacao.getPageNumber(), paginacao.getSize(), listaProduto, totalElements,
        changedQuery, paginacao.getSort());
  }

  private Predicate[] criarResticoes(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<Produto> root) {
    List<Predicate> predicates = new ArrayList<>();

    if (!StringUtils.isEmpty(produtoFilter.getNome())) {
      predicates.add(builder.like(builder.lower(root.get("nome")), "%" + produtoFilter.getNome() + "%"));
    }

    return predicates.toArray(new Predicate[predicates.size()]);
  }

}
