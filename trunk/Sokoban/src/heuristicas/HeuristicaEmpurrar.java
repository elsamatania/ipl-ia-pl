/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import java.util.ArrayList;
import java.util.List;
import sokoban.Celula;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaEmpurrar extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Prioridade a empurrar";

    public HeuristicaEmpurrar(ProblemaSokoban problema) {
        super(problema);
    }

    @Override
    public double calcular(EstadoSokoban estado) {
        if(problema.isObjetivoAtingido(estado)){
            return 0;
        }
        
        Celula agente = estado.getPosicaoAgente();
        double valorTotal = 2;

        List<Celula> vizinhas = new ArrayList<Celula>();
        vizinhas.add(estado.getCelulaAcima(agente));
        vizinhas.add(estado.getCelulaDireita(agente));
        vizinhas.add(estado.getCelulaAbaixo(agente));
        vizinhas.add(estado.getCelulaEsquerda(agente));

        for (Celula c : vizinhas) {
            if (c.temCaixote()) {
                if (c.isObjetivo()) {
                    valorTotal = 0;
                    break;
                } else {
                    valorTotal = 1;
                }

            }
        }

        return valorTotal;
    }
}
