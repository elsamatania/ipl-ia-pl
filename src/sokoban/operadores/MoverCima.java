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
public class MoverCima extends OperadorSokoban{

    public MoverCima(double custoAgente, double custoCaixote) {
        super(custoAgente);
        this.operadorAgente = new MoverAgenteCima(custoAgente);
        this.operadorCaixote = new MoverCaixoteCima(custoCaixote);
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        if(estado.getCelulaAcima(estado.getPosicaoAgente()) == null){
            return false;
        }
        return getSubOperador(estado).podeSerAplicado(estado);
    }

    @Override
    protected SubOperadorSokoban getSubOperador(EstadoSokoban estado) {
        Celula destino = estado.getCelulaAcima(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            return operadorCaixote;
        }
        return operadorAgente;
    }
    
    private class MoverAgenteCima extends MoverAgente {

        public MoverAgenteCima(double custo) {
            super(custo);
        }

        @Override
        public void executar(EstadoSokoban estado) {
            estado.setPosicaoAgente(estado.getCelulaAcima(estado.getPosicaoAgente()));
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return !estado.getCelulaAcima(estado.getPosicaoAgente()).temParede();
        }
        
    }
    
    private class MoverCaixoteCima extends MoverCaixote {

        public MoverCaixoteCima(double custoCaixote) {
            super(custoCaixote);
        }

        @Override
        public void executar(EstadoSokoban estado) {
           Celula destino = estado.getCelulaAcima(estado.getPosicaoAgente());
           moverCaixote(destino, estado.getCelulaAcima(destino));
           operadorAgente.executar(estado);
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return estado.getCelulaAcima(estado.getCelulaAcima(estado.getPosicaoAgente())).estaVazia();
        }
        
    }
}