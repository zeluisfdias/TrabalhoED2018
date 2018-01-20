/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import recursos.interfaces.IComida;

/**
 *
 * @author Fábio Rêgo
 */
public class ProcessedException extends recursos.exceptions.ProcessedException {

    public ProcessedException(IComida comida) {
        super(comida);
    }

   
}
