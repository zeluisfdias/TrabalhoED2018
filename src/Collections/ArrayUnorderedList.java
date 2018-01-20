/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

/**
 *
 * @author Fábio Rêgo
 * @param <T>
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        if (super.isEmpty()) {
            this.array[0] = element;
            super.setCount(super.getCount() + 1);
        } else {
            System.arraycopy(this.array, 0, this.array, 1, super.size());
            this.array[0] = element;
            super.setCount(super.getCount() + 1);
        }
    }

    @Override
    public void addToRear(T element) {
        if (super.isEmpty()) {
            this.array[0] = element;
            super.setCount(super.getCount() + 1);
        } else {
            this.array[super.size()] = element;
            super.setCount(super.getCount() + 1);
        }
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, ElementNotFoundException {
        int index = 0;
        if (!super.contains(target)) {
            throw new ElementNotFoundException("O elemento nao existe");
        }
        for (int i = 0; i < super.size(); i++) {
            if (target.equals(super.getArray()[i])) {
                index = i;
            }
        }

        System.arraycopy(this.array, index, this.array, index + 1, super.size());
        this.array[index + 1] = (T) element;
        this.setCount(super.getCount() + 1);

    }
   

}

