import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { ProfessorModel } from '../models/professor.model';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService extends BaseService {

  constructor(protected httpClient: HttpClient) {
    super(httpClient);
   }

  saveProfessor(professor: ProfessorModel) {
    return this.post("/professor", professor);
  }

  getAllProfessor() {
    return this.get("/professor");
  }

  getOneProfessor(id: string) {
    return this.get("/professor/" + id);
  }

  deleteProfessor(id: string) {
    return this.delete("/professor/" + id);
  }

  updateProfessor(professor: ProfessorModel, id: string) {
    return this.put("/professor/" + id, professor);
  }
}