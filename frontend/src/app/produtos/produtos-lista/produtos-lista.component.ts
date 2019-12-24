import { Component, OnInit } from '@angular/core';
import { Observable, empty, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Produto } from '../produto';
import { ProdutosService } from './../produtos.service';
import { AlertModalService } from 'src/app/shared/alert-modal.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-produtos-lista',
  templateUrl: './produtos-lista.component.html',
  styleUrls: ['./produtos-lista.component.scss']
})
export class ProdutosListaComponent implements OnInit {

  produtos$: Observable<Produto[]>;
  error$ = new Subject<boolean>();

  constructor(
    private service: ProdutosService,
    private alertService: AlertModalService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.atualizar();
  }

  atualizar() {
    this.produtos$ = this.service.listarProdutos()
      .pipe(
        catchError(error => {
          console.error(error);
          this.handleError();
          return empty();
        })
      );
  }

  handleError() {
    this.alertService.showAlertDanger('Erro ao carregar produtos. Tente novamente mais tarde');
  }

  editar(idProduto: number) {
    this.router.navigate(['produtos/editar', idProduto]);
  }

  desativar() {

  }

}
