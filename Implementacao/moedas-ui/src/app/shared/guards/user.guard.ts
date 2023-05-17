import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UsuarioService } from '../services/usuario.service';
import { GlobalService } from '../services/global.service';

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

        return true;

    }
}