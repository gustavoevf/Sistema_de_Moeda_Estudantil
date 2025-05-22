import { Injectable } from '@angular/core';
import { UsuarioModel } from '../models/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class GlobalService {
  usuario: UsuarioModel | null = null;
  tipoUsuario: string = '';
  constructor() {}
}
