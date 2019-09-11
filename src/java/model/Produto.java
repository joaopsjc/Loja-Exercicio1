/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import persistence.ProdutoDAO;

/**
 *
 * @author ice
 */
public class Produto extends Observable{
    private String nome, estado;
    private Float valor;

    public Produto(String nome) {
        this.nome = nome;
        estado = null;
        valor = null;
    }

    public Produto(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
        valor = null;
    }
    
    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public Float getValor() {
        return valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public void gravar()
    {
        setChanged();
        notifyObservers();
    }
    public void alterarEstado() throws SQLException, ClassNotFoundException
    {
        ProdutoDAO.getInstance().updateEstado(this.getEstado(), this.getNome());
        
        List<Cliente> observadores = ProdutoDAO.getInstance().readObservers(this.getNome());

        observadores.forEach((observador) -> {
            this.addObserver(observador);
        });

        setChanged();
        notifyObservers();
    }
    public void excluir()
    {
        setChanged();
        notifyObservers();
    }
}
