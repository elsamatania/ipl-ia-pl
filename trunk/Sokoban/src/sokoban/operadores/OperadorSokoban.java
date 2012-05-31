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

    public OperadorSokoban(double custo) {
        super(custo);
    }
    
    protected boolean isDestinoValidoAgente(EstadoSokoban estado, Celula celula) {
        if (celula == null) {
            return false;
        }
        if (celula.isParede()) {
            return false;
        }
        return true;
    }

    protected boolean isDestinoValidoCaixote(EstadoSokoban estado, Celula celula) {
        if (celula == null) {
            return false;
        }
        if (celula.isParede() || celula.temCaixote()) {
            return false;
        }

        if (!celula.isObjetivo() && celula.isCanto()) {
            return false;
        }
        return true;
    }
}
