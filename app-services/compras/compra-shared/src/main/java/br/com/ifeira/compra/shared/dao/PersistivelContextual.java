package br.com.ifeira.compra.shared.dao;

import java.util.List;

public interface PersistivelContextual<T,K> {
    T salvar(T item, String contexto);
    List<T> listar(String contexto);
    T buscar(K key, String contexto);
    List<T> buscarMultiplos(K key, String contexto);
    T editar(T item);
}
