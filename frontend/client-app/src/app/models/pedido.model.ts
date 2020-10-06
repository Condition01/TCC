import { Pagamento } from './pagamento.mode';
import { ProdutoQuantidade } from './produto-quantidade';

export class Pedido {
    produtoQuantidade: ProdutoQuantidade
    pagamento: Pagamento;
}