import { EmpresaModel } from "./empresa.model"

export interface VantagemModel {
    descricao: string,
    fotoProduto: string
    custo: number,
    empresa: EmpresaModel
}