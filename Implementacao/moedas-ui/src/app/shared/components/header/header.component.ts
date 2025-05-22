import { Component, OnInit } from '@angular/core';
import { GlobalService } from '../../services/global.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less'],
})
export class HeaderComponent implements OnInit {
  constructor(private globalService: GlobalService) {}

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
    window.location.href = '/login';
    localStorage.clear();
  }
}
