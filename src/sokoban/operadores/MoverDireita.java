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
public class MoverDireita extends OperadorSokoban{
    
    
    public MoverDireita(double custoAgente, double custoCaixote) {
        super(custoAgente);
        this.operadorAgente = new MoverAgenteDireita(custoAgente);
        this.operadorCaixote = new MoverCaixoteDireita(custoCaixote);
    }



    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        if(estado.getCelulaDireita(estado.getPosicaoAgente()) == null){
            return false;
        }
        return getSubOperador(estado).podeSerAplicado(estado);
    }

    @Override
    protected SubOperadorSokoban getSubOperador(EstadoSokoban estado) {
        Celula destino = estado.getCelulaDireita(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            return operadorCaixote;
        }
        return operadorAgente;
    }
    
    private class MoverAgenteDireita extends MoverAgente {

        public MoverAgenteDireita(double custo) {
            super(custo);
        }

        @Override
        public void executar(EstadoSokoban estado) {
            Celula destino = estado.getCelulaDireita(estado.getPosicaoAgente());
            estado.setPosicaoAgente(destino);
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return !estado.getCelulaDireita(estado.getPosicaoAgente()).temParede();
        }
        
    }
    
    private class MoverCaixoteDireita extends MoverCaixote {

        public MoverCaixoteDireita(double custoCaixote) {
            super(custoCaixote);
        }

        @Override
        public void executar(EstadoSokoban estado) {
           Celula destino = estado.getCelulaDireita(estado.getPosicaoAgente());
           moverCaixote(destino, estado.getCelulaDireita(destino));
           operadorAgente.executar(estado);
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return estado.getCelulaDireita(estado.getCelulaDireita(estado.getPosicaoAgente())).estaVazia();
        }
        
    }
}