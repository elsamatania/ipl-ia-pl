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
public class HeuristicaCombinacao extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Soma mínima sôfrega + prioridade a empurrar";
    
    private HeuristicaSomaMinimaSofrega heurObj;

    public HeuristicaCombinacao(ProblemaSokoban problema) {
        super(problema);
        heurObj = new HeuristicaSomaMinimaSofrega(problema);
    }

    @Override
    public double calcular(EstadoSokoban estado) {

        double valortotal = 0;

        //caixotes ao objectivo
        valortotal += heurObj.calcular(estado);

        //empurrar
        if(valortotal > 0){
            valortotal += empurrar(estado, valortotal);
        }

        return valortotal;
    }

    private double empurrar(EstadoSokoban estado, double valorTotal) {
        Celula agente = estado.getPosicaoAgente();

        List<Celula> vizinhas = new ArrayList<Celula>();
        vizinhas.add(estado.getCelulaAcima(agente));
        vizinhas.add(estado.getCelulaDireita(agente));
        vizinhas.add(estado.getCelulaAbaixo(agente));
        vizinhas.add(estado.getCelulaEsquerda(agente));

        for (Celula c : vizinhas) {
            if (c.temCaixote()) {
                if (!c.isObjetivo()) {
                    valorTotal = 0;
                }
            }
        }

        
        return valorTotal;
    }
}
