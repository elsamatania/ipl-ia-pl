/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

/**
 *
 * @author Renato
 */
public class MoverAgenteDireita extends OperadorSokoban{

    public MoverAgenteDireita(double custo) {
        super(custo);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        estado.moverAgenteDireita();
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        return estado.agentePodeMoverDireita();
    }
    
}
