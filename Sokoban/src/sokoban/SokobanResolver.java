/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Agente;
import agente.Operador;
import agente.Solucao;
import java.util.ArrayList;
import java.util.List;
import sokoban.operadores.MoverBaixo;
import sokoban.operadores.MoverCima;
import sokoban.operadores.MoverDireita;
import sokoban.operadores.MoverEsquerda;

/**
 *
 * @author Renato
 */
public class SokobanResolver {

    private List<Operador> operadores;
    private ProblemaSokoban problema;
    private Agente agente = new Agente();
    private Solucao solucao;
    private long tempoPesquisa;

    public SokobanResolver(char[][] tabela) {
        if (!isSokobanValido(tabela)) {
            throw new IllegalArgumentException("A tabela fornecida não corresponde a um problema de Sokoban válido.");
        }
        operadores = new ArrayList<Operador>();
        operadores.add(new MoverCima(1));
        operadores.add(new MoverDireita(1));
        operadores.add(new MoverBaixo(1));
        operadores.add(new MoverEsquerda(1));
        problema = new ProblemaSokoban(new EstadoSokoban(tabela), operadores);
    }

    public void setProblema(char[][] tabela) {
        problema = new ProblemaSokoban(new EstadoSokoban(tabela), operadores);
    }

    public static boolean isSokobanValido(char[][] tabela) {
        int numAgentes, numCaixotes, numObjetivos;
        numAgentes = numCaixotes = numObjetivos = 0;
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i].length != tabela[0].length) {
                return false;
            }
            for (int j = 0; j < tabela[0].length; j++) {
                switch (tabela[i][j]) {
                    case 'P':
                    case 'V':
                        break;
                    case 'O':
                        numObjetivos++;
                        break;
                    case 'C':
                        numCaixotes++;
                        break;
                    case 'X':
                        numObjetivos++;
                        numCaixotes++;
                        break;
                    case 'A':
                        numAgentes++;
                        break;
                    default:
                        return false;
                }
            }
        }

        if (numAgentes != 1 || numCaixotes != numObjetivos || numCaixotes == 0) {
            return false;
        }

        return true;
    }

    public EstadoSokoban getPuzzleInicial() {
        return problema.getEstadoInicial();
    }

    public void resolverProblema() {
        long tempoInicial = System.nanoTime();
        solucao = agente.resolveProblema(problema, null);
        tempoPesquisa = System.nanoTime() - tempoInicial;
    }
    
    public void mostrarSolucao(){
        if(temSolucao()){
            problema.getEstadoInicial().aplicarOperadores(solucao.getOperadores());
        }
    }
    
    public boolean temSolucao(){
        return solucao != null;
    }

    public double getCustoSolucao() {
        if(temSolucao()){
            return solucao.getCusto();
        }
        return 0;
    }

    public int getProfundidadeSolucao() {
        if(temSolucao()){
            return solucao.getProfundidade();
        }
        return 0;
        
    }

    public long getTempoPesquisa() {
        return tempoPesquisa;
    }
    

    
}
