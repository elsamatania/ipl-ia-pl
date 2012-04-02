/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.operadores;

import agente.Operador;
import sokoban.Celula;
import sokoban.EstadoSokoban;

/**
 *
 * @author Renato
 */
public abstract class OperadorSokoban extends Operador<EstadoSokoban> {

    protected double custoCaixote;
    protected int movimentosCaixote = 0;
    
    public OperadorSokoban(double custoAgente, double custoCaixote) {
        super(custoAgente);
        this.custoCaixote = custoCaixote;
    }
    
    protected void moverCaixote(Celula origem, Celula destino){
        origem.setCaixote(false);
        destino.setCaixote(true);
        movimentosCaixote++;
    }

    @Override
    public double getCusto() {
        return super.getCusto() + movimentosCaixote*custoCaixote;
    }
    
    
    
}
