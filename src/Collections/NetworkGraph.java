/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Collections.ArrayUnorderedList;
import Collections.LinkedQueue;
import Collections.LinkedStack;
import java.io.Serializable;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.ISala;
import recursos.interfaces.ITunel;
import recursos.interfaces.collections.NetworkADT;

/**
 *
 * @author Zé Luís
 * @param <T>
 */
public class NetworkGraph<T> extends Graph<T> implements NetworkADT<T>, Serializable {

    ITunel[][] auxMatrix;
    ISala[] vertexSalas;

    public NetworkGraph() {

        this.numVertices = 0;
        this.auxMatrix = new ITunel[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ITunel[][] getAuxMatrix() {
        return auxMatrix;
    }

    public void setAdjMatrix(ITunel[][] auxMatrix) {
        this.auxMatrix = auxMatrix;
    }

    /**
     * Adiciona um trajeto entre dois vertices
     *
     * @param t
     * @param t1
     * @param itunel
     * @throws ElementNotFoundException
     */
    @Override
    public void addEdge(T t, T t1, ITunel itunel) throws ElementNotFoundException {
        if (indexIsValid(getIndex(t)) && indexIsValid(getIndex(t1))) {
            this.auxMatrix[getIndex(t)][getIndex(t1)] = itunel;
            this.auxMatrix[getIndex(t1)][getIndex(t)] = itunel;
        }
    }

    /**
     * Calcula o peso do caminho mais curto
     *
     * @param t
     * @param t1
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public double shortestPathWeight(T t, T t1) throws ElementNotFoundException {
        double cost = 0;

        Iterator it = super.iteratorShortestPath(t, t1);

        while (it.hasNext()) {
            if (auxMatrix[getIndex(t)][getIndex(t1)] != null) {
                cost += auxMatrix[getIndex(t)][getIndex(t1)].getDistance();
            }
        }
        return cost;
    }

    /**
     * Adiciona um vertice, ou no contexto do trabalho uma sala
     *
     * @param vertex
     */
    @Override
    public void addVertex(T vertex) {
        if (this.numVertices == this.vertices.length) {
            super.expandCapacity();

        }
        this.vertices[this.numVertices] = vertex;
        for (int i = 0; i <= this.numVertices; i++) {
            this.auxMatrix[this.numVertices][i] = new Tunel(i);
            this.auxMatrix[i][this.numVertices] = new Tunel(i);
        }
        this.numVertices++;
    }

    /**
     * Remove um vertice, ou no contexto do trabalho uma sala
     *
     * @param t
     * @throws ElementNotFoundException
     */
    @Override
    public void removeVertex(T t) throws ElementNotFoundException {
        if (isEmpty()) {
            throw new ElementNotFoundException("Network vazia!");
        }
        for (int i = 0; i < numVertices; i++) {
            if (t.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * Insere uma aresta entre os dois vértices especificados.
     *
     * @param t o primeiro vértice
     * @param t1 o segundo vértice
     */
    @Override
    public void addEdge(T t, T t1) {
        addEdge(getIndex(t), getIndex(t1));
    }

    /**
     * Devolve o índice do vértice especificado.
     *
     * @param vertex o vértice cujo índice se vai devolver
     * @return o índice do vértice especificado
     */
    public int getIndex(T vertex) {
        for (int i = 0; i < size(); i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Insere uma aresta entre dois vértices.
     *
     * @param index1 o primeiro índice
     * @param index2 o segundo índice
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;

        }
    }

    /**
     * Remove a aresta entre os dois vértices especificados. Lança uma execao se
     * o Graph estiver vazio.
     *
     * @param t o primeiro vértice
     * @param t1 o segundo vértice
     * @throws Exceptions.ElementNotFoundException
     */
    @Override
    public void removeEdge(T t, T t1) throws Exceptions.ElementNotFoundException {
        if (isEmpty()) {
            throw new Exceptions.ElementNotFoundException("Graph vazio!");
        }
        removeEdge(getIndex(t), getIndex(t1));
    }

    /**
     * Remove uma aresta entre dois vértices.
     *
     * @param index1 o índice do primeiro vértice
     * @param index2 o índice do segundo vértice
     * @throws Exceptions.ElementNotFoundException
     */
    public void removeEdge(int index1, int index2) throws Exceptions.ElementNotFoundException {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    /**
     * Devolve um iterador que irá realizar a travessia em largura (depth-first
     * traversal), iniciando-a no vértice especificado. Lança uma Execao se o
     * Graph estiver vazio.
     *
     * @param t o vértice onde irá começar a travessia
     * @return um iterador de travessia em largura, iniciado no vértice
     * especificado
     */
    @Override
    public Iterator iteratorBFS(T t) {
        T value;
        LinkedQueue<T> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(getIndex(t))) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(t);
        visited[getIndex(t)] = true;

        while (!traversalQueue.isEmpty()) {
            value = traversalQueue.dequeue();
            resultList.addToRear(this.vertices[getIndex(value)]);
            /**
             * Encontrar todos os vértices adjacentes a x que ainda não foram
             * visitados e adicioná-los à queue
             */
            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[getIndex(value)][i] && !visited[i]) {
                    traversalQueue.enqueue(getVertices()[i]);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Devolve um iterador que irá realizar a travessia em profundidade
     * (depth-first traversal), iniciando-a no indice especificado.
     *
     * @param t o índice onde irá começar a travessia
     * @return um iterador de travessia em profundidade, iniciado no índice
     * especificado
     */
    public Iterator iteratorDFS(T t) {

        T value;
        boolean encontrado;

        LinkedStack<T> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[this.numVertices];

        if (!indexIsValid(getIndex(t))) {
            return resultList.iterator();
        }
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(t);
        resultList.addToRear(this.vertices[getIndex(t)]);
        visited[getIndex(t)] = true;

        while (!traversalStack.isEmpty()) {
            value = traversalStack.peek();
            encontrado = false;
            /* Encontrar um vértice adjacente a x que ainda não tenha sido
             * visitado e adicioná-lo à stack */
            for (int i = 0; (i < this.numVertices) && !encontrado; i++) {
                if (this.adjMatrix[getIndex(value)][i] && !visited[i]) {
                    traversalStack.push(getVertices()[i]);
                    resultList.addToRear(this.vertices[i]);
                    visited[i] = true;
                    encontrado = true;
                }
            }
            if (!encontrado && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * Devolve um iterador com os índices dos vértices que estão no caminho mais
     * curto entre os dois vértices especificados. Lança uma Execao se o Graph
     * estiver vazio.
     *
     * @param startIndex o índice inicial
     * @param targetIndex o índice alvo
     * @return um iterador com os índices dos vértices que estão no caminho mais
     * curto entre os dois vértices especificados
     * @throws Exceptions.ElementNotFoundException
     */
    protected Iterator iteratorShortestPathIndices(int startIndex,
            int targetIndex) throws Exceptions.ElementNotFoundException {

        int index = startIndex;
        int[] pathLength = new int[numVertices];
        int[] predecessor = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<Integer> resultList
                = new ArrayUnorderedList<Integer>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex)) {
            index = (traversalQueue.dequeue()).intValue();

            /* Actualizar o comprimento do caminho no índice actual por cada
             vértice adjacente ao vértice x. */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        if (index != targetIndex) {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        index = targetIndex;
        stack.push(new Integer(index));
        do {
            index = predecessor[index];
            stack.push(new Integer(index));
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear(((Integer) stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * Devolve um iterador que contém o caminho mais curto entre os dois
     * vértices especificados. Lança uma execao se o Graph estiver vazio.
     *
     * @param t o vértice inicial
     * @param t1 o vértice final
     * @return o iterador que contém o caminho mais curto entre dois vértices
     */
    @Override
    public Iterator iteratorShortestPath(T t, T t1) {
        T value;

        LinkedQueue<T> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultsList = new ArrayUnorderedList<>();

        if (!indexIsValid(getIndex(t))) {
            return resultsList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }
        traversalQueue.enqueue(t);
        visited[getIndex(t)] = true;

        while (!traversalQueue.isEmpty()) {
            value = traversalQueue.dequeue();
            resultsList.addToRear(vertices[getIndex(value)]);

            //Encontra todos os vertices adjacentes a x que nao tenham sido visitados e adiciona a queue
            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[getIndex(value)][i] && !visited[i]) {
                    traversalQueue.enqueue(getVertices()[i]);
                    visited[i] = true;
                }
            }
        }
        return resultsList.iterator();
    }

    /**
     * Devolve true se o Graph estiver vazio e false em caso contrário.
     *
     * @return true se o Graph estiver vazio
     */
    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    /**
     * Devolve true se o Graph for conexo e false caso contrário. Lança uma
     * execao se o Graph estiver vazio.
     *
     * @return true se o Graph for conexo
     */
    @Override
    public boolean isConnected() {
        boolean isConnected = false;
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                isConnected = this.adjMatrix[i][j] == true;
            }
        }
        return isConnected;
    }

    /**
     * Devolve o numero de vertices do Graph.
     *
     * @return o numero de vertices do Graph
     */
    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public T[] getVertices() {
        return this.vertices;
    }

    /**
     *
     * Metodo Set e Get da Class Graph
     */
    public int getNumVertices() {
        return this.numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

}
