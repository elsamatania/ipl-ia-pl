/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Operador;

/**
 *
 * @author Renato
 */
public abstract class OperadorSokoban extends Operador<EstadoSokoban> {

    protected double custoCaixote;
    
    public OperadorSokoban(double custo) {
        super(custo);
    }
    
}
