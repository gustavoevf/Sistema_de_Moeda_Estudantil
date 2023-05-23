import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UsuarioService } from '../services/usuario.service';
import { GlobalService } from '../services/global.service';
import { UsuarioModel } from '../models/usuario.model';

@Injectable({
    providedIn: 'root'
})
export class UserGuard implements CanActivate {
    constructor(private router: Router, private globalService: GlobalService) { }

    loggedPages: Array<string> = [
        'aluno',
        'transacoes',
        'empresa',
        'vantagens'
    ]

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (this.loggedPages.some(x => x.includes(route.url.toString())) && !this.globalService.usuario) {
            this.router.navigate(['login']);
            return false;
        }

        let objUser = localStorage.getItem('@User');
        let tipo = '';
        if(objUser){
            tipo = JSON.parse(objUser).tipo;
        }
        if(route.url.toString() == 'login' && objUser) {
            switch(tipo) {
                case 'Aluno':
                  this.router.navigate(['vantagens']);
                  break;
                case 'Professor':
                  this.router.navigate(['transacoes']);
                  break;
                case 'Empresa':
                  this.router.navigate(['vantagens']);
                  break;
                case 'Admin':
                  this.router.navigate(['aluno']);
              }
        }

        return true;

    }
}