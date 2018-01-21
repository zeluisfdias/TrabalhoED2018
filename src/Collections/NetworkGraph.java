/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.ITunel;
import recursos.interfaces.collections.NetworkADT;

/**
 *
 * @author Zé Luís
 */
public class NetworkGraph implements NetworkADT{

    /**
     * Adiciona um trajeto entre dois vertices 
     * @param t 
     * @param t1
     * @param itunel
     * @throws ElementNotFoundException 
     */
    @Override
    public void addEdge(Object t, Object t1, ITunel itunel) throws ElementNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Calcula o peso do caminho mais curto
     * @param t
     * @param t1
     * @return
     * @throws ElementNotFoundException 
     */
    @Override
    public double shortestPathWeight(Object t, Object t1) throws ElementNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Adiciona um vertice, ou no contexto do trabalho uma sala
     * @param t 
     */
    @Override
    public void addVertex(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Remove um vertice, ou no contexto do trabalho uma sala
     * @param t
     * @throws ElementNotFoundException 
     */
    @Override
    public void removeVertex(Object t) throws ElementNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * Adiciona um trajeto entre dois pontos 
     * @param t
     * @param t1
     * @throws ElementNotFoundException 
     */
    @Override
    public void addEdge(Object t, Object t1) throws ElementNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Remove um trajeto entre dois pontos
     * @param t
     * @param t1
     * @throws ElementNotFoundException 
     */
    @Override
    public void removeEdge(Object t, Object t1) throws ElementNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Iterador BFS 
     * @param t
     * @return 
     */
    @Override
    public Iterator iteratorBFS(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iteratorDFS(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iteratorShortestPath(Object t, Object t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] getVertices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
