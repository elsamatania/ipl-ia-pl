/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Estado;
import agente.Heuristica;
import agente.Problema;
import java.awt.Point;
import java.util.LinkedList;
import sokoban.Celula;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaCaixotesForaSitio extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Caixotes fora do destino";
    public HeuristicaCaixotesForaSitio(ProblemaSokoban problema) {
        super(problema);
    }
    
    

    @Override
    public double calcular(EstadoSokoban estado) {
        
        LinkedList<Point> listaObjetivos = problema.getPosicoesObjetivo();
        Celula celula;
        double contador=0;
        for (Point point : listaObjetivos) {
            celula = estado.getValueAt(point.x, point.y);
            if (!celula.temCaixote()) contador++;
        }
        
        
        return contador;
    }
    
}
