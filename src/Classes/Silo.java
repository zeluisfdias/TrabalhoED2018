/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.IComida;
import recursos.interfaces.ISilo;

/**
 *
 * @author Zé Luís
 */
public class Silo extends Sala implements ISilo {

    public Silo(int Id, String Descricao, int x, int y) {
        super(Id, Descricao, x, y);
    }

    @Override
    public void guardaComida(IComida ic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IComida retiraComida() throws EmptyCollectionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<IComida> iteratorComida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
