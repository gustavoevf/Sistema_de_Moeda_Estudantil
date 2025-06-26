import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less'],
})
export class HeaderComponent implements OnInit {
  constructor(private readonly router: Router) {}

  tipoUsuario: string = '';
  usuario: any;

  ngOnInit(): void {
    this.usuario = localStorage.getItem('@User');
    if (this.usuario) {
      let tipo = JSON.parse(this.usuario).tipo;
      this.tipoUsuario = tipo;
      console.log(tipo);
    }
  }

  logout() {
    this.tipoUsuario = '';
    this.router.navigate(['login']);
    localStorage.clear();
  }
}
