import { Injectable } from '@angular/core';
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserGuard implements CanActivate {
  constructor(private readonly router: Router) {}

  loggedPages: Array<string> = ['aluno', 'transacoes', 'empresa', 'vantagens'];

  canActivate(
    route: ActivatedRouteSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    let objUser = localStorage.getItem('@User');
    let tipo = '';
    if (objUser) {
      tipo = JSON.parse(objUser).tipo;
    }

    if (this.loggedPages.some(x => x.includes(route.url.toString())) && !tipo) {
      this.router.navigate(['login']);
      return false;
    }

    if (route.url.toString() == 'login' && objUser) {
      switch (tipo) {
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
