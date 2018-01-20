/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 *
 * @author Fábio Rêgo
 */
public class ArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    protected T[] array;
    private int count;
    private BasicIteratorArrayList bi;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];;
        this.count = 0;
    }

    public ArrayList(int inicialCapacity) {
        this.array = (T[]) new Object[inicialCapacity];;
        this.count = 0;
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("O array esta vazio");
        } else {
            T remove = this.array[0];
//            while (bi.hasNext() != false) {
//                arrayTemp[posicao] = (T) bi.next();
//                posicao++;
//                bi.next();
//            }
            for (int i = 0; i < this.size(); i++) {
                this.array[i] = this.array[i + 1];
            }
            this.array[this.size() - 1] = null;
            this.count--;
            //this.array = arrayTemp;
            return remove;
        }
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        int posicao = 0;
        T remove;
        if (this.isEmpty()) {
            throw new EmptyCollectionException("O array esta vazio");
        } else {
//            while (bi.hasNext() != false) {
//                posicao++;
//            }

            remove = this.last();
            this.array[this.size() - 1] = null;
            this.count--;

            return remove;
        }
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        int posicao = 0;
        T remover = null;
        // T[] arrayTemp = (T[]) new Object[this.size() - 1];
        boolean existe = false;
        if (this.isEmpty()) {
            throw new EmptyCollectionException("O array esta vazio");
        } else {
//            while (bi.next() != element) {
//                posicao++;
//                arrayTemp[posicao] = this.getArray()[posicao];
//                bi.next();
//                existe = true;
//            }
            for (int i = 0; i < this.size(); i++) {
                if (this.array[i] != element) {
                    posicao++;
                } else {
                    remover = this.array[posicao];
                    existe = true;
                }
            }
            if (existe == false) {
                throw new ElementNotFoundException("O elemento nao existe na coleccao");
            } else {
                //while (bi.hasNext()) {
//                    arrayTemp[posicao] = this.getArray()[posicao + 1];
//                    posicao++;
//                }
//                this.array = arrayTemp;

                for (int i = 0; i < this.size(); i++) {
                    this.array[i] = this.array[i + 1];
                }
                this.count--;
                return remover;
            }
        }
    }

    @Override
    public T first() {
        return this.getArray()[0];
    }

    @Override
    public T last() {
        return this.getArray()[this.size() - 1];
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException, ElementNotFoundException {
        boolean existe = false;
        if (this.isEmpty()) {
            throw new EmptyCollectionException("O array esta vazio");
        } else {
            if (this.first().equals(target) || this.last().equals(target)) {
                existe = true;
            }
//            while(bi.hasNext()){
//              if(bi.next().equals(target)){
//                  existe = true;
//              }
//              bi.next();
//            }
            for (int i = 0; i < this.size(); i++) {
                if (this.array[i].equals(target)) {
                    existe = true;
                }
            }
        }
        return existe;
    }

    @Override
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIteratorArrayList();
    }

    @Override
    public String toString() {
        return "ArrayList{" + "array=" + array + '}';
    }

    public void imprimir() {
        for (int i = 0; i < this.size(); i++) {
            System.out.printf(this.array[i] + "||");
        }
    }

    public class BasicIteratorArrayList<T> implements Iterator<T> {

        private int current;
        private int expectedCount;

        public BasicIteratorArrayList() {
            this.current = 0;
            this.expectedCount = getCount();
        }

        @Override
        public boolean hasNext() {
            if (this.expectedCount != getCount()) {
                throw new ConcurrentModificationException();
            }
            if (current < size()) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (this.expectedCount != getCount()) {
                throw new ConcurrentModificationException();
            }
            Object obj = getArray()[current];
            current++;
            return (T) obj;
        }

        @Override
        public void remove() {
            /* Object remove;
            if (this.expectedCount != getCount()) {
                throw new ConcurrentModificationException();
            }
            T element = (T) getArray()[current];
            try {
                remove = ArrayList.this.remove(element);
            } catch (EmptyCollectionException ex) {
                System.out.println("ERRO: A coleção está vazia! ");
            } catch (ElementNotFoundException ex) {
                System.out.println("ERRO: Elemento não encontrado! ");
            }
            this.expectedCount++;
        }*/

        }
    }
}

