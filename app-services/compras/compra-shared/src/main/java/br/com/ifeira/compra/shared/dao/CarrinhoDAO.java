package br.com.ifeira.compra.shared.dao;

import javafx.util.Pair;

import java.util.List;

public class CarrinhoDAO implements PersistivelContextual<Pair<Long, String>, String> {

    @Override
    public Pair<Long, String> salvar(Pair<Long, String> item, String contexto) {
        return null;
    }

    @Override
    public List<Pair<Long, String>> listar(String contexto) {
        return null;
    }

    @Override
    public Pair<Long, String> buscar(String key, String contexto) {
        return null;
    }

    @Override
    public List<Pair<Long, String>> buscarMultiplos(String key, String contexto) {
        return null;
    }

    @Override
    public Pair<Long, String> editar(Pair<Long, String> item) {
        return null;
    }

}
