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
public class HeuristicaManhattan extends Heuristica {
    public static final String NOME = "Distancia Manhattan para Caixote mais proximo";
    public HeuristicaManhattan(Problema problema) {
        super(problema);
    }

    @Override
    public double calcular(Estado estado) {

        LinkedList<Point> objectivos = ((ProblemaSokoban) problema).getPosicoesObjetivo();
        EstadoSokoban estadoAtual = (EstadoSokoban) estado;
        double dx = 0;
        double dy = 0;
        double valorFinal = 0;
        double caixoteMaisPerto = 0;

        for (Point objectivo : objectivos) {
            for (int i = 0; i < estadoAtual.getNumColunas(); i++) {
                for (int j = 0; j < estadoAtual.getNumLinhas(); j++) {
                    Celula celulaAtual = estadoAtual.getValueAt(i, j);
                    if (celulaAtual.temCaixote()) {
                        dx = Math.abs(i - objectivo.getX());
                        dy = Math.abs(j - objectivo.getY());
                    }
                    caixoteMaisPerto += dx + dy;
                }
            }
            if (caixoteMaisPerto > valorFinal) {
                valorFinal = caixoteMaisPerto;
            }
        }




        return valorFinal;
    }
}
