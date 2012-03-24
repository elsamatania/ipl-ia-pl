/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Estado;
import agente.Operador;
import java.util.List;

/**
 *
 * @author Renato
 */
public final class EstadoSokoban extends Estado{

    private Celula[][] matriz;
    private Celula posicaoAgente;

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

    public boolean agentePodeMoverCima() {
        Celula destino = matriz[posicaoAgente.getX()][posicaoAgente.getY()-1];
        if(posicaoAgente.getY() <= 0 || destino.temParede()){
            return false;
        }

        if(destino.temCaixote()){
            if(posicaoAgente.getY()<=1){
                return false;
            }
            Celula acima = matriz[posicaoAgente.getX()][posicaoAgente.getY()-2];
            if(acima.temCaixote() || acima.temParede()){
                return false;
            }
        }
        return true;
    }
    
    public void moverAgenteCima(){
        Celula destino = matriz[posicaoAgente.getX()][posicaoAgente.getY()-1];

        if(destino.temCaixote()){
            matriz[posicaoAgente.getX()][posicaoAgente.getY()-2].setCaixote(true);
            destino.setCaixote(false);
        }
        setPosicaoAgente(destino);
    }
    
    
    public Celula getPosicaoAgente() {
        return posicaoAgente;
    }
    
    public void setPosicaoAgente(Celula destino){
        posicaoAgente.setAgente(false);
        posicaoAgente = destino;
        posicaoAgente.setAgente(true);
    }

    void moverAgenteDireita() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    boolean agentePodeMoverDireita() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
