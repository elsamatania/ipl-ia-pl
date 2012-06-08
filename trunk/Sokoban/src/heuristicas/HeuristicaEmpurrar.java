/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import sokoban.Celula;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaEmpurrar extends Heuristica<ProblemaSokoban, EstadoSokoban> {
    
    public static final String NOME = "Prioridade a empurar";

    public HeuristicaEmpurrar(ProblemaSokoban problema) {
        super(problema);
    }
    
    

    @Override
    public double calcular(EstadoSokoban estado) {
        Celula agente = estado.getPosicaoAgente();
        Celula abaixo = estado.getCelulaAbaixo(agente);
        Celula acima = estado.getCelulaAcima(agente);
        Celula esquerda = estado.getCelulaEsquerda(agente);
        Celula direita = estado.getCelulaDireita(agente);
         
        double valortotal = 50;
        
        if (abaixo.temCaixote()) valortotal = 1;
        if (acima.temCaixote()) valortotal = 1;
        if (esquerda.temCaixote()) valortotal = 1;
        if (direita.temCaixote()) valortotal = 1;
        
        
        if (abaixo.temCaixote() && abaixo.isObjetivo()) valortotal = 0;
        if (acima.temCaixote() && acima.isObjetivo()) valortotal = 0;
        if (esquerda.temCaixote() && esquerda.isObjetivo()) valortotal = 0;
        if (direita.temCaixote() && direita.isObjetivo()) valortotal = 0;
        
        return valortotal;
    }
    
    
}
