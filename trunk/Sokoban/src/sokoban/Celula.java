/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Renato
 */
public class Celula implements Cloneable{
    private int x;
    private int y;
    private boolean agente = false;
    private boolean parede = false;
    private boolean caixote = false;
    private boolean objetivo = false ;

    public Celula(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Celula clone(){
        try {
            return (Celula) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    public boolean temAgente() {
        return agente;
    }

    public void setAgente(boolean agente) {
        this.agente = agente;
    }

    public boolean temCaixote() {
        return caixote;
    }

    public void setCaixote(boolean caixote) {
        this.caixote = caixote;
    }

    public boolean isObjetivo() {
        return objetivo;
    }

    public void setObjetivo(boolean objetivo) {
        this.objetivo = objetivo;
    }

    public boolean temParede() {
        return parede;
    }

    public void setParede(boolean parede) {
        this.parede = parede;
    }

    /*
     *  V, para uma posição vazia;
     *  P, para uma posição ocupada por uma parede;
     *  A, para uma posição ocupada pelo agente;
     *    O, para uma posição objetivo;
     *    C, para uma posição que não seja uma posição objetivo, ocupada por um caixote;
     *  X, para uma posição objetivo ocupada por um caixote.
     */
    @Override
    public String toString() {
        if(temParede()){
            return "P";
        }
        if(temAgente()){
            return "A";
        }
        if(temCaixote() && isObjetivo()){
            return "X";
        }
        if(temCaixote()){
            return "C";
        }
        if(isObjetivo()){
            return "O";
        }
        return "V";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean estaVazia(){
        return !temParede() && !temCaixote() && !temAgente();
    }

}
