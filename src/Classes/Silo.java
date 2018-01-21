/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Collections.LinearNode;
import Collections.LinkedStack;
import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.IComida;
import recursos.interfaces.ISilo;

/**
 * SubClasse Silo da Classe Pai Sala, usada para representar um Silo Usa os
 * atributos da Classe Pai Para o empilhamento de comida teremos que usar Stack
 * pois o Silo é uma estrutura vertical (Stack) que as formigas depositam comida
 * uma em cima das outra. Quando eliminam é a primeira do topo que sai. O silo
 * não tem limite de espaço
 *
 * FILO - First In First Out
 *
 * @author Zé Luís
 */
public class Silo extends Sala implements ISilo {

    /**
     * Estrutura de Dados responsável por armazenar a comida que as formigas
     * depositam no silo
     */
    private LinkedStack<IComida> stackComidas;

    public Silo(int Id, String Descricao, int x, int y) {
        super(Id, Descricao, x, y);
        this.stackComidas = new LinkedStack<>();
    }

    /**
     * Metodo responsavel por adicionar comida no silo, ou seja, o metodo
     * responsavel por guardar a comida na stack.
     *
     * @param ic
     */
    @Override
    public void guardaComida(IComida ic) {
        this.stackComidas.push(ic);
    }

    /**
     * Metodo responsavel por eliminar comida do silo, ou seja, o metodo
     * responsavel por eliminar comida da stack.
     *
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public IComida retiraComida() throws EmptyCollectionException {
        if (this.stackComidas.isEmpty()) {
            throw new EmptyCollectionException("A Stack encontra-se vazia no momentoz");
        } else {
            return (IComida) this.stackComidas.pop();
        }
    }

    @Override
    public Iterator<IComida> iteratorComida() {
        return new IteratorForSilo();
    }

    private class IteratorForSilo implements Iterator<IComida> {

        private LinearNode noTemp;

        public IteratorForSilo() {
            noTemp = stackComidas.getTopoLista();
        }

        @Override
        public boolean hasNext() {
            return noTemp != null;
        }

        @Override
        public IComida next() {
            if (!hasNext()) {
                throw new NullPointerException("Variavel nula!");
            }
            IComida item = (IComida) noTemp.getElement();
            this.noTemp= this.noTemp.getNext();
            return item;

        }

    }

}
