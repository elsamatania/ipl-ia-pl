/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import java.awt.Point;
import java.util.LinkedList;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Renato
 */
public class HeuristicaSomaMinimaOtima extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Soma mínima ótima das distâncias dos caixotes aos objetivos";
    int[][] permutacoes3 = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
    private LinkedList<Point> objetivos;
    
    public HeuristicaSomaMinimaOtima(ProblemaSokoban problema) {
        super(problema);
    }

    @Override
    public double calcular(EstadoSokoban estado) {
        int min = Integer.MAX_VALUE;
        int temp, soma;
        LinkedList<Point> caixotes = estado.getPosicoesCaixotes();
        objetivos = estado.getPosicoesObjetivo();
        Point caixAtual;
        Point objAtual;

        for (int[] per : permutacoes3) {
            soma = 0;
            for (int i = 0; i < 3; i++) {
                caixAtual = caixotes.get(i);
                objAtual = objetivos.get(per[i]-1);
                temp = Math.abs(caixAtual.x - objAtual.x) + Math.abs(caixAtual.y - objAtual.y);
                soma += temp;
            }
            min = Math.min(soma, min);
        }

        return min;
    }
    
}
