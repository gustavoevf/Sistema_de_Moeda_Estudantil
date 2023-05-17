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
    return this.post("/transacao", vantagem);
  }

  getAllVantagem() {
    return this.get("/transacao");
  }

  getOneVantagem(id: string) {
    return this.get("/transacao/" + id);
  }

  deleteVantagem(id: string) {
    return this.delete("/transacao/" + id);
  }

  updateVantagem(vantagem: VantagemModel, id: string) {
    return this.put("/transacao/" + id, vantagem);
  }
}