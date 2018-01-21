/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.StackADT;

/**
 * A classe LinkedStack usada para representar a estrutura de dados Stack responsável por FILO - First in First Out 
 * Esta classe implementa a Interface @link StackADT disponibilizada pelo professor
 * @author Zé Luís
 * @param <T>
 */
public class LinkedStack<T> implements StackADT<T> {

    private LinearNode<T> topoLista; //referencia ao elemento que esta no topo da stack
    private int size; // um silo não tem limite de espaço

   
    /**
     * Construtor que cria uma stack com um tamanho e um elemento do top já definidos inicialmente
     * @param topoLista no do topo da stack
     * @param size  tamanho da stack que estamos a criar no momento
     */
    public LinkedStack(LinearNode<T> topoLista, int size) {
        this.topoLista = topoLista;
        this.size = size;
    }

    /**
     * Construtor que cria uma stack sem elementos, ou seja vazia inicialmente.
     * A unica maneira de adicionar é com recurso ao metodo @link push()
     */
    public LinkedStack() {
        this.topoLista = null;
        this.size = 0;
    }
    
     
    public LinearNode<T> getTopoLista() {
        return topoLista;
    }

    public void setTopoLista(LinearNode<T> topoLista) {
        this.topoLista = topoLista;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
    
    
    /**
     * Método responsavel por adicionar um elemento no topo de uma stack
     * @param t 
     */
    @Override
    public void push(T t) {
      
        LinearNode<T> nodeTemp = new LinearNode<>(t);
        
        if(isEmpty()) { //verifica se a stack está vazia 
            topoLista = nodeTemp;
            size++;
        }else { //senão estiver vazia entao dá adiciona o novo valor a stack, atribuindo o seguinte nó como o antigo topo da lista 
            nodeTemp.setNext(topoLista);
            topoLista = nodeTemp; //atribui como topo da lista o novo que se adicionou
            size++; //aumenta-se o tamanho da stack
        }
        
    }

    /**
     * Remove um dado no da stack
     * @return
     * @throws EmptyCollectionException 
     */
    @Override
    public T pop() throws EmptyCollectionException {
       if(isEmpty()) {
           throw  new EmptyCollectionException("A stack encontra-se vazia no momemento");
       }else {
           T element = topoLista.getElement();
           topoLista.setElement(topoLista.getNext().getElement()); 
           size--;
           return element;
       }
    }

    /**
     * Rwtorna o elemento do topo da stack sem o remover
     * @return
     * @throws EmptyCollectionException 
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException("A stack encontra-se vazia no momento");
        }else {
             return topoLista.getElement();
        }
    }

    @Override
    public boolean isEmpty() {
        return (size() ==0);
    }

    @Override
    public int size() {
       return this.size;
    }
    
}
