package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.EntregaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {
    @Autowired
    private EntregaDAO entregaDAO;
}
