/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 *
 * @author Fábio Rêgo
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root of the new tree
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }
    
    public ArrayBinaryTree(T[] element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree = element;
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree. Throws a NoSuchElementException if the specified target
     * element is not found in the binary tree.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the element is in the tree
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;
        for (int ct = 0; ct < count && !found; ct++) {
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }
        }
        if (!found) {
            throw new ElementNotFoundException("binary tree");
        }
        return temp;
    }

    @Override
    public T getRoot() {
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            T temp = find(targetElement);
            return true;
        } catch (ElementNotFoundException ex) {
            return false;
        }
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist);
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2, templist);
            }
        }
    }

    
    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        inorder(0, templist);
        return templist.iterator();
    }
    
    protected void PreOrder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                PreOrder(node * 2 + 1, templist);
                PreOrder((node + 1) * 2, templist);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        PreOrder(0, templist);
        return templist.iterator();
    }
    
    protected void PostOrder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                PostOrder((node * 2) + 1, templist);
                PostOrder((node + 1) * 2, templist);
                templist.addToRear(tree[node]);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        PostOrder(0, templist);
        return templist.iterator();
    }

    protected void LevelOrder(int node, ArrayUnorderedList<T> templist) throws EmptyCollectionException {
        CircularArrayQueue<T> queue = new CircularArrayQueue<>();
        try{
        if (node < tree.length){
            if(tree[node] != null){
                queue.enqueue(tree[node]);
                while(queue.isEmpty() == false){
                    T temp = queue.dequeue();
                    templist.addToRear(temp);
                    if(tree[node] != null){
                        LevelOrder(node + 1 , templist);
                    }
                }
            }
        }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Chegou ao fim do array!");
        }
    }
    
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        try {
            LevelOrder(0, templist);
        } catch (EmptyCollectionException ex) {
            System.out.println("Vazia");
        }
        return templist.iterator();}
}
