import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AlunoModel } from '../shared/models/aluno.model';
import { AlunoService } from '../shared/services/aluno.service';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.less']
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
    instituicaoEnsino: new FormControl({ value: '', disabled: this.action == 'view' }),
    rg: new FormControl({ value: '', disabled: this.action == 'view' }),
    cpf: new FormControl({ value: '', disabled: this.action == 'view' }),
    email: new FormControl({ value: '', disabled: this.action == 'view' }),
    endereco: new FormControl({ value: '', disabled: this.action == 'view' })
  });

  constructor(private alunoService: AlunoService,
    private router: Router) { }

  ngOnInit(): void {
    this.getAlunos();
  }

  salvar() {
    switch (this.action) {
      case 'create':
        this.alunoService.saveAluno(this.form.value).then(resp => {
          this.switchAction();
          this.getAlunos();
        }).catch(error => {
          this.switchAction();
        });
        break;
      case 'edit':
        this.alunoService.updateAluno(this.form.value, this.alunoSelecionado.id).then(resp => {
          this.getAlunos();
          this.switchAction();
        }).catch(error => {
          this.switchAction();
        });;
        break;

      default:
        break;
    }
  }

  deletar(aluno: AlunoModel) {
    this.alunoService.deleteAluno(aluno.id).then(
      resp => this.getAlunos()
    ).catch(error =>
      this.getAlunos()
    )
  }

  setAction(action: string, aluno: AlunoModel | null = null) {
    this.action = action;
    switch (action) {
      case 'view':
        this.title = 'Visualizar Aluno'
        if (aluno)
          this.alunoSelecionado = aluno;
        this.showAction = true;
        break;

      case 'edit':
        this.title = 'Editar Aluno'
        if (aluno)
          this.alunoSelecionado = aluno;
        this.showAction = true;
        break;

      case 'create':
        this.title = 'Incluir Aluno'
        this.showAction = true;
        break;

      default:
        break;
    }
  }

  getAlunos() {
    this.alunoService.getAllAluno().then(resp => {
      this.alunos = resp.content;
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
