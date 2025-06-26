import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { EmpresaModel } from '../shared/models/empresa.model';
import { EmpresaService } from '../shared/services/empresa.service';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.less'],
})
export class EmpresaComponent implements OnInit {
  @ViewChild('closeButton') closeButton: any;

  showAction: boolean = false;
  empresas: Array<EmpresaModel> = [];
  empresaSelecionada: EmpresaModel;
  action: string = 'edit';
  title: string = '';
  form: FormGroup = new FormGroup({
    nome: new FormControl({ value: '', disabled: this.action == 'view' }),
    email: new FormControl({ value: '', disabled: this.action == 'view' }),
  });

  constructor(private readonly empresaService: EmpresaService) {}

  ngOnInit(): void {
    this.getEmpresas();
  }

  salvar() {
    switch (this.action) {
      case 'create':
        this.empresaService
          .saveEmpresa(this.form.value)
          .then(_resp => {
            this.switchAction();
            this.getEmpresas();
          })
          .catch(_error => {
            this.switchAction();
          });
        break;
      case 'edit':
        this.empresaService
          .updateEmpresa(this.form.value, this.empresaSelecionada.id)
          .then(_resp => {
            this.getEmpresas();
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

  deletar(empresa: EmpresaModel) {
    this.empresaService
      .deleteEmpresa(empresa.id)
      .then(_resp => this.getEmpresas())
      .catch(_error => this.getEmpresas());
  }

  setAction(action: string, empresa: EmpresaModel | null = null) {
    this.action = action;
    switch (action) {
      case 'view':
        this.title = 'Visualizar Empresa';
        if (empresa) this.empresaSelecionada = empresa;
        this.form.patchValue({
          nome: this.empresaSelecionada.nome,
          email: this.empresaSelecionada.email,
        });
        this.showAction = true;
        break;

      case 'edit':
        this.title = 'Editar Empresa';
        if (empresa) this.empresaSelecionada = empresa;
        this.form.patchValue({
          nome: this.empresaSelecionada.nome,
          email: this.empresaSelecionada.email,
        });
        this.showAction = true;
        break;

      case 'create':
        this.title = 'Incluir Empresa';
        this.showAction = true;
        break;

      default:
        break;
    }
  }

  getEmpresas() {
    this.empresaService
      .getAllEmpresa()
      .then(resp => {
        this.empresas = resp.content;
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
