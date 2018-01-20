/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Collections.ArrayUnorderedList;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.IFormiga;
import recursos.interfaces.ISala;
import recursos.interfaces.collections.UnorderedListADT;

/**
 * Classe responsavel pela Gestão de Salas no Formigueiro
 *
 * @author Zé Luís
 */
public class Sala implements ISala {

    private int Id;
    private String Descricao;
    private int x;
    private int y;

    private ArrayUnorderedList formigasSala; //array de formigas na sala

    /**
     * Construtor que cria uma nova sala, com os varios atributos associados a
     * classe @link Sala
     *
     * @param Id identificador da sala
     * @param Descricao descricao da sala
     * @param x coordenada x no plano bidimensional
     * @param y coordenada y no plano bidimensional
     */
    public Sala(int Id, String Descricao, int x, int y) {
        this.Id = Id;
        this.Descricao = Descricao;
        this.x = x;
        this.y = y;
        this.formigasSala = new ArrayUnorderedList();
    }
//Metodos de acesso as variaveis privadas da classe

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int Id) {
        this.Id = Id;
    }

    @Override
    public String getDescricao() {
        return Descricao;
    }

    @Override
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    // Metodos de Gestão das Estruturas de Dados
    /**
     * Acrescenta uma formiga a lista de formigas presentes nesta sala
     *
     * @param newFormiga Objecto Formiga
     */
    @Override
    public void entraFormiga(IFormiga newFormiga) {
        formigasSala.addToFront(newFormiga); //adiciona a nova formiga ao array de formigas
    }

    /**
     * Retira uma formiga das várias formigas existentes nesta sala
     *
     * @param i posicao da formiga a eliminar da sala
     * @return formiga que saiu
     * @throws EmptyCollectionException lancada esta excepcao se nao existem
     * nenhuma formiga na sala
     * @throws ElementNotFoundException lancada esta excepcao se nao existe a
     * formiga que foi passada como parametro
     */
    @Override
    public IFormiga saiFormiga(int i) throws EmptyCollectionException, ElementNotFoundException {
        Formiga formiga_ExistInst = null;
        Iterator iterator_FormigasRoom = formigasSala.iterator();

        if (iterator_FormigasRoom.hasNext()) {
            formiga_ExistInst = (Formiga) iterator_FormigasRoom.next();

            if (formiga_ExistInst.getId() == i) {
                formigasSala.remove(formiga_ExistInst);
                return formiga_ExistInst;
            } else {
                throw new ElementNotFoundException("A formiga não existe");
            }
        } else {
            throw new Exceptions.EmptyCollectionException("Não existem formigas para remover");
        }

    }

    /**
     * Retorna a lista de formigas existentes numa sala
     *
     * @return unorderedlist com todas a formigas existentes na sala.
     */
    @Override
    public UnorderedListADT<IFormiga> listaFormigas() {
        return null;
    }

}
