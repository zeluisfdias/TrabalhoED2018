/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.ProcessedException;
import recursos.interfaces.IComida;
import recursos.interfaces.IProcessamento;


/**
 * Classe SalaProcessamnto é uma subclass de Sala que indica uma sala em que as formigas 
 * processam a comida, que funciona como uma linha de producao. Ou seja a nova comida é depositada no fim da linha de producao.
 * 
 * Não há limite de espaço. Quando uma formiga sai da Sala ou a proxima comida tem tamanho 1.
 * @author Zé Luís
 */
public class SalaProcessamento extends Sala implements IProcessamento{
    
    /**
     * Construtor para criar uma sala de processamento para o deposito de comida como linha de producao
     * em Queue, com a metodologia FIFO
     * @param Id
     * @param Descricao
     * @param x
     * @param y 
     */
    public SalaProcessamento(int Id, String Descricao, int x, int y) {
        super(Id, Descricao, x, y);
    }

    @Override
    public void acrescentaComida(IComida ic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IComida getProximaComida() throws EmptyCollectionException, ProcessedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<IComida> iteratorComida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
