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
public class MoverAgenteDireita extends OperadorSokoban{

    public MoverAgenteDireita(double custoAgente, double custoCaixote) {
        super(custoAgente, custoCaixote);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        Celula destino = estado.getCelulaDireita(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            moverCaixote(destino, estado.getCelulaDireita(destino));
        }
        estado.setPosicaoAgente(destino);
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula destino = estado.getCelulaDireita(estado.getPosicaoAgente());
        if(destino != null){
            if(destino.temCaixote()){
                Celula direita = estado.getCelulaDireita(destino);
                if(direita != null){
                    return direita.estaVazia();
                }
                return false;
            }
            return !destino.temParede();
        }
        return false;
    }
    
}
