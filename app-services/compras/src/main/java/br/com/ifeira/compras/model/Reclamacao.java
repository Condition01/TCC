package br.com.ifeira.compras.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_reclamacao")
public class Reclamacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_reclamacao")
    private Long idReclamacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_pedido", referencedColumnName = "numero")
    private Pedido pedido;

    @ElementCollection
    private List<String> reclamacao;

    public Reclamacao(Long idReclamacao, Pedido pedido, List<String> reclamacao) {
        this.idReclamacao = idReclamacao;
        this.pedido = pedido;
        this.reclamacao = reclamacao;
    }

    public Reclamacao() {
    }

    public Long getIdReclamacao() {
        return idReclamacao;
    }

    public void setIdReclamacao(Long idReclamacao) {
        this.idReclamacao = idReclamacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<String> getReclamacao() {
        return reclamacao;
    }

    public void setReclamacao(List<String> reclamacao) {
        this.reclamacao = reclamacao;
    }
}
