import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TransacaoModel } from '../shared/models/transacao.model';
import { TransacaoService } from '../shared/services/transacao.service';
import { AlunoModel } from '../shared/models/aluno.model';
import { AlunoService } from '../shared/services/aluno.service';

@Component({
  selector: 'app-transacoes',
  templateUrl: './transacoes.component.html',
  styleUrls: ['./transacoes.component.less'],
})
export class TransacoesComponent implements OnInit {
  @ViewChild('closeButton') closeButton: any;

  showAction: boolean = false;
  transacoes: Array<TransacaoModel> = [];
  transacaoSelecionada: TransacaoModel;
  alunos: Array<AlunoModel>;
  action: string = 'edit';
  title: string = '';
  form: FormGroup = new FormGroup({
    destinatario: new FormControl(
      { value: '', disabled: this.action == 'view' },
      Validators.required
    ),
    valor: new FormControl(
      { value: '', disabled: this.action == 'view' },
      Validators.min(0)
    ),
    descricao: new FormControl(
      { value: '', disabled: this.action == 'view' },
      Validators.required
    ),
  });

  user: any = {};

  constructor(
    private readonly transacaoService: TransacaoService,
    private readonly alunoService: AlunoService
  ) {}

  ngOnInit(): void {
    let obLocalStorage = localStorage.getItem('@User');
    if (obLocalStorage) {
      this.user = JSON.parse(obLocalStorage);
    }
    this.getTransacoes();
    this.getAlunos();
  }

  salvar() {
    if (this.form.invalid) {
      alert('Verifique o preenchimento dos campos');
    } else {
      switch (this.action) {
        case 'create':
          this.transacaoService
            .saveTransacao({
              ...this.form.value,
              remetente: { id: this.user.id },
              destinatario: {
                id: this.form.controls['destinatario'].value.toString(),
              },
            })
            .then(() => {
              this.switchAction();
              this.getTransacoes();
            })
            .catch(() => {
              alert('Saldo insuficiente');
              this.switchAction();
            });
          break;
        case 'edit':
          this.transacaoService
            .updateTransacao(
              {
                ...this.form.value,
                remetente: { id: this.user.id },
                destinatario: {
                  id: this.form.controls['destinatario'].value.toString(),
                },
              },
              this.transacaoSelecionada.id
            )
            .then(() => {
              this.getTransacoes();
              this.switchAction();
            })
            .catch(() => {
              this.switchAction();
            });
          break;

        default:
          break;
      }
    }
  }

  deletar(transacao: TransacaoModel) {
    this.transacaoService
      .deleteTransacao(transacao.id)
      .then(() => this.getTransacoes())
      .catch(() => this.getTransacoes());
  }

  setAction(action: string, transacao: TransacaoModel | null = null) {
    this.action = action;
    switch (action) {
      case 'view':
        this.title = 'Visualizar Transacao';
        if (transacao) this.transacaoSelecionada = transacao;
        this.showAction = true;
        break;

      case 'edit':
        this.title = 'Editar Transacao';
        if (transacao) this.transacaoSelecionada = transacao;
        this.showAction = true;
        break;

      case 'create':
        this.title = 'Incluir Transacao';
        this.showAction = true;
        break;

      default:
        break;
    }
  }

  getTransacoes() {
    this.transacaoService
      .buscarPorRemetente(this.user.id)
      .then(resp => {
        this.transacoes = resp;
      })
      .catch(error => {
        console.log(error);
      });
  }

  getAlunos() {
    this.alunoService
      .getAllAluno()
      .then(resp => {
        this.alunos = resp.content;
      })
      .catch(error => {
        console.log(error);
      });
  }

  switchAction() {
    this.showAction = false;
  }

  canSave(): boolean {
    return this.action !== 'view';
  }
}
