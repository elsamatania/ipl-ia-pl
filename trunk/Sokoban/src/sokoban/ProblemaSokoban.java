/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Operador;
import agente.Problema;
import java.util.List;

/**
 *
 * @author Renato
 */
public class ProblemaSokoban extends Problema<EstadoSokoban> {

    public ProblemaSokoban(EstadoSokoban estadoInicial, List<Operador> listaOperadores) {
        super(estadoInicial, listaOperadores);
    }

    
    @Override
    public boolean isObjetivoAtingido(EstadoSokoban e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EstadoSokoban> aplicarOperadores(EstadoSokoban e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
