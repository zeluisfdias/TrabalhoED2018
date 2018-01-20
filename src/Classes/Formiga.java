/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Zé Luís
 */
import Collections.ArrayUnorderedList;
import java.io.Serializable;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.FormigaCheiaException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;

public class Formiga implements IFormiga, Serializable {

    /**
     * Chave de identificação de uma dada formiga 
     */
    private int idFormiga;
    /**
     * Atributo indicativo da capacidade de carga de uma dada formiga.
     */
    private int capacidadeCarga;
    /**
     * Lista Desordenada de comidas de uma dada formiga
     */
    private ArrayUnorderedList comidasFormiga;

    
    /**
     * Construtor que permite criar uma dada formiga, atribuindo lhe os parametros seguintes
     * @param idFormiga identificador
     * @param capacidadeCarga  capacidade máxima de comida que pode carregar
     */
    public Formiga(int idFormiga, int capacidadeCarga) {
        this.idFormiga = idFormiga;
        this.capacidadeCarga = capacidadeCarga;
    }

    public ArrayUnorderedList getComidasFormiga() {
        return comidasFormiga;
    }

    public void setComidasFormiga(ArrayUnorderedList comidasFormiga) {
        this.comidasFormiga = comidasFormiga;
    }

    /**
     * Metodo de acesso para obter o id da formiga
     *
     * @return
     */
    @Override
    public int getId() {
        return this.idFormiga;
    }

    /**
     * Metodo de acesso para definir um novo id para a formiga
     *
     * @param i
     */
    @Override
    public void setId(int i) {
        this.idFormiga = i;
    }

    /**
     * Metodo de acesso para obter a capacidade de carga de uma formiga
     *
     * @return
     */
    @Override
    public int getCapacidadeCarga() {
        return this.capacidadeCarga;
    }

    /**
     * Metodo de acesso para definir uma nova capacidade carga para a formiga
     *
     * @param i
     */
    @Override
    public void setCapacidadeCarga(int i) {
        this.capacidadeCarga = i;
    }

    /**
     * Adiciona uma nova comida a formiga
     *
     * @param ic comida para adicionar
     * @throws FormigaCheiaException quando a formiga nao tem espaço
     */
    @Override
    public void addComida(IComida ic) throws FormigaCheiaException {
        Comida comida = (Comida) ic;
        if (this.capacidadeCarga > this.comidasFormiga.size()) {
            this.comidasFormiga.addToFront(comida);
        } else {
            System.out.println("Formiga encontra-se cheia");
        }
    }

    /**
     * Metodo para remover uma determinada comida da formiga
     *
     * @param posicao
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    @Override
    public IComida removeComida(int posicao) throws EmptyCollectionException, ElementNotFoundException {
        IComida comidaInst;
        Iterator iterador = this.comidasFormiga.iterator();

        /*
           Verifica se tem um proximo elemento
         */
        if (iterador.hasNext()) {
            comidaInst = (IComida) iterador.next();
            if (comidaInst.getId() == posicao) {
                this.comidasFormiga.remove(comidaInst); //remove o elemento da lista
                return comidaInst;
            } else {
                throw new ElementNotFoundException("Elemento nao encontrado");
            }
        } else {
            throw new EmptyCollectionException("Elemento nao encontrado");
        }

    }

    /**
     * Metodo para remover um qualquer comida da formiga.
     *
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public IComida removeComida() throws EmptyCollectionException {
        IComida comidaInst;
        Iterator iterador = this.comidasFormiga.iterator();

        /*
           Verifica se tem um proximo elemento
         */
        if (iterador.hasNext()) {
            comidaInst = (IComida) iterador.next();

            this.comidasFormiga.removeFirst(); //remove o elemento da lista
            return comidaInst;

        } else {
            throw new EmptyCollectionException("Elemento nao encontrado");
        }

    }

    /**
     * Obtem o tamanho do vetor de comidas de formigas.
     * @return 
     */
    @Override
    public int getCarga() {
        return comidasFormiga.size();
    }

}
