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
public class HeuristicaManhattan extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Distancia Manhattan para Caixote mais proximo";

    public HeuristicaManhattan(ProblemaSokoban problema) {
        super(problema);
    }

    @Override
    public double calcular(EstadoSokoban estado) {

        LinkedList<Point> objectivos = problema.getPosicoesObjetivo();
        double dx, dy;
        double valorFinal = 10000;
        double caixoteMaisPerto;

        for (Point objectivo : objectivos) {
            for (int i = 0; i < estado.getNumColunas(); i++) {
                for (int j = 0; j < estado.getNumLinhas(); j++) {
                    Celula celulaAtual = estado.getValueAt(i, j);
                    if (celulaAtual.temCaixote()) {
                        dx = Math.abs(i - objectivo.getX());
                        dy = Math.abs(j - objectivo.getY());
                        caixoteMaisPerto = dx + dy;

                        if (caixoteMaisPerto < valorFinal) {
                            valorFinal = caixoteMaisPerto;
                        }

                    }

                }
            }

        }

        if (problema.isObjetivoAtingido(estado)) {
            System.out.println("valor final:" + valorFinal);
        }

        return valorFinal;
    }
}
