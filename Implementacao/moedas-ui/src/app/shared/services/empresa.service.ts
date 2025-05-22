import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmpresaModel } from '../models/empresa.model';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root',
})
export class EmpresaService extends BaseService {
  constructor(protected httpClient: HttpClient) {
    super(httpClient);
  }

  saveEmpresa(empresa: EmpresaModel) {
    return this.post('/empresa', empresa);
  }

  getAllEmpresa() {
    return this.get('/empresa');
  }

  getOneEmpresa(id: string) {
    return this.get('/empresa/' + id);
  }

  deleteEmpresa(id: string) {
    return this.delete('/empresa/' + id);
  }

  updateEmpresa(empresa: EmpresaModel, id: string) {
    return this.put('/empresa/' + id, empresa);
  }
}
