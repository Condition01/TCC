package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoDAO produtoDAO;
}
