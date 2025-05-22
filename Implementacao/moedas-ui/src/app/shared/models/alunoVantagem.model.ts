import { AlunoModel } from './aluno.model';
import { VantagemModel } from './vantagem.model';

export interface AlunoVantagemModel {
  id?: string;
  aluno: AlunoModel;
  vantagem: VantagemModel;
}
