/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

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
public class HeuristicaCaixotesForaSitio extends Heuristica {

    public HeuristicaCaixotesForaSitio(Problema problema) {
        super(problema);
    }
    
    

    @Override
    public double calcular(Estado estado) {
        
        LinkedList<Point> listaObjetivos = ((ProblemaSokoban)problema).getPosicoesObjetivo();
        Celula celula;
        double contador=0;
        for (Point point : listaObjetivos) {
            celula = ((EstadoSokoban) estado).getCelula(point);
            if (!celula.temCaixote()) contador++;
        }
        
        return contador;
    }
    
}
