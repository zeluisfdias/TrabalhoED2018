/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Exceptions.EmptyCollectionException;

/**
 *
 * @author Fábio Rêgo
 * @param <T>
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    private T[] arrayQueue;
    private final int DEFAULT_CAPACITY = 100;
    private int front;
    private int rear;
    private int count;

    public CircularArrayQueue() {
        this.arrayQueue = (T[]) new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = 0;
    }

    public CircularArrayQueue(int inicialCapacity) {
        this.arrayQueue = (T[]) new Object[inicialCapacity];
        this.front = 0;
        this.rear = 0;
    }

    public void expandCapacity() {
        T[] newArray = (T[]) new Object[size() * 2];
        for (int i = 0; i < arrayQueue.length; i++) {
            newArray[i] = arrayQueue[i];
        }
        arrayQueue = newArray;
    }

    @Override
    public void enqueue(T element) {
        if (count == arrayQueue.length) {
            System.out.println("Array cheio"); //falta exceçao ou expandir capacidade
        }
        arrayQueue[rear] = element;
        count++;
        rear = (rear + 1) % arrayQueue.length;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("O array está vazio!");
        } else {
            T temp = arrayQueue[front];
            arrayQueue[front] = null;
            front = (front + 1) % arrayQueue.length;
            count--;
            return temp;
        }
    }

    @Override
    public T first() throws EmptyCollectionException {
        T retorno = arrayQueue[front];
        return retorno;
    }

    @Override
    public boolean isEmpty() {
        if (count == 0) 
            return true;
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        int i = 0;
        int x = front;
        while (i < count) {
            System.out.println(arrayQueue[x]);
            x = (x + 1) % arrayQueue.length;
            i++;
        }
        return null;
    }
}
