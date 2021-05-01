package br.com.ifeira.auth.service;

import br.com.ifeira.auth.entity.Pessoa;
import br.com.ifeira.auth.enums.Role;
import br.com.ifeira.auth.enums.Situacao;
import br.com.ifeira.auth.dtos.PessoaDTO;
import br.com.ifeira.auth.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioDAO usuarioDAO;

    public PessoaDTO cadastroUsuario(PessoaDTO pessoaDTO) {
        if(usuarioExiste(pessoaDTO.getCpf())){
            throw new DuplicateKeyException("Usuário já existe!!");
        }else {
            Pessoa pessoa = Pessoa.fromDTO(pessoaDTO);
            pessoa.getEndereco().setUsuario(pessoa);
            pessoa.setRole(Role.CLIENTE);
            pessoa.setSitucao(Situacao.HABILITADO);
            pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
            return PessoaDTO.fromPessoa(usuarioDAO.save(pessoa));
        }
    }

    public PessoaDTO alterarUsuario(PessoaDTO pessoaDTO) {
        Pessoa pessoaAlterado = Pessoa.fromDTO(pessoaDTO);
        Optional<Pessoa> optUsuario = this.usuarioDAO.findUsuarioByCpf(pessoaAlterado.getCpf());
        if(optUsuario.isPresent()){
            Pessoa pessoa = optUsuario.get();
            pessoa.setEmail(pessoaAlterado.getEmail());
            pessoa.setDataNasc(pessoaAlterado.getDataNasc());
            pessoa.setNome(pessoaAlterado.getNome());
            pessoa.setSobrenome(pessoaAlterado.getSobrenome());
            return PessoaDTO.fromPessoa(usuarioDAO.save(pessoa));
        }else{
            throw new UsernameNotFoundException("Não se pode alterar um usuario não presente");
        }
    }

    public PessoaDTO pegarUsuarioCpf(String cpf) {
        Optional<Pessoa> userOp = usuarioDAO.findUsuarioByCpf(cpf);
        if(userOp.isPresent()){
            return PessoaDTO.fromPessoa(userOp.get());
        }else{
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

    public PessoaDTO pegarUsuarioEmail(String email) {
        Optional<Pessoa> userOp = usuarioDAO.findUsuarioByEmail(email);
        if(userOp.isPresent()){
            return PessoaDTO.fromPessoa(userOp.get());
        }else{
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

    public boolean usuarioExiste(String cpf) {
        Optional<String> cpfOption = this.usuarioDAO.findCpf(cpf);
        return cpfOption.isPresent();
    }
}
