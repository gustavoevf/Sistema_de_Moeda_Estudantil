import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { HttpClient } from '@angular/common/http';
import { LoginModel } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends BaseService {

  constructor(protected httpClient: HttpClient) {
    super(httpClient);
   }

  login(login: LoginModel) {
    return this.post("/usuario/login", login);
  }
}
