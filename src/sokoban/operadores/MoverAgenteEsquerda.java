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
public class MoverAgenteEsquerda extends OperadorSokoban{

    public MoverAgenteEsquerda(double custoAgente, double custoCaixote) {
        super(custoAgente, custoCaixote);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        Celula destino = estado.getCelulaEsquerda(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            moverCaixote(destino, estado.getCelulaEsquerda(destino));
        }
        estado.setPosicaoAgente(destino);
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula destino = estado.getCelulaEsquerda(estado.getPosicaoAgente());
        if(destino != null){
            if(destino.temCaixote()){
                Celula esquerda = estado.getCelulaEsquerda(destino);
                if(esquerda != null){
                    return esquerda.estaVazia();
                }
                return false;
            }
            return !destino.temParede();
        }
        return false;
    }
    
}
