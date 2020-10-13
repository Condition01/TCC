package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDao;

}
