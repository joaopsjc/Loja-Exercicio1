/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;

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
    public void alterar()
    {
        setChanged();
        notifyObservers();
    }
    public void excluir()
    {
        setChanged();
        notifyObservers();
    }
}
