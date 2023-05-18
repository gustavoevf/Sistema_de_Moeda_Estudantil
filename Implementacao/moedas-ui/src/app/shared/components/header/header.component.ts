import { Component, OnInit } from '@angular/core';
import { GlobalService } from '../../services/global.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  constructor(private globalService: GlobalService) { }

  tipoUsuario: string = '';

  ngOnInit(): void {
    let objUser = localStorage.getItem('@User');
    if(objUser){
      let tipo = JSON.parse(objUser).tipo;
      this.tipoUsuario = tipo;
      console.log(tipo)
    }
    
  }

  logout(){
    window.location.href = "/login";
    localStorage.clear();
  }

}
