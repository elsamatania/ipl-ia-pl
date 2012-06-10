/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Renato
 */
public class HeuristicaSomaMinimaSofrega extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Soma mínima sôfrega das distâncias dos caixotes aos objetivos";
    private int[][] tabela;
    private LinkedList<Point> objetivos;
    //private LinkedList<Point> caixotes;
    private int dimensao;
    private HeuristicaAgenteCaixoteMaisProximo heurAgente;

    public HeuristicaSomaMinimaSofrega(ProblemaSokoban problema) {
        super(problema);
        heurAgente = new HeuristicaAgenteCaixoteMaisProximo(problema);
        objetivos = problema.getPosicoesObjetivo();
        dimensao = objetivos.size();
        tabela = new int[objetivos.size()][objetivos.size()];
        //lista = new int[dimensao*dimensao];
    }

    @Override
    public double calcular(EstadoSokoban estado) {
        
        int temp;
        Point pos = new Point();
        Point minimo = preencherTabela(estado);
        int total = tabela[minimo.x][minimo.y];

        invalidarLinhaEColuna(minimo);

        for (int i = 0; i < dimensao - 1; i++) {
            temp = Integer.MAX_VALUE;
            for (int j = 0; j < dimensao; j++) {
                for (int k = 0; k < dimensao; k++) {
                    if (tabela[j][k] < temp) {
                        temp = tabela[j][k];
                        pos.setLocation(j, k);
                    }
                }
            }
            total += temp;
            invalidarLinhaEColuna(pos);
        }

        if (total > 0) {
            total += heurAgente.calcular(estado);
        }

        return total;
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
                if (val < min) {
                    min = val;
                    minimo = new Point(i, j);
                }
            }
        }

        return minimo;
    }

    private void invalidarLinhaEColuna(Point p) {
        Arrays.fill(tabela[p.x], Integer.MAX_VALUE);
        for (int i = 0; i < tabela[0].length; i++) {
            tabela[i][p.y] = Integer.MAX_VALUE;
        }
    }
}
