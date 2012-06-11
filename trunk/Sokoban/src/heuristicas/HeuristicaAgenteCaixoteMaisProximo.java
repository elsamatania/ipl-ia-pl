/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import java.awt.Point;
import java.util.List;
import sokoban.Celula;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaAgenteCaixoteMaisProximo extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Agente ao Caixote Mais Proximo";

    public HeuristicaAgenteCaixoteMaisProximo(ProblemaSokoban problema) {
        super(problema);
    }

    @Override
    public double calcular(EstadoSokoban estado) {
        double dx, dy;
        double valorFinalAgente = Double.POSITIVE_INFINITY;
        double cMaisPertoAgente;
        List<Point> caixotesFalta = estado.getPosicoesCaixotes();
        caixotesFalta.removeAll(estado.getPosicoesObjetivo());
        
        if(caixotesFalta.isEmpty()){
            return 0;
        }

        Celula celulaAgente = estado.getPosicaoAgente();
        for (Point p : caixotesFalta) {
            dx = Math.abs(celulaAgente.getX() - p.x);
            dy = Math.abs(celulaAgente.getY() - p.y);
            cMaisPertoAgente = dx + dy;

            if (cMaisPertoAgente < valorFinalAgente) {
                valorFinalAgente = cMaisPertoAgente;
            }
        }

        return valorFinalAgente;
    }
}
