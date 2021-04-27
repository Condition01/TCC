package br.com.ifeira.compra.shared.dao;

import java.util.List;

public interface Persistivel<T> {
    T Salvar();
    List<T> listar();
    T buscar(T obj);
    T editar(T obj);
}
