import { OnInit, Component } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Produto } from '../produto';
import { ProdutosService } from './../produtos.service';
import { AlertModalService } from 'src/app/shared/alert-modal.service';
import { ActivatedRoute } from '@angular/router';
import { map, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-produtos-form',
  templateUrl: './produtos-form.component.html',
  styleUrls: ['./produtos-form.component.scss']
})
export class ProdutosFormComponent implements OnInit {

  form: FormGroup;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private service: ProdutosService,
    private alertService: AlertModalService,
    private location: Location,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params
      .pipe(
        map((params: any) => params['id']),
        switchMap(idProduto => this.service.buscarProdutoPorId(idProduto))
      )
      .subscribe((produto: Produto) => this.preencherFormulario(produto));

    this.form = this.fb.group({
      id: [null],
      nome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      quantidade: [null, [Validators.required]],
      valor: [null, [Validators.required]],
      ativo: [null]
    });
  }

  submeter() {
    this.submitted = true;
    if (this.form.valid) {
      if (this.form.value.id) {
        this.service.atualizarProduto(this.preencherProduto()).subscribe(
          success => {
            this.alertService.showAlertSucess('Produto atualizado com sucesso!');
            this.location.back();
          },
          error => this.alertService.showAlertDanger('Erro ao atualizar produto, tente novamente!'),
          () => console.log('update completo')
        );
      } else {
        this.service.criarProduto(this.preencherProduto()).subscribe(
          success => {
            this.alertService.showAlertSucess('Produto criado com sucesso!');
            this.location.back();
          },
          error => this.alertService.showAlertDanger('Erro ao criar produto, tente novamente!'),
          () => console.log('request completo')
        );
      }
    }
  }

  preencherProduto() {
    let produto = <Produto>{};
    produto.id = this.form.value.id;
    produto.nome = this.form.value.nome;
    produto.quantidade = this.form.value.quantidade;
    produto.valor = this.form.value.valor;
    produto.ativo = true;

    return produto;
  }

  preencherFormulario(produto: Produto) {
    if (produto) {
      this.form.patchValue({
        id: produto.id,
        nome: produto.nome,
        quantidade: produto.quantidade,
        valor: produto.valor,
        ativo: produto.ativo
      });
    }
  }

  cancelar() {
    const submmitted = false;
    this.form.reset();
    this.location.back();
  }

  hasError(field: string) {
    return this.form.get(field).errors;
  }

}
