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
public class MoverBaixo extends OperadorSokoban{
    
    
    public MoverBaixo(double custoAgente, double custoCaixote) {
        super(custoAgente);
        this.operadorAgente = new MoverAgenteBaixo(custoAgente);
        this.operadorCaixote = new MoverCaixoteBaixo(custoCaixote);
    }



    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        if(estado.getCelulaAbaixo(estado.getPosicaoAgente()) == null){
            return false;
        }
        return getSubOperador(estado).podeSerAplicado(estado);
    }

    @Override
    protected SubOperadorSokoban getSubOperador(EstadoSokoban estado) {
        Celula destino = estado.getCelulaAbaixo(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            return operadorCaixote;
        }
        return operadorAgente;
    }
    
    private class MoverAgenteBaixo extends MoverAgente {

        public MoverAgenteBaixo(double custo) {
            super(custo);
        }

        @Override
        public void executar(EstadoSokoban estado) {
            Celula destino = estado.getCelulaAbaixo(estado.getPosicaoAgente());
            estado.setPosicaoAgente(destino);
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return !estado.getCelulaAbaixo(estado.getPosicaoAgente()).temParede();
        }
        
    }
    
    private class MoverCaixoteBaixo extends MoverCaixote {

        public MoverCaixoteBaixo(double custoCaixote) {
            super(custoCaixote);
        }

        @Override
        public void executar(EstadoSokoban estado) {
           Celula destino = estado.getCelulaAbaixo(estado.getPosicaoAgente());
           moverCaixote(destino, estado.getCelulaAbaixo(destino));
           operadorAgente.executar(estado);
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return estado.getCelulaAbaixo(estado.getCelulaAbaixo(estado.getPosicaoAgente())).estaVazia();
        }
        
    }
    
}
