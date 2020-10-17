import { Produto } from './produto.model';

export interface CarrinhoShow {
    produto: Produto
    quantidade: number
    valor?: number
}

// export interface CarrinhoView {
    // id: string,
    // item: string,
    // quantidade: 
// }