/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import agente.Estado;
import agente.Heuristica;
import agente.Problema;
import sokoban.Celula;
import sokoban.EstadoSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class HeuristicaAgenteCaixoteMaisProximo extends Heuristica {
    
    public static final String NOME = "Agente ao Caixote Mais Proximo";

    public HeuristicaAgenteCaixoteMaisProximo(Problema problema) {
        super(problema);
    }
    

    @Override
    public double calcular(Estado estado) {
        EstadoSokoban estadoAtual = (EstadoSokoban) estado;
         double dx = 0;
        double dy = 0;
        double valorFinalAgente = 0;
        double cMaisPertoAgente = 10000;
        
        Celula celulaAgente = estadoAtual.getPosicaoAgente();
        
        for (int i = 0; i < estadoAtual.getNumColunas(); i++) {
                for (int j = 0; j < estadoAtual.getNumLinhas(); j++) {
                    Celula celulaAtual = estadoAtual.getValueAt(i, j);
                    if (celulaAtual.temCaixote()) {
                        
                        dx = Math.abs(i - celulaAgente.getX());
                        dy = Math.abs(j - celulaAgente.getY());
                        cMaisPertoAgente = dx + dy;

                        if (cMaisPertoAgente < valorFinalAgente) {
                            valorFinalAgente = cMaisPertoAgente;
                        }

                    }

                }
            }
        
        
        return valorFinalAgente;
    }
    
}
