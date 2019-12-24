package br.com.cew.api.resource;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.cew.api.event.RecursoCriadoEvent;
import br.com.cew.api.model.Produto;
import br.com.cew.api.repository.ProdutoRepository;
import br.com.cew.api.repository.filter.ProdutoFilter;
import br.com.cew.api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public List<Produto> pesquisar(ProdutoFilter produtoFilter) {
    // return produtoRepository.filtrar(produtoFilter);
    return produtoRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto, HttpServletResponse response) {
    Produto produtoSalvo = produtoRepository.save(produto);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
    Produto produto = produtoRepository.findOne(id);
    return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long id) {
    produtoRepository.delete(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
    Produto produtoSalvo = produtoService.atualizar(id, produto);
    return ResponseEntity.ok(produtoSalvo);
  }

  @PutMapping("/{id}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean status) {
    produtoService.atualizarPropriedadeAtivo(id, status);
  }
}
