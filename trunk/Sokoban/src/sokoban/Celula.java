/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

/**
 *
 * @author Renato
 */
public class Celula {
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

    public boolean temObjetivo() {
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
        if(temCaixote() && temObjetivo()){
            return "X";
        }
        if(temCaixote()){
            return "C";
        }
        if(temObjetivo()){
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

}
