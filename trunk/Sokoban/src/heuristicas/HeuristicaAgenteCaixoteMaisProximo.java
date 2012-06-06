/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Estado;
import agente.Heuristica;
import agente.Problema;
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

        Celula celulaAgente = estado.getPosicaoAgente();

        for (int i = 0; i < estado.getNumLinhas(); i++) {
            for (int j = 0; j < estado.getNumColunas(); j++) {
                Celula celulaAtual = estado.getValueAt(i, j);
                if (celulaAtual.temCaixote()) {

                    dx = Math.abs(i - celulaAgente.getX());
                    dy = Math.abs(j - celulaAgente.getY());
                    cMaisPertoAgente = dx + dy;

                    if (cMaisPertoAgente < valorFinalAgente) {
                        valorFinalAgente = cMaisPertoAgente;
                    }

                }

            }
        }


        return valorFinalAgente;
    }
}
