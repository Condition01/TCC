package br.com.ifeira.compra.shared.dao;

import java.util.List;

public interface Persistivel {
    public Object Salvar();
    public List<Object> listar();
    public Object buscar(Object obj);
    public Object editar(Object obj);
}
