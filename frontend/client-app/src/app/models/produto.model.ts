export interface Produto {
    codProduto: number
    nome: string
    tp_Produto: TipoProduto
    descricao: string
    preco: number
    imgUrl: string
}

export enum TipoProduto {
    FRUTA, VERDURA, LEGUMES
}