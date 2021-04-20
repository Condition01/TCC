package br.com.Ifeira.compra.dao.shared;

import java.util.List;

public interface Persistivel {
    public Object Salvar();
    public List<Object> listar();
    public Object buscar(Object obj);
    public Object editar(Object obj);
}
