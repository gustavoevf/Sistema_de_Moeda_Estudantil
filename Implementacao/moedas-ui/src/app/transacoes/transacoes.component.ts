import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TransacaoModel } from '../shared/models/transacao.model';
import { TransacaoService } from '../shared/services/transacao.service';
import { ProfessorModel } from '../shared/models/professor.model';
import { AlunoModel } from '../shared/models/aluno.model';
import { AlunoService } from '../shared/services/aluno.service';
import { ProfessorService } from '../shared/services/professor.service';

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
  professores: Array<ProfessorModel>;
  alunos: Array<AlunoModel>;
  action: string = 'edit';
  title: string = '';
  form: FormGroup = new FormGroup({
    remetente: new FormControl({ value: '', disabled: this.action == 'view' }, Validators.required),
    destinatario: new FormControl({ value: '', disabled: this.action == 'view' }, Validators.required),
    valor: new FormControl({ value: '', disabled: this.action == 'view' }, Validators.min(0)),
    descricao: new FormControl({ value: '', disabled: this.action == 'view' }, Validators.required)
  });

  constructor(private transacaoService: TransacaoService,
    private alunoService: AlunoService,
    private professorService: ProfessorService,
    private router: Router) { }

  ngOnInit(): void {
    this.getTransacoes();
    this.getAlunos();
    this.getProfessores();
  }

  salvar() {
    if (this.form.invalid) {
      alert("Verifique o preenchimento dos campos");
    } else {
      switch (this.action) {
        case 'create':
          this.transacaoService.saveTransacao({
            ...this.form.value,
            remetente: { id: this.form.controls['remetente'].value.toString() },
            destinatario: { id: this.form.controls['destinatario'].value.toString() }
          }).then(resp => {
            this.switchAction();
            this.getTransacoes();
          }).catch(error => {
            alert("Saldo insuficiente");
            this.switchAction();
          });
          break;
        case 'edit':
          this.transacaoService.updateTransacao({
            ...this.form.value,
            remetente: { id: this.form.controls['remetente'].value.toString() },
            destinatario: { id: this.form.controls['destinatario'].value.toString() }
          }, this.transacaoSelecionada.id
          ).then(resp => {
            this.getTransacoes();
            this.switchAction();
          }).catch(error => {
            this.switchAction();
          });;
          break;

        default:
          break;
      }
    }
  }

  deletar(transacao: TransacaoModel) {
    this.transacaoService.deleteTransacao(transacao.id).then(
      resp => this.getTransacoes()
    ).catch(error =>
      this.getTransacoes()
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

  getTransacoes() {
    this.transacaoService.getAllTransacao().then(resp => {
      this.transacoes = resp.content;
    }).catch(error => {
      console.log(error);
    })
  }

  getAlunos() {
    this.alunoService.getAllAluno().then(resp => {
      this.alunos = resp.content;
    }).catch(error => {
      console.log(error);
    })
  }

  getProfessores() {
    this.professorService.getAllProfessor().then(resp => {
      this.professores = resp.content;
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
