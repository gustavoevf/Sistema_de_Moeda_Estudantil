import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AlunoModel } from '../models/aluno.model';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root',
})
export class AlunoService extends BaseService {
  constructor(protected httpClient: HttpClient) {
    super(httpClient);
  }

  saveAluno(aluno: AlunoModel) {
    return this.post('/aluno', aluno);
  }

  getAllAluno() {
    return this.get('/aluno');
  }

  getOneAluno(id: string) {
    return this.get('/aluno/' + id);
  }

  deleteAluno(id: string) {
    return this.delete('/aluno/' + id);
  }

  updateAluno(aluno: AlunoModel, id: string) {
    return this.put('/aluno/' + id, aluno);
  }
}
