package br.com.Ifeira.compra.controller;

import br.com.Ifeira.compra.entity.shared.Feira;

import java.util.ArrayList;
import java.util.List;

public class FeiraController {
    List<Feira> listaFeira = new ArrayList<Feira>();

    public List<Feira> listarFeiras() {
        return listaFeira;
    }

    public Feira selecionarFeira(int feira){
        return listaFeira.get(feira);
    }
}
