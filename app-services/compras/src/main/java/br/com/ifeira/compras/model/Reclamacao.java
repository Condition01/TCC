package br.com.ifeira.compras.model;

import br.com.ifeira.compras.model.Pedido;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Entity
public class Reclamacao {

    @id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long IdReclamacao;
    private Pedido pedido;
    private List<String> listaComentarios = new ArrayList<String>();

}
