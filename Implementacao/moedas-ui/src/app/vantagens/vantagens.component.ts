import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { VantagemService } from '../shared/services/vantagem.service';
import { VantagemModel } from '../shared/models/vantagem.model';
import { GlobalService } from '../shared/services/global.service';
import { AlunoVantagemModel } from '../shared/models/alunoVantagem.model';
import { AlunoModel } from '../shared/models/aluno.model';

@Component({
  selector: 'app-vantagens',
  templateUrl: './vantagens.component.html',
  styleUrls: ['./vantagens.component.less'],
})
export class VantagensComponent implements OnInit {
  @ViewChild('closeButton') closeButton: any;

  showAction: boolean = false;
  showObterVantagem: boolean = false;
  vantagens: Array<VantagemModel> = [];
  vantagensObtidas: Array<AlunoVantagemModel> = [];
  vantagemSelecionada: VantagemModel;
  action: string = 'edit';
  title: string = '';
  form: FormGroup = new FormGroup({
    descricao: new FormControl({ value: '', disabled: this.action == 'view' }),
    custo: new FormControl({ value: '', disabled: this.action == 'view' }),
    fotoProduto: new FormControl({
      value: '',
      disabled: this.action == 'view',
    }),
  });

  constructor(
    private vantagemService: VantagemService,
    private router: Router,
    private globalService: GlobalService
  ) {}

  user: any = {};

  ngOnInit(): void {
    let obLocalStorage = localStorage.getItem('@User');
    if (obLocalStorage) {
      this.user = JSON.parse(obLocalStorage);
    }
    this.getVantagens();
  }

  salvar() {
    switch (this.action) {
      case 'create':
        let newObj = { ...this.form.value, empresa: { id: this.user.id } };
        this.vantagemService
          .saveVantagem(newObj)
          .then(resp => {
            this.switchAction();
            this.getVantagens();
          })
          .catch(error => {
            this.switchAction();
          });
        break;

      default:
        break;
    }
  }

  setAction(action: string, vantagem: VantagemModel | null = null) {
    this.action = action;
    switch (action) {
      case 'view':
        this.title = 'Visualizar Vantagem';
        if (vantagem) this.vantagemSelecionada = vantagem;
        this.showAction = true;
        break;

      case 'edit':
        this.title = 'Editar Vantagem';
        if (vantagem) this.vantagemSelecionada = vantagem;
        this.showAction = true;
        break;

      case 'create':
        this.title = 'Incluir Vantagem';
        this.showAction = true;
        break;

      default:
        break;
    }
  }

  getVantagens() {
    if (this.user.tipo === 'Admin' || this.user.tipo === 'Aluno') {
      this.vantagemService
        .getAllVantagem()
        .then(resp => {
          this.vantagens = resp.content;
        })
        .catch(error => {
          console.log(error);
        });
    }
    if (this.user.tipo === 'Aluno') {
      this.vantagemService
        .alunoVantagemPorAluno(this.user.id)
        .then(resp => {
          this.vantagensObtidas = resp;
        })
        .catch(error => {
          console.log(error);
        });
    }

    if (this.user.tipo === 'Empresa') {
      this.vantagemService
        .buscarPorEmpresa(this.user.id)
        .then(resp => {
          this.vantagens = resp;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }

  obterVantagem(vantagem: VantagemModel) {
    let param: AlunoVantagemModel = {
      aluno: this.user as AlunoModel,
      vantagem: vantagem,
    };
    this.vantagemService
      .saveAlunoVantagem(param)
      .then(result => {
        location.reload();
      })
      .catch(error => {
        alert('Falha ao obter vantagem.');
      });
  }

  switchAction() {
    this.showAction = false;
  }

  canSave(): boolean {
    return this.action !== 'view';
  }
}
