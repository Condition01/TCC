package br.com.ifeira.compra.controller;

import br.com.ifeira.compra.shared.dao.FeiraDAO;
import br.com.ifeira.compra.shared.dao.Persistivel;
import br.com.ifeira.compra.shared.entity.Feira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeiraController {

    private Persistivel<Feira, String> feiraDAO;

    public FeiraController(@Autowired JdbcTemplate jdbcTemplate) {
        this.feiraDAO = new FeiraDAO(jdbcTemplate);
    }

    public List<Feira> listarFeiras() {
        return this.feiraDAO.listar();
    }

    public Feira selecionarFeira(String feira){
        return this.feiraDAO.buscar(feira);
    }

}
