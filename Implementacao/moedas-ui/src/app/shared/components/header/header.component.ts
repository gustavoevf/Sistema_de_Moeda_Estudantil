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
    this.tipoUsuario = this.globalService.tipoUsuario;
  }

}
