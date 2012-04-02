/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.operadores;

import sokoban.Celula;
import sokoban.EstadoSokoban;

/**
 *
 * @author Renato
 */
public class MoverAgenteCima extends OperadorSokoban {

    public MoverAgenteCima(double custoAgente, double custoCaixote) {
        super(custoAgente, custoCaixote);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        Celula destino = estado.getCelulaAcima(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            moverCaixote(destino, estado.getCelulaAcima(destino));
        }
        estado.setPosicaoAgente(destino);
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula destino = estado.getCelulaAcima(estado.getPosicaoAgente());
        if(destino != null){
            if(destino.temCaixote()){
                Celula acima = estado.getCelulaAcima(destino);
                if(acima != null){
                    return acima.estaVazia();
                }
                return false;
            }
            return !destino.temParede();
        }
        return false;
    }
    
}
