/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import agente.Estado;
import agente.Heuristica;
import agente.Problema;
import java.util.Random;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaAleatoria extends Heuristica {

    public static final String NOME = "Aleatoria";
    public static final int PERCENTAGEM = 3;

    public HeuristicaAleatoria(Problema problema) {
        super(problema);
    }

    @Override
    public double calcular(Estado estado) {


        double valorFinal = 2;
        Random rand = new Random();

        if (rand.nextInt(PERCENTAGEM) == 1) {
            valorFinal=1;
        }

        return valorFinal;
    }
}
