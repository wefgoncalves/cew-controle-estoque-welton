package br.com.cew.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.cew.api.model.Produto;
import br.com.cew.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  public Produto atualizar(Long id, Produto produto) {
    Produto produtoSalvo = buscarProdutoPorId(id);

    BeanUtils.copyProperties(produto, produtoSalvo, "id");
    return produtoRepository.save(produtoSalvo);
  }

  public void atualizarPropriedadeAtivo(Long id, Boolean status) {
    Produto produtoSalvo = buscarProdutoPorId(id);
    produtoSalvo.setAtivo(status);
    produtoRepository.save(produtoSalvo);
  }

  private Produto buscarProdutoPorId(Long id) {
    Produto produtoSalvo = produtoRepository.findOne(id);
    if (produtoSalvo == null || produtoSalvo.isInativo()) {
      throw new EmptyResultDataAccessException(1);
    }
    return produtoSalvo;
  }

}
