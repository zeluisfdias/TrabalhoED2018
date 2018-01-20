/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 *
 * @author Fábio Rêgo
 */
public class LinkedQueue<T> implements QueueADT<T>, Iterable<T> {

    public LinearNode front;
    public LinearNode rear;
    public int size;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        if (isEmpty()) {
            front = new LinearNode(element);
            rear = front;
            size++;
        } else {
            LinearNode temp = new LinearNode(element);
            rear.setNext(temp);
            rear = rear.getNext();
            size++;
        }
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A queue esta vazia");
        } else {
            T retorno = (T) front.getElement();
            front = front.getNext();
            size--;
            return retorno;
       }
        
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A queue esta vazia");
        }
        T retorno = (T) front.getElement();
        return retorno;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<>();
    }
    
    public class BasicIterator<T> implements Iterator<T>{

        @Override
        public boolean hasNext() {
            if(front != null)
                return true;
            return false;
        }

        @Override
        public T next() {
            Object retorno = front.getElement();
            front = front.getNext();
            return (T) retorno;
        }
        
    
}

}

