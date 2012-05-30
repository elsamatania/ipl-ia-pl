/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import agente.Heuristica;
import java.awt.Point;
import java.util.LinkedList;
import sokoban.Celula;
import sokoban.EstadoSokoban;
import sokoban.ProblemaSokoban;

/**
 *
 * @author Renato
 */
public class HeuristicasDistanciasCaixoteAObjetivo extends Heuristica<ProblemaSokoban, EstadoSokoban>{

    public static final String NOME = "Soma das distâncias de cada caixote ao objetivo mais próximo";
    
    public HeuristicasDistanciasCaixoteAObjetivo(ProblemaSokoban problema) {
        super(problema);
    }

    @Override
    public double calcular(EstadoSokoban estado) {
        LinkedList<Point> objectivos = problema.getPosicoesObjetivo();
        double dx, dy;
        double valorFinal = 0;
        double distCaixote;
        double menorDistancia = Double.POSITIVE_INFINITY;

            for (int i = 0; i < estado.getNumColunas(); i++) {
                for (int j = 0; j < estado.getNumLinhas(); j++) {
                    Celula celulaAtual = estado.getValueAt(i, j);
                    if (celulaAtual.temCaixote()) {
                        menorDistancia = Double.POSITIVE_INFINITY;
                        for (Point objectivo : objectivos) {
                            dx = Math.abs(i - objectivo.getX());
                            dy = Math.abs(j - objectivo.getY());
                            distCaixote = dx + dy;
                            menorDistancia = Math.min(distCaixote, menorDistancia);
                        }
                        valorFinal += menorDistancia;
                    }
                }
            }

        if (problema.isObjetivoAtingido(estado)) {
            System.out.println("valor final:" + valorFinal);
        }

        return valorFinal;
    }
    
}
