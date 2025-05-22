import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UsuarioService } from '../shared/services/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less'],
})
export class LoginComponent {
  form: FormGroup = new FormGroup({
    login: new FormControl(),
    senha: new FormControl(),
  });

  constructor(private readonly usuarioService: UsuarioService) {}

  entrar() {
    this.usuarioService
      .login(this.form.value)
      .then(result => {
        localStorage.setItem('@User', JSON.stringify(result));
        location.reload();
      })
      .catch(_error => {
        alert('Falha ao realizar login. Verifique usuário e senha.');
      });
  }
}
