import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { VantagemModel } from '../models/vantagem.model';

@Injectable({
  providedIn: 'root'
})
export class VantagemService extends BaseService {

  constructor(protected httpClient: HttpClient) {
    super(httpClient);
   }

  saveVantagem(vantagem: VantagemModel) {
    return this.post("/vantagem", vantagem);
  }

  getAllVantagem() {
    return this.get("/vantagem");
  }

  getOneVantagem(id: string) {
    return this.get("/vantagem/" + id);
  }

  deleteVantagem(id: string) {
    return this.delete("/vantagem/" + id);
  }

  updateVantagem(vantagem: VantagemModel, id: string) {
    return this.put("/vantagem/" + id, vantagem);
  }
}