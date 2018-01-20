/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import recursos.interfaces.IComida;

/**
 * Classe @link Comida. Cada comida tem um identificador id uníco e um tamanho.
 * Uma comida com tamanho > n pode ser partida em n comidas de tamanho 1 numa
 * sala de Processamento
 *
 * @author Zé Luís
 */
public class Comida implements IComida, Serializable {

    private int ID;
    private int tamanhoComida;

    /**
     * Cria uma nova de instancia de comida com os dois atributos principais de caracterização de uma comida.
     * @param ID
     * @param tamanhoComida 
     */
    public Comida(int ID, int tamanhoComida) {
        this.ID = ID;
        this.tamanhoComida = tamanhoComida;
    }

    /**
     * Cria uma nova de instancia de comida com o id [CONSTRUTOR OPCIONAL PARA O CASO DE SER NECESSÁRIO]
     *
     * @param ID
     */
    public Comida(int ID) {
        this.ID = ID;
    }

    /**
     * Metodo de acesso para obter o atributo ID de uma comida
     *
     * @return
     */
    @Override
    public int getId() {
        return this.ID;
    }

    /**
     * Meotdo de acesso que atribui um novo id a uma dada comida
     * @param id 
     */
    @Override
    public void setId(int id) {
        this.ID = id;
    }

    /**
     * Metodo que obtem o tamanho de uma comida
     * @return 
     */
    @Override
    public int getTamanho() {
        return tamanhoComida;
    }

    /**
     * Metodo de acesso que atribui um novo valor ao tamanho de uma dada comida.
     * @param tamanho 
     */
    @Override
    public void setTamanho(int tamanho) {
        this.tamanhoComida = tamanho;
    }

}
