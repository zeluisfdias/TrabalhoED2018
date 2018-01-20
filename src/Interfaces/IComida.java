/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Fábio Rêgo
 */
public class IComida implements recursos.interfaces.IComida{

    private int Id;
    private int tamanho;
    
    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int newId) {
          this.Id = newId;
    }

    @Override
    public int getTamanho() {
        return tamanho;
    }

    @Override
    public void setTamanho(int newTamanho) {
        this.tamanho =  newTamanho;
    }
    
}
