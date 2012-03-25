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
public class MoverAgenteBaixo extends OperadorSokoban{

    public MoverAgenteBaixo(double custo) {
        super(custo);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        Celula destino = estado.getCelulaAbaixo(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            destino.setCaixote(false);
            estado.getCelulaAbaixo(destino).setCaixote(true);
        }
        estado.setPosicaoAgente(destino);
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula destino = estado.getCelulaAbaixo(estado.getPosicaoAgente());
        if(destino != null){
            if(destino.temCaixote()){
                Celula abaixo = estado.getCelulaAbaixo(destino);
                if(abaixo != null){
                    return abaixo.estaVazia();
                }
                return false;
            }
            return !destino.temParede();
        }
        return false;
    }
    
}
