import { UsuarioModel } from './usuario.model';

export interface TransacaoModel {
  id: string;
  valor: number;
  descricao: string;
  remetente: UsuarioModel;
  destinatario: UsuarioModel;
}
