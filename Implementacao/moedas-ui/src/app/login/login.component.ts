import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '../shared/services/usuario.service';
import { GlobalService } from '../shared/services/global.service';
import { UsuarioModel } from '../shared/models/usuario.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  form: FormGroup = new FormGroup({
    login: new FormControl({ value: '' }),
    senha: new FormControl({ value: '' })
  });

  constructor(
    private router: Router,
    private usuarioService: UsuarioService,
    private globalService: GlobalService
  ) { }

  ngOnInit(): void {
    this.router.navigate(['aluno']);
  }

  entrar() {
    this.usuarioService.login(this.form.value).then(result => {
      this.globalService.usuario = result as UsuarioModel;
    }).catch(error => {
      alert(error);
    })
  }

}
