/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Exceptions.ElementNotFoundException;

/**
 *
 * @author Fábio Rêgo
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new binary search
     * tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds the specified object to the binary search tree in the appropriate
     * position according to its key value. Note that equal elements are added
     * to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element; //Forma de ter a certeza de que o elemento é comparavel (alternativa a excepção)
        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) {
                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else {
                        current = current.left;
                    }
                } else if (current.right == null) {
                    current.right = temp;
                    added = true;
                } else {
                    current = current.right;
                }
            }
        }
        count++;
    }

    /**
     * Returns a reference to a node that will replace the one specified for
     * removal. In the case where the removed node has two children, the inorder
     * successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        if ((node.left == null) && (node.right == null)) {
            result = null;
        } else if ((node.left != null) && (node.right == null)) {
            result = node.left;
        } else if ((node.left == null) && (node.right != null)) {
            result = node.right;
        } else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            if (node.right == current) {
                current.left = node.left;
            } else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }
        return result;
    }

    /**
     * Removes the first element that matches the specified target element from
     * the binary search tree and returns a reference to it. Throws a
     * ElementNotFoundException if the specified target element is not found in
     * the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        if (!isEmpty()) {
            if (((Comparable) targetElement).equals(root.element)) {
                result = root.element;
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;
                if (((Comparable) targetElement).compareTo(root.element) < 0) {
                    current = root.left;
                } else {
                    current = root.right;
                }
                while (current != null && !found) {
                    if (targetElement.equals(current.element)) {
                        found = true;
                        count--;
                        result = current.element;
                        if (current == parent.left) {
                            parent.left = replacement(current);
                        } else {
                            parent.right = replacement(current);
                        }
                    } else {
                        parent = current;
                        if (((Comparable) targetElement).compareTo(current.element) < 0) {
                            current = current.left;
                        } else {
                            current = current.right;
                        }
                    }
                }
//while
                if (!found) {
                    throw new ElementNotFoundException("binary search tree");
                }
            }
        }
// end outer if
        return result;
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException{
        int i = 0; 
        removeElement(targetElement);
        while(i != 1){
            try {
                removeElement(targetElement);
            } catch (ElementNotFoundException ex) {
                //ex = new ElementNotFoundException("Elementos eliminados");
                //break;
                i = 1;
            }
        }
    }

    @Override
    public T removeMin() {
        BinaryTreeNode<T> current = root;
        if (current.left != null) {
            while (current.left.left != null) {
                current = current.left;
            }
            T elementToReturn = current.left.element;
            if (current.left.right != null) {
                BinaryTreeNode<T> temp = current.left.right;
                current.left = temp;
            } else{
                current.left = null;
            }
            return elementToReturn;
        } else {
            T elementToReturn = current.element;
            root = root.right;
            return elementToReturn;
        }
    }

    @Override
    public T removeMax(){
        BinaryTreeNode<T> current = root;
        if (current.right != null) {
            while (current.right.right != null) {
                current = current.right;
            }
            T elementToReturn = current.right.element;
            if (current.right.left != null) {
                BinaryTreeNode<T> temp = current.right.left;
                current.right = temp;
            } else {
                current.right = null;
            }
            return elementToReturn;
        } else {
            T elementToReturn = current.element;
            root = root.left;
            return elementToReturn;
        }
    }

    @Override
    public T findMin() {
        BinaryTreeNode<T> current = root;
        while (current.left.left != null) {
            current = current.left;
        }
        return current.left.element;
    }

    @Override
    public T findMax() {
        BinaryTreeNode<T> current = root;
        while (current.right.right != null) {
            current = current.right;
        }
        return current.right.element;
    }
}
