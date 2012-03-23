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
public class EstadoSokoban extends Estado{

    private int agentePosicaoX;
    private int agentePosicaoY;
    private Celula[][] matriz;

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
                        matriz[i][j].setAgente(true);
                        agentePosicaoX = i;
                        agentePosicaoY = j;
                }
            } 
        }
    }
    
    
    
    @Override
    public void aplicarOperadores(List<Operador> operadores) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean agentePodeMoverCima() {
        if(agentePosicaoY <= 0){
            return false;
        }
        Celula destino = matriz[agentePosicaoX][agentePosicaoY-1];
        return !destino.temCaixote() && !destino.temParede();
    }
    
    public void moverAgenteCima(){
        matriz[agentePosicaoX][agentePosicaoY].setAgente(false);
        matriz[agentePosicaoX][agentePosicaoY-1].setAgente(true);
        agentePosicaoY--;
    }
    
    public boolean caixotePodeMoverCima(int xAtual, int yAtual){
        if(yAtual <= 0 || yAtual >= matriz[0].length-1){
            return false;
        }
        Celula destino = matriz[xAtual][yAtual-1];
        return !destino.temParede() && !destino.temCaixote() 
                && matriz[xAtual][yAtual+1].temAgente();
    }
    
    public void moverCaixoteCima(int xAtual, int yAtual){
        matriz[xAtual][yAtual].setCaixote(false);
        matriz[xAtual][yAtual-1].setCaixote(true);
        moverAgenteCima();
    }
    
}
