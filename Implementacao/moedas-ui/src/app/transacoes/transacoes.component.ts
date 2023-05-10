import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { TransacaoModel } from '../shared/models/transacao.model';
import { TransacaoService } from '../shared/services/transacao.service';

@Component({
  selector: 'app-transacoes',
  templateUrl: './transacoes.component.html',
  styleUrls: ['./transacoes.component.less']
})
export class TransacoesComponent implements OnInit {
  @ViewChild('closeButton') closeButton: any;

  showAction: boolean = false;
  transacoes: Array<TransacaoModel> = [];
  transacaoSelecionada: TransacaoModel;
  action: string = 'edit';
  title: string = '';
  form: FormGroup = new FormGroup({
    nome: new FormControl({ value: '', disabled: this.action == 'view' }),
    vantagens: new FormControl({ value: '', disabled: this.action == 'view' })
  });

  constructor(private transacaoService: TransacaoService,
    private router: Router) { }

  ngOnInit(): void {
    this.getTransacaos();
  }

  salvar() {
    switch (this.action) {
      case 'create':
        this.transacaoService.saveTransacao({...this.form.value, vantagens: this.form.controls['vantagens'].value.toString()}).then(resp => {
          this.switchAction();
          this.getTransacaos();
        }).catch(error => {
          this.switchAction();
        });
        break;
      case 'edit':
        this.transacaoService.updateTransacao({...this.form.value, vantagens: this.form.controls['vantagens'].value.toString()}, this.transacaoSelecionada.id).then(resp => {
          this.getTransacaos();
          this.switchAction();
        }).catch(error => {
          this.switchAction();
        });;
        break;

      default:
        break;
    }
  }

  deletar(transacao: TransacaoModel) {
    this.transacaoService.deleteTransacao(transacao.id).then(
      resp => this.getTransacaos()
    ).catch(error =>
      this.getTransacaos()
    )
  }

  setAction(action: string, transacao: TransacaoModel | null = null) {
    this.action = action;
    switch (action) {
      case 'view':
        this.title = 'Visualizar Transacao'
        if (transacao)
          this.transacaoSelecionada = transacao;
        this.showAction = true;
        break;

      case 'edit':
        this.title = 'Editar Transacao'
        if (transacao)
          this.transacaoSelecionada = transacao;
        this.showAction = true;
        break;

      case 'create':
        this.title = 'Incluir Transacao'
        this.showAction = true;
        break;

      default:
        break;
    }
  }

  getTransacaos() {
    this.transacaoService.getAllTransacao().then(resp => {
      this.transacoes = resp.content;
    }).catch(error => {
      console.log(error);
    })
  }

  switchAction() {
    this.showAction = false;
  }

  canSave(): boolean {
    return this.action !== 'view'
  }

}
