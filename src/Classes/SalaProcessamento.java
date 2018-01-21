/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Collections.LinearNode;
import Collections.LinkedQueue;
import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.ProcessedException;
import recursos.interfaces.IComida;
import recursos.interfaces.IProcessamento;

/**
 * Classe SalaProcessamnto é uma subclass de Sala que indica uma sala em que as
 * formigas processam a comida, que funciona como uma linha de producao. Ou seja
 * a nova comida é depositada no fim da linha de producao.
 *
 * Não há limite de espaço. Quando uma formiga sai da Sala ou a proxima comida
 * tem tamanho 1.
 *
 * @author Zé Luís
 */
public class SalaProcessamento extends Sala implements IProcessamento {

    private LinkedQueue<IComida> queueComida;

    /**
     * Construtor para criar uma sala de processamento para o deposito de comida
     * como linha de producao em Queue, com a metodologia FIFO
     *
     * @param Id
     * @param Descricao
     * @param x
     * @param y
     */
    public SalaProcessamento(int Id, String Descricao, int x, int y) {
        super(Id, Descricao, x, y);
        this.queueComida = new LinkedQueue<>();
    }

    /**
     * Metodo responsavel por acrescentar comida a uma linha de producao,
     * representada por uma Queue.
     *
     * @param ic
     */
    @Override
    public void acrescentaComida(IComida ic) {
        this.queueComida.enqueue(ic);
    }

    /**
     * Metodo responsavel pela continuidade da producao na Sala de Processamento
     *
     * @return
     * @throws EmptyCollectionException lancada quando nao existe nenhuma comida
     * na linha de produxcao
     * @throws ProcessedException
     */
    @Override
    public IComida getProximaComida() throws EmptyCollectionException, ProcessedException {
        int i = 0;

        if (this.queueComida.first().getTamanho() == 1) { // vai verificar se o tamanho da comida da primeira posicao  é de 1
            //  throw new ProcessedException(queueComida.first());
            return (IComida) this.queueComida.dequeue();
        } else {
            int sizeComida = this.queueComida.first().getTamanho();
            this.queueComida.dequeue();

            while (i != sizeComida) {
                IComida comida = new Comida(i, 1);
                acrescentaComida(comida);
            }

        }

        return null;

        }

    @Override
    public Iterator<IComida> iteratorComida() {
        return new IteratorForProcessamento();
    }

    private class IteratorForProcessamento implements Iterator<IComida> {

        private LinearNode noQueue;

        public IteratorForProcessamento() {
            noQueue = queueComida.getRear();
        }

        @Override
        public boolean hasNext() {
            return (noQueue != null);
        }

        @Override
        public IComida next() {
            if (noQueue == null) {
                throw new NullPointerException("O nó encontra-se nulo");
            }

            noQueue = noQueue.getNext();
            IComida noTemporario = (IComida) noQueue.getElement();

            if (noQueue == queueComida.getRear()) {
                noQueue = null;
            }

            return noTemporario;
        }

    }

}
