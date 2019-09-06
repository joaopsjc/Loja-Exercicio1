/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ice
 */
public class Cliente implements Observer{
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setObservedProduto(Observable revistaObservada)
    {
        revistaObservada.addObserver(this);
    }

    public void gravar()
    {

    }
    public void alterar()
    {

    }
    public void excluir()
    {

    }
    public void update(Observable revistaSubject, Object arg1)
    {
        if(revistaSubject instanceof Produto)
        {
            Produto produto = (Produto) revistaSubject;
            System.out.println(getNome() + " o produto " + produto.getNome() + " mudou!!");
        }
    }
    
}
