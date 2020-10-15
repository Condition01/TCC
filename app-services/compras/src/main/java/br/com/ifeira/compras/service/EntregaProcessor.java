package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.UsuarioDAO;
import br.com.ifeira.compras.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class EntregaProcessor{
    @Autowired
    private UsuarioDAO usuarioDAO;
}
