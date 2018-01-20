/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Exceptions.ElementNotFoundException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.exceptions.EmptyCollectionException;
/**
 *
 * @author Fábio Rêgo
 */

/**
 * ArrayBinarySearchTree implements a binary search tree using an array.
 *
 * @param <T>
 */
public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T>
        implements BinarySearchTreeADT<T> {

    protected int height;
    protected int maxIndex;

    /**
     * Creates an empty binary search tree.
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Creates a binary search with the specified element as its root
     *
     * @param element the element that will become the root of the new tree
     */
    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    public void expandCapacity() {
        T[] newTree = (T[]) new Object[super.count * 2];
        System.arraycopy(newTree, 0, super.tree, 0, super.count);
        super.tree = newTree;
    }

    /**
     * Adds the specified object to this binary search tree in the appropriate
     * position according to its key value. Note that equal elements are added
     * to the right. Also note that the index of the left child of the current
     * index can be found by doubling the current index and adding 1. Finding
     * the index of the right child can be calculated by doubling the current
     * index and adding 2.
     *
     * @param element the element to be added to the search tree
     */
    public void addElement(T element) {
        if (tree.length < maxIndex * 2 + 3) {
            expandCapacity();
        }
        Comparable<T> tempelement = (Comparable<T>) element;
        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            while (!added) {
                if (tempelement.compareTo((tree[currentIndex])) < 0) {
                    /**
                     * go left
                     */
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else /**
                 * go right
                 */
                 if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;

    }

    protected void replacement(int targetIndex) throws EmptyCollectionException {

        int currentIndex, parentIndex, temp, oldIndex, newIndex;
        ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<Integer>();
        ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<Integer>();
        ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<Integer>();
        Iterator<Integer> oldIt, newIt;

        // se o target não tiver filhos
        if ((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length)) {
            tree[targetIndex] = null;
        } // se o target não tiver filhos
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null)) {
            tree[targetIndex] = null;
        } // se o target só tiver filho esquerdo
        else if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {

            // fill newlist with indices of nodes that will replace 
            // the corresponding indices in oldlist
            // fill newlist
            currentIndex = targetIndex * 2 + 1;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                oldlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // if target node only has a right child
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {

            // fill newlist with indices of nodes that will replace 
            // the corresponding indices in oldlist
            // fill newlist
            currentIndex = targetIndex * 2 + 2;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // fill oldlist
            currentIndex = targetIndex;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                oldlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // target node has two children
        else {
            currentIndex = targetIndex * 2 + 2;

            while (tree[currentIndex * 2 + 1] != null) {
                currentIndex = currentIndex * 2 + 1;
            }

            tree[targetIndex] = tree[currentIndex];

            // the index of the root of the subtree to be replaced
            int currentRoot = currentIndex;

            // if currentIndex has a right child
            if (tree[currentRoot * 2 + 2] != null) {

                // fill newlist with indices of nodes that will replace 
                // the corresponding indices in oldlist
                // fill newlist
                currentIndex = currentRoot * 2 + 2;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    newlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }

                // fill oldlist
                currentIndex = currentRoot;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    oldlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }

                // do replacement
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();
                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            } else {
                tree[currentRoot] = null;
            }
        }

    }

    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        boolean found = false;

        if (isEmpty()) {
            return result;
        }

        for (int i = 0; (i <= maxIndex) && !found; i++) {
            if ((tree[i] != null) && targetElement.equals(tree[i])) {
                found = true;
                result = tree[i];
                try {
                    replacement(i);
                } catch (EmptyCollectionException ex) {
                    //Coleção vazia
                }
                count--;
            }
        }
        if (!found) {
            throw new ElementNotFoundException("element not found in the binary tree");
        }

        int temp = maxIndex;
        maxIndex = -1;
        for (int i = 0; i <= temp; i++) {
            if (tree[i] != null) {
                maxIndex = i;
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        return result;
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException {
 //igual ao da lista ligada
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException{
        T result = null;
        if (isEmpty()) {
            throw new EmptyCollectionException("Coleção vazia!");
        } else {
            int currentIndex = 0;
            //int previousIndex = 0;
            while (tree[currentIndex] != null && currentIndex <= tree.length) {
               // previousIndex = currentIndex;
                currentIndex = currentIndex * 2 + 1;
            }
            result = tree[currentIndex];
            replacement(currentIndex);
        }
        count--;
        return result;
        /*int parent = 0, current = 0;
        if (tree[current * 2 + 1] != null) {
            while (tree[current] != null) {
                parent = current;
                current = current * 2 + 1;
            }
            if (tree[parent * 2 + 2] == null) {
                T tmp = tree[parent];
                tree[parent] = null;
                count--;
                return tmp;
            } else {
                int tempIndex = parent * 2 + 2;
                // current = current * 2 + 2;
                T tmp = tree[parent];
                tree[parent] = tree[tempIndex];
                tree[tempIndex] = null;
                count--;
                return tmp;
            }
        } else {
            T temp = tree[current];
            while (tree[current] != null) {
                tree[current] = tree[2 * (current + 1)];
                current = 2 * (current + 1);
            }
            count--;
            return temp;
        }
         */
    }

    @Override
    public T removeMax() throws EmptyCollectionException {
        T result = null;

        if (isEmpty()) {
            throw new EmptyCollectionException("binary tree");
        } else {
            int currentIndex = 2;
            //int previousIndex = 0;
            while (tree[currentIndex] != null && currentIndex <= maxIndex) {
              //  previousIndex = currentIndex;
                currentIndex = currentIndex * 2 + 2;
            } 
            result = tree[currentIndex];
            replacement(currentIndex);
        } 

        count--;

        return result;
        /*int parent = 0, current = 0;
        if (tree[2 * (current + 1)] != null) {
            while (tree[current] != null) {
                parent = current;
                current = current * 2 + 2;
            }
            if (tree[parent * 2 + 1] == null) {
                T tmp = tree[parent];
                tree[parent] = null;
                count--;
                return tmp;
            } else {
                int tempIndex = parent * 2 + 1;
                // current = current * 2 + 2;
                T tmp = tree[parent];
                tree[parent] = tree[tempIndex];
                tree[tempIndex] = null;
                count--;
                return tmp;
            }
        } else {
            T temp = tree[current];
            while (tree[current] != null) {
                if (tree[((2 * current) + 1)] != null) {//Verificar se o lado esquerdo é null
                    tree[current] = tree[2 * current + 1];
                    current = 2 * current + 1;
                } else { //Lado direito preeenchido, com lado esquerdo == null
                    tree[current] = tree[2 * (current + 1)];
                }
            }
            count--;
            return temp;
        }*/
    }

    @Override
    public T findMin() {
        int parent = 0, current = 0;
        while (tree[current] != null) {
            parent = current;
            current = current * 2 + 1;
        }
        if (tree[parent * 2 + 2] == null) {
            T tmp = tree[parent];
            return tmp;
        } else {
            int tempIndex = parent * 2 + 2;
            current = current * 2 + 2;
            T tmp = tree[parent];
            return tmp;
        }
    }

    @Override
    public T findMax() {
        int parent = 0, current = 0;
        while (tree[current] != null) {
            parent = current;
            current = current * 2 + 2;
        }
        if (tree[parent * 2 + 1] == null) {
            T tmp = tree[parent];
            return tmp;
        } else {
            int tempIndex = parent * 2 + 1;
            //current = current * 2 + 2;
            T tmp = tree[parent];
            return tmp;
        }
    }
}

