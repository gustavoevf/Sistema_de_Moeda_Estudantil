import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AlunoModel } from '../shared/models/aluno.model';
import { AlunoService } from '../shared/services/aluno.service';
import { GlobalService } from '../shared/services/global.service';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.less'],
})
export class AlunoComponent implements OnInit {
  @ViewChild('closeButton') closeButton: any;

  showAction: boolean = false;
  alunos: Array<AlunoModel> = [];
  alunoSelecionado: AlunoModel;
  action: string = 'edit';
  title: string = '';
  form: FormGroup = new FormGroup({
    nome: new FormControl({ value: '', disabled: this.action == 'view' }),
    curso: new FormControl({ value: '', disabled: this.action == 'view' }),
    instituicaoEnsino: new FormControl({
      value: '',
      disabled: this.action == 'view',
    }),
    rg: new FormControl({ value: '', disabled: this.action == 'view' }),
    cpf: new FormControl({ value: '', disabled: this.action == 'view' }),
    email: new FormControl({ value: '', disabled: this.action == 'view' }),
    endereco: new FormControl({ value: '', disabled: this.action == 'view' }),
  });

  constructor(
    private readonly alunoService: AlunoService,
    private readonly globalService: GlobalService
  ) {}

  ngOnInit(): void {
    this.getAlunos();
    console.log(this.globalService.usuario);
  }

  salvar() {
    switch (this.action) {
      case 'create':
        this.alunoService
          .saveAluno({
            ...this.form.value,
            login: this.form.controls['nome'].value,
          })
          .then(_resp => {
            this.switchAction();
            this.getAlunos();
          })
          .catch(_error => {
            this.switchAction();
          });
        break;
      case 'edit':
        this.alunoService
          .updateAluno(
            { ...this.form.value, login: this.form.controls['nome'] },
            this.alunoSelecionado.id
          )
          .then(_resp => {
            this.getAlunos();
            this.switchAction();
          })
          .catch(_error => {
            this.switchAction();
          });
        break;

      default:
        break;
    }
  }

  deletar(aluno: AlunoModel) {
    this.alunoService
      .deleteAluno(aluno.id)
      .then(() => this.getAlunos())
      .catch(() => this.getAlunos());
  }

  setAction(action: string, aluno: AlunoModel | null = null) {
    this.action = action;
    switch (action) {
      case 'view':
        this.title = 'Visualizar Aluno';
        if (aluno) this.alunoSelecionado = aluno;
        this.form.patchValue({
          nome: this.alunoSelecionado?.nome,
          curso: this.alunoSelecionado?.curso,
          instituicaoEnsino: this.alunoSelecionado?.instituicaoEnsino,
          rg: this.alunoSelecionado?.rg,
          cpf: this.alunoSelecionado?.cpf,
          email: this.alunoSelecionado?.email,
          endereco: this.alunoSelecionado?.endereco,
        });
        this.showAction = true;
        break;

      case 'edit':
        this.title = 'Editar Aluno';
        if (aluno) this.alunoSelecionado = aluno;
        this.form.patchValue({
          nome: this.alunoSelecionado?.nome,
          curso: this.alunoSelecionado?.curso,
          instituicaoEnsino: this.alunoSelecionado?.instituicaoEnsino,
          rg: this.alunoSelecionado?.rg,
          cpf: this.alunoSelecionado?.cpf,
          email: this.alunoSelecionado?.email,
          endereco: this.alunoSelecionado?.endereco,
        });
        this.showAction = true;
        break;

      case 'create':
        this.title = 'Incluir Aluno';
        this.showAction = true;
        break;

      default:
        break;
    }
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
