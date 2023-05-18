import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { VantagemService } from '../shared/services/vantagem.service';
import { VantagemModel } from '../shared/models/vantagem.model';
import { GlobalService } from '../shared/services/global.service';

@Component({
  selector: 'app-vantagens',
  templateUrl: './vantagens.component.html',
  styleUrls: ['./vantagens.component.less']
})
export class VantagensComponent implements OnInit {
  @ViewChild('closeButton') closeButton: any;

  showAction: boolean = false;
  vantagens: Array<VantagemModel> = [];
  vantagemSelecionada: VantagemModel;
  action: string = 'edit';
  title: string = '';
  form: FormGroup = new FormGroup({
    descricao: new FormControl({ value: '', disabled: this.action == 'view' }),
    custo: new FormControl({ value: '', disabled: this.action == 'view' }),
    fotoProduto: new FormControl({ value: '', disabled: this.action == 'view' }),
  });

  constructor(private vantagemService: VantagemService,
    private router: Router,
    private globalService: GlobalService) { }

    user: any = {};

  ngOnInit(): void {
    this.getVantagens();
    let obLocalStorage = localStorage.getItem('@User');
    if(obLocalStorage){
      this.user = JSON.parse(obLocalStorage);
    }
  }

  salvar() {
    switch (this.action) {
      case 'create':
        
        let newObj = {...this.form.value, empresa: {id: this.user.id}}
        this.vantagemService.saveVantagem(newObj).then(resp => {
          this.switchAction();
          this.getVantagens();
        }).catch(error => {
          this.switchAction();
        });
        break;
      // case 'edit':
      //   this.vantagemService.updateVantagem({...this.form.value, vantagens: this.form.controls['vantagens'].value.toString()}, this.vantagemSelecionada.id).then(resp => {
      //     this.getVantagens();
      //     this.switchAction();
      //   }).catch(error => {
      //     this.switchAction();
      //   });;
      //   break;

      default:
        break;
    }
  }

  // deletar(vantagem: VantagemModel) {
  //   this.vantagemService.deleteVantagem(vantagem.id).then(
  //     resp => this.getEmpresas()
  //   ).catch(error =>
  //     this.getEmpresas()
  //   )
  // }

  setAction(action: string, vantagem: VantagemModel | null = null) {
    this.action = action;
    switch (action) {
      case 'view':
        this.title = 'Visualizar Vantagem'
        if (vantagem)
          this.vantagemSelecionada = vantagem;
        this.showAction = true;
        break;

      case 'edit':
        this.title = 'Editar Vantagem'
        if (vantagem)
          this.vantagemSelecionada = vantagem;
        this.showAction = true;
        break;

      case 'create':
        this.title = 'Incluir Vantagem'
        this.showAction = true;
        break;

      default:
        break;
    }
  }

  getVantagens() {
    this.vantagemService.getAllVantagem().then(resp => {
      this.vantagens = resp.content;
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
