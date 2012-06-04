/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import agente.Estado;
import agente.Heuristica;
import agente.Problema;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import sokoban.Celula;
import sokoban.EstadoSokoban;

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

        private HashMap<String, Integer> listaRegras;
        private Celula[][] matriz;

        public Regra() {
            listaRegras = new HashMap<String, Integer>();
            matriz = new Celula[2][2];
        }

        public void adicionarRegras() {

            String r1 = "0P00CP000";
            listaRegras.put(r1, 50);
            listaRegras.put(inverteHorizontal(r1), 50);
            listaRegras.put(inverteVertical(r1), 50);

            /*
             * 0P0 0CP 000
             */
        }

        public String inverteHorizontal(String str) {
            char aux, aux2;
            StringBuilder novaRegra = new StringBuilder(str);
            aux = str.charAt(0);
            aux2 = str.charAt(2);
            novaRegra.setCharAt(0, aux2);
            novaRegra.setCharAt(2, aux);

            aux = str.charAt(3);
            aux2 = str.charAt(5);
            novaRegra.setCharAt(3, aux2);
            novaRegra.setCharAt(5, aux);

            aux = str.charAt(6);
            aux2 = str.charAt(8);
            novaRegra.setCharAt(6, aux2);
            novaRegra.setCharAt(8, aux);

            return novaRegra.toString();
        }

        public String inverteVertical(String str) {
            char aux, aux2;
            StringBuilder novaRegra = new StringBuilder(str);
            aux = str.charAt(0);
            aux2 = str.charAt(6);
            novaRegra.setCharAt(0, aux2);
            novaRegra.setCharAt(6, aux);

            aux = str.charAt(1);
            aux2 = str.charAt(7);
            novaRegra.setCharAt(1, aux2);
            novaRegra.setCharAt(7, aux);

            aux = str.charAt(2);
            aux2 = str.charAt(8);
            novaRegra.setCharAt(2, aux2);
            novaRegra.setCharAt(8, aux);

            return novaRegra.toString();
        }

        public void buildMatriz(EstadoSokoban estadoAtual, int i, int j) {
            matriz[0][0] = estadoAtual.getValueAt(i - 1, j - 1);
            matriz[1][0] = estadoAtual.getValueAt(i, j - 1);
            matriz[2][0] = estadoAtual.getValueAt(i + 1, j - 1);

            matriz[0][1] = estadoAtual.getValueAt(i - 1, j);
            matriz[1][1] = estadoAtual.getValueAt(i, j);
            matriz[2][1] = estadoAtual.getValueAt(i + 1, j);

            matriz[0][2] = estadoAtual.getValueAt(i - 1, j + 1);
            matriz[1][2] = estadoAtual.getValueAt(i, j + 1);
            matriz[2][2] = estadoAtual.getValueAt(i + 1, j + 1);
        }

        public double compareMatrizRegras() {

            for (Map.Entry<String, Integer> regra : listaRegras.entrySet()) {
                String key = regra.getKey();
                int value = regra.getValue();
                
                if (key.charAt(0) != 0){
                    if (key.charAt(0)=='P' && matriz[0][0].isParede()) return value;
                        
                    
                }
                
              
            }

            return 0;
        }

        public double compareEstadoToRegras(Estado estado) {
            EstadoSokoban estadoAtual = (EstadoSokoban) estado;
            double valorFinal = 0;

            for (int i = 0; i < estadoAtual.getNumColunas(); i++) {
                for (int j = 0; j < estadoAtual.getNumLinhas(); j++) {
                    Celula celulaAtual = estadoAtual.getValueAt(i, j);
                    if (celulaAtual.temCaixote()) {
                        buildMatriz(estadoAtual, i, j);

                    }

                }
            }
            return valorFinal;
        }
    }
}
