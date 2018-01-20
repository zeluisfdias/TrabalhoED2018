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
/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 *
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary
     * tree
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    public LinkedBinaryTree(BinaryTreeNode<T> element, int nEle) {
        count = nEle;
        root = element;
    }

    @Override
    public T getRoot() {
        return root.element;
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
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null) {
            return false;
        }
        return true;
    }

    @Override
    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree. Throws a NoSuchElementException if the specified target
     * element is not found in the binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null) {
            throw new ElementNotFoundException("binary tree");
        }
        return (current.element);
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement,
            BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }
        if (next.element.equals(targetElement)) {
            return next;
        }
        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }
        return temp;
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node,
            LinkedList<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addElemento(node.element);
            inorder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        LinkedList<T> tempList = new LinkedList<>();
        inorder(root, tempList);
        return tempList.iterator();
    }

    protected void PreOrder(BinaryTreeNode<T> node,
            LinkedList<T> tempList) {
        if (node != null) {
            tempList.addElemento(node.element);
            PreOrder(node.left, tempList);
            PreOrder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        LinkedList<T> tempList = new LinkedList<>();
        PreOrder(root, tempList);
        return tempList.iterator();
    }

    protected void PostOrder(BinaryTreeNode<T> node,
            LinkedList<T> tempList) {
        if (node != null) {
            PostOrder(node.left, tempList);
            PostOrder(node.right, tempList);
            tempList.addElemento(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        LinkedList<T> tempList = new LinkedList<>();
        PostOrder(root, tempList);
        return tempList.iterator();
    }

    protected void levelOrder(BinaryTreeNode<T> node,
            LinkedQueue<T> Queue) throws EmptyCollectionException {

        LinkedQueue<T> tempQueue = Queue;
        if (tempQueue.front == null) {
            tempQueue.enqueue(node.element);
        }
        if (node != null) {
            if (node.left != null) {
                tempQueue.enqueue(node.left.element);
            }
            if (node.right != null) {
                tempQueue.enqueue(node.right.element);
            }

            levelOrder(node.left, tempQueue);
            levelOrder(node.right, tempQueue);
        }
}

@Override
        public Iterator<T> iteratorLevelOrder() {
        LinkedQueue<T> tempQueue = new LinkedQueue<>();
        try {
            levelOrder(root, tempQueue);
        } catch (EmptyCollectionException ex) {
            ex = new EmptyCollectionException("Queue vazia!");
        }
        return tempQueue.iterator();
    }

    @Override
        public String toString() {
        return "LinkedBinaryTree{" + "root=" + root + '}';
    }

}
