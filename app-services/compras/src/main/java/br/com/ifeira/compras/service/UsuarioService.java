package br.com.ifeira.compras.service;

import br.com.ifeira.compras.dao.UsuarioDAO;
import br.com.ifeira.compras.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDao;

    public List<Usuario> listarUsuario() {
        return this.usuarioDao.findAll();
    }

    public Optional<Usuario> buscarUsuario(String cpf) {
        return this.usuarioDao.findById(cpf);
    }


    public void inserirUsuario(Usuario usuario) {
        this.usuarioDao.save(usuario);
    }

    public String deletarUsuario(Usuario usuario) {
        this.usuarioDao.delete(usuario);
        return "usuario excluido com sucesso";
    }

    public Optional<Usuario> editarUsuario(Usuario usuario) {
        this.usuarioDao.saveAndFlush(usuario);
        return usuarioDao.findById(usuario.getCpf());
    }
}
