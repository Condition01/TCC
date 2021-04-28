package br.com.ifeira.compra.shared.dao;

import java.util.List;

public interface Persistivel<T, K> {

    T salvar(T item);
    List<T> listar();
    T buscar(K key);
    T editar(T item);

}
