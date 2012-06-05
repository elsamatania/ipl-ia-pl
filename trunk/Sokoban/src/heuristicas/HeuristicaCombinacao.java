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
 * @author Leonardo Lino
 */
public class HeuristicaCombinacao extends Heuristica<ProblemaSokoban, EstadoSokoban> {

    public static final String NOME = "Combinacao de Varias Heuristicas";

    public HeuristicaCombinacao(ProblemaSokoban problema) {
        super(problema);
    }

    @Override
    public double calcular(EstadoSokoban estado) {

        //para dar um custo proporcional ao tamanho do puzzle
        int tamanhoPuzzle = estado.getNumColunas() * estado.getNumLinhas();

        double valortotal = 0;

        //caixotes ao objectivo
        valortotal += caixotesObjectivo(estado, valortotal);
        
        //distancia agente ao objectivo mais proximo
        //valortotal += agenteObjetivo(estado, valortotal);

        //empurrar
        valortotal += empurrar(estado, valortotal);

        return valortotal;
    }

    private double agenteObjetivo(EstadoSokoban estado, double valorFinal) {
        LinkedList<Point> objectivos = problema.getPosicoesObjetivo();
        Celula agente = estado.getPosicaoAgente();
        double distancia; //distancia ao objectivo mais proximo
        double dx, dy;
        double valorTemp = 0;

        for (Point objectivo : objectivos) {
            dx = Math.abs(agente.getX() - objectivo.getX());
            dy = Math.abs(agente.getY() - objectivo.getY());
            distancia = dx + dy;

            if (distancia > valorTemp) valorTemp = distancia;
        }

        return valorTemp;
    }

    private double caixotesObjectivo(EstadoSokoban estado, double valorFinal) {
        LinkedList<Point> objectivos = problema.getPosicoesObjetivo();
        double dx, dy;
        double distCaixote;
        double menorDistancia = Double.POSITIVE_INFINITY;

        for (int i = 0; i < estado.getNumColunas(); i++) {
            for (int j = 0; j < estado.getNumLinhas(); j++) {
                Celula celulaAtual = estado.getValueAt(j, i);
                if (celulaAtual.temCaixote()) {
                    menorDistancia = Double.POSITIVE_INFINITY;
                    for (Point objectivo : objectivos) {
                        dx = Math.abs(i - objectivo.getY());
                        dy = Math.abs(j - objectivo.getX());
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

    private double empurrar(EstadoSokoban estado, double valortotal) {
        Celula agente = estado.getPosicaoAgente();
        Celula abaixo = estado.getCelulaAbaixo(agente);
        Celula acima = estado.getCelulaAcima(agente);
        Celula esquerda = estado.getCelulaEsquerda(agente);
        Celula direita = estado.getCelulaDireita(agente);


        if (abaixo.temCaixote()) {
            valortotal = 1;
        }
        if (acima.temCaixote()) {
            valortotal = 1;
        }
        if (esquerda.temCaixote()) {
            valortotal = 1;
        }
        if (direita.temCaixote()) {
            valortotal = 1;
        }


        if (abaixo.temCaixote() && abaixo.isObjetivo()) {
            valortotal = 0;
        }
        if (acima.temCaixote() && acima.isObjetivo()) {
            valortotal = 0;
        }
        if (esquerda.temCaixote() && esquerda.isObjetivo()) {
            valortotal = 0;
        }
        if (direita.temCaixote() && direita.isObjetivo()) {
            valortotal = 0;
        }

        return valortotal;
    }
}
