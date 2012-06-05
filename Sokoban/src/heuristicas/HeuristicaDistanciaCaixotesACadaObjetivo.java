/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Renato
 */
public class HeuristicaDistanciaCaixotesACadaObjetivo extends Heuristica<ProblemaSokoban, EstadoSokoban>{

    private int[][] tabela;
    private int[] lista;
    private LinkedList<Point> objetivos;
    private int dimensao;
    
    public HeuristicaDistanciaCaixotesACadaObjetivo(ProblemaSokoban problema) {
        super(problema);
        objetivos = problema.getPosicoesObjetivo();
        dimensao = objetivos.size();
        tabela = new int[objetivos.size()][objetivos.size()];
        lista = new int[dimensao*dimensao];
    }

    @Override
    public double calcular(EstadoSokoban estado) {
        Point minimo = preencherTabela(estado);
        Arrays.fill(tabela[minimo.x], Integer.MAX_VALUE);
        //for()
        return 0;
    }

    private Point preencherTabela(EstadoSokoban e) {
        LinkedList<Point> caixotes = e.getPosicoesCaixotes();
        Point minimo = null;
        int min = Integer.MAX_VALUE;
        int val;
        for (int i = 0; i < objetivos.size(); i++) {
            Point o = objetivos.get(i);
            for (int j = 0; j < caixotes.size(); j++) {
                Point c = caixotes.get(j);
                val = Math.abs(o.x - c.x) + Math.abs(o.y - c.y);
                tabela[i][j] = val;
                if (val < min){
                    min = val;
                    minimo = new Point(i,j);
                }
            } 
        }
        
        return minimo;
    }
    
    
}
