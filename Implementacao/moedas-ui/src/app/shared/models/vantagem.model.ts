import { EmpresaModel } from "./empresa.model"

export interface VantagemModel {
    id: string,
    descricao: string,
    fotoProduto: string
    custo: number,
    empresa: EmpresaModel
}