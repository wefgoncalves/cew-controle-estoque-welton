import { environment } from './../../environments/environment.prod';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Produto } from './produto';
import { tap, delay, take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  //private readonly API = `${environment.API}cew/produtos`;
  private readonly API = 'http://localhost:8080/cew/produtos';

  constructor(private http: HttpClient) { }

  listarProdutos() {
    return this.http.get<Produto[]>(this.API)
      .pipe(
        delay(1000),
        //tap(console.log)
      );
  }

  criarProduto(produto: Produto) {
    return this.http.post(this.API, produto).pipe(take(1));
  }

  atualizarProduto(produto) {
    return this.http.put(`${this.API}/${produto.id}`, produto).pipe(take(1));;
  }

  buscarProdutoPorId(idProduto) {
    return this.http.get(`${this.API}/${idProduto}`).pipe(take(1));
  }

}
