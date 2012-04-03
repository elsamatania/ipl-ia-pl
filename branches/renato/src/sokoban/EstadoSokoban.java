/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Estado;
import agente.Operador;
import java.awt.Point;
import java.util.*;
import main.PuzzleListener;

/**
 *
 * @author Renato
 */
public final class EstadoSokoban extends Estado implements Cloneable{

    private Celula[][] matriz;
    private Celula posicaoAgente;
    private Set<Celula> celulasCaixotes;

    private EstadoSokoban() {
        
    }
    
    public EstadoSokoban(char[][] tabela) {
        matriz = new Celula[tabela.length][tabela[0].length];
        celulasCaixotes = new HashSet<Celula>(4);
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[0].length; j++) {
                switch(tabela[i][j]){
                    case 'P':
                        matriz[i][j] = new Celula(i, j, true, false);
                        break;
                    case 'O':
                        matriz[i][j] = new Celula(i, j, false, true);
                        break;
                    case 'C':
                        matriz[i][j] = new Celula(i, j, false, false);
                        matriz[i][j].setCaixote(true);
                        celulasCaixotes.add(matriz[i][j]);
                        break;
                    case 'X':
                        matriz[i][j] = new Celula(i, j, false, true);
                        matriz[i][j].setCaixote(true);
                        celulasCaixotes.add(matriz[i][j]);
                        break;
                    case 'A':
                        matriz[i][j] = new Celula(i, j, false, false);
                        matriz[i][j].setAgente(true);
                        posicaoAgente = matriz[i][j];
                        break;
                    default:
                        matriz[i][j] = new Celula(i, j, false, false);
                        
                }
            } 
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstadoSokoban other = (EstadoSokoban) obj;
        return equals(other);
    }
    
    public boolean equals(EstadoSokoban other){
        if (!Arrays.deepEquals(this.matriz, other.matriz)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Arrays.deepHashCode(this.matriz);
        return hash;
    }

    @Override
    public EstadoSokoban clone() {
        EstadoSokoban copia = new EstadoSokoban();
        copia.matriz = new Celula[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                copia.matriz[i][j] = matriz[i][j].clone();
            }
        }
        copia.posicaoAgente = copia.getCelula(new Point(posicaoAgente.getX(), posicaoAgente.getY()));
        return copia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder((matriz.length + 1)*matriz[0].length);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sb.append(matriz[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public Celula getCelulaAcima(Celula c){
        if(c.getY()<=0){
            return null;
        }
        return matriz[c.getX()][c.getY()-1];
    }
    
    public Celula getCelulaDireita(Celula c){
        if(c.getX()>= matriz.length-1){
            return null;
        }
        return matriz[c.getX()+1][c.getY()];
    }
    
    public Celula getCelulaAbaixo(Celula c){
        if(c.getY()>= matriz[0].length-1){
            return null;
        }
        return matriz[c.getX()][c.getY()+1];
    }
    
    public Celula getCelulaEsquerda(Celula c){
        if(c.getX()<= 0){
            return null;
        }
        return matriz[c.getX()-1][c.getY()];
    }
    
    @Override
    public void aplicarOperadores(List<Operador> operadores) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public Celula getPosicaoAgente() {
        return posicaoAgente;
    }
    
    public void setPosicaoAgente(Celula destino){
        posicaoAgente.setAgente(false);
        posicaoAgente = destino;
        posicaoAgente.setAgente(true);
    }

    public LinkedList<Point> getPosicoesObjetivo() {
        LinkedList<Point> lista = new LinkedList<Point>();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                Celula c = matriz[i][j];
                if(c.isObjetivo()){
                    lista.add(new Point(c.getX(), c.getY()));
                }
            }
        }
        return lista;
    }
    
    public Celula getCelula(Point p){
        return matriz[p.x][p.y];
    }
    
    //Listeners
    private transient ArrayList<PuzzleListener> listeners = new ArrayList<PuzzleListener>(3);

    public synchronized void removePuzzleListener(PuzzleListener l) {
            listeners.remove(l);
    }

    public synchronized void addPuzzleListener(PuzzleListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(EventObject eo){
        for(PuzzleListener listener : listeners){
            listener.puzzleChanged(eo);
        }
    }

    public int getNumColunas() {
        return matriz.length;
    }

    public int getNumLinhas() {
        return matriz[0].length;
    }

    public Celula getValueAt(int row, int col) {
        return matriz[row][col];
    }
    
    public void moverCaixote(Celula origem, Celula destino){
        celulasCaixotes.remove(origem);
        celulasCaixotes.add(destino);
    }
    
}