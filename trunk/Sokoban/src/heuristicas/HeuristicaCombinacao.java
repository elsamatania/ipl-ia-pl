/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import java.util.LinkedList;
import sokoban.Celula;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaCombinacao extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Combinação de várias heurísticas";
    public static LinkedList<Celula> visitadas;
    
    private HeuristicaAgenteCaixoteMaisProximo heurAgente;
    private HeuristicasDistanciasCaixoteAObjetivo heurCaixotes;
    private HeuristicaEmpurrar heurEmpurrar;
    

    public HeuristicaCombinacao(ProblemaSokoban problema) {
        super(problema);
        heurAgente = new HeuristicaAgenteCaixoteMaisProximo(problema);
        heurCaixotes = new HeuristicasDistanciasCaixoteAObjetivo(problema);
        heurEmpurrar = new HeuristicaEmpurrar(problema);
        visitadas = new LinkedList<Celula>();
    }

    @Override
    public double calcular(EstadoSokoban estado) {
        return heurAgente.calcular(estado) + heurCaixotes.calcular(estado) + heurEmpurrar.calcular(estado);
    }
    
    
    private double celulaVisitada(EstadoSokoban estado){
        
        double visita = 0;
         //para dar um custo proporcional ao tamanho do puzzle
        int tamanhoPuzzle = estado.getNumColunas() * estado.getNumLinhas();
        
        for (Celula celula : visitadas) {
            if (estado.getPosicaoAgente().equals(celula)){
                visita = tamanhoPuzzle;
            } else {
                visitadas.add(celula);
            }
        }
        return visita;
    }
}
