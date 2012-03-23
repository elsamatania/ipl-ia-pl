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
public class MoverAgenteCima extends Operador<EstadoSokoban> {

    public MoverAgenteCima(double custo) {
        super(custo);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        estado.moverAgenteCima();
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        return estado.agentePodeMoverCima();
    }
    
}
