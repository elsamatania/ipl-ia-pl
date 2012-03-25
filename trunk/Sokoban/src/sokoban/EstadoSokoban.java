/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Estado;
import agente.Operador;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Renato
 */
public final class EstadoSokoban extends Estado implements Cloneable{

    private Celula[][] matriz;
    private Celula posicaoAgente;

    private EstadoSokoban() {
        
    }
    
    

    public EstadoSokoban(char[][] tabela) {
        matriz = new Celula[tabela.length][tabela[0].length];
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[0].length; j++) {
                matriz[i][j] = new Celula(i, j);
                switch(tabela[i][j]){
                    case 'P':
                        matriz[i][j].setParede(true);
                        break;
                    case 'O':
                        matriz[i][j].setObjetivo(true);
                        break;
                    case 'C':
                        matriz[i][j].setCaixote(true);
                        break;
                    case 'X':
                        matriz[i][j].setObjetivo(true);
                        matriz[i][j].setCaixote(true);
                        break;
                    case 'A':
                        setPosicaoAgente(matriz[i][j]);
                }
            } 
        }
    }

    @Override
    protected EstadoSokoban clone() {
        EstadoSokoban copia = new EstadoSokoban();
        copia.matriz = new Celula[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                copia.matriz[i][j] = matriz[i][j].clone();
            }
        }
        copia.posicaoAgente = posicaoAgente.clone();
        return copia;
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
    
}
