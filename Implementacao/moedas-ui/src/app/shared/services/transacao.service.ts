import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { TransacaoModel } from '../models/transacao.model';

@Injectable({
  providedIn: 'root'
})
export class TransacaoService extends BaseService {

  constructor(protected httpClient: HttpClient) {
    super(httpClient);
   }

  saveTransacao(transacao: TransacaoModel) {
    return this.post("/transacao", transacao);
  }

  getAllTransacao() {
    return this.get("/transacao");
  }

  getOneTransacao(id: string) {
    return this.get("/transacao/" + id);
  }

  deleteTransacao(id: string) {
    return this.delete("/transacao/" + id);
  }

  updateTransacao(transacao: TransacaoModel, id: string) {
    return this.put("/transacao/" + id, transacao);
  }

  buscarPorRemetente(id: string) {
    return this.get("/transacao/por-remetente/" + id)
  }

  buscarPorDestinatario(id: string) {
    return this.get("/transacao/por-destinatario/" + id)
  }
}