/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import agente.Estado;
import agente.Heuristica;
import agente.Problema;
import java.util.HashMap;
import java.util.LinkedList;
import sokoban.Celula;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaComRegras extends Heuristica {

    public HeuristicaComRegras(Problema problema) {
        super(problema);
    }

    @Override
    public double calcular(Estado estado) {


        return 0;
    }

    public class Regra {

        private HashMap<char[][], Integer> listaRegras;

        public Regra() {
            listaRegras = new HashMap<char[][], Integer>();

        }

        public Celula[][] adicionarRegras() {

            LinkedList<String> stringsRegras = new LinkedList<String>();
            String r1 = "0PP0CP0AP";
            stringsRegras.add(r1);
            String r2 = "PP0PC0PA0";
            stringsRegras.add(r2);

            /*
             * PP0 PC0 PA0
             */
            int c;
            for (String e : stringsRegras) {
                c = 0;
                char[][] matriz = new char[3][3];

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matriz[i][j] = e.charAt(c);
                        c++;
                    }
                }
                listaRegras.put(matriz, 50);
            }

            return null;
        }

        public boolean compareEstadoToRegras(Estado estado) {
            return false;
        }
    }
}
