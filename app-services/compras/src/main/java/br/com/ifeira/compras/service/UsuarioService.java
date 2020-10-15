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

    public List<Usuario> listarUsuario(){
        return usuarioDao.findAll();
    }

    public Optional<Usuario> buscarUsuario(String cpf){
        return this.usuarioDao.findById(cpf);
    }


    public void inserirUsuario(Usuario usuario){
        usuarioDao.save(usuario);
        return;
    }

    public String deletarUsuario(Usuario usuario){
        usuarioDao.delete(usuario);
        return "usuario excluido com sucesso";
    }

    public Optional<Usuario> editarUsuario(Usuario usuario){
        usuarioDao.saveAndFlush(usuario);
        return usuarioDao.findById(usuario.getCpf());
    }
}
