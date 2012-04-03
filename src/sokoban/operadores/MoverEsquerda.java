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
public class MoverEsquerda extends OperadorSokoban{
    
    
    public MoverEsquerda(double custoAgente, double custoCaixote) {
        super(custoAgente);
        this.operadorAgente = new MoverAgenteEsquerda(custoAgente);
        this.operadorCaixote = new MoverCaixoteEsquerda(custoCaixote);
    }



    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        if(estado.getCelulaEsquerda(estado.getPosicaoAgente()) == null){
            return false;
        }
        return getSubOperador(estado).podeSerAplicado(estado);
    }

    @Override
    protected SubOperadorSokoban getSubOperador(EstadoSokoban estado) {
        Celula destino = estado.getCelulaEsquerda(estado.getPosicaoAgente());
        if(destino.temCaixote()){
            return operadorCaixote;
        }
        return operadorAgente;
    }
    
    private class MoverAgenteEsquerda extends MoverAgente {

        public MoverAgenteEsquerda(double custo) {
            super(custo);
        }

        @Override
        public void executar(EstadoSokoban estado) {
            Celula destino = estado.getCelulaEsquerda(estado.getPosicaoAgente());
            estado.setPosicaoAgente(destino);
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return !estado.getCelulaEsquerda(estado.getPosicaoAgente()).temParede();
        }
        
    }
    
    private class MoverCaixoteEsquerda extends MoverCaixote {

        public MoverCaixoteEsquerda(double custoCaixote) {
            super(custoCaixote);
        }

        @Override
        public void executar(EstadoSokoban estado) {
           Celula destino = estado.getCelulaEsquerda(estado.getPosicaoAgente());
           moverCaixote(destino, estado.getCelulaEsquerda(destino));
           operadorAgente.executar(estado);
        }

        @Override
        public boolean podeSerAplicado(EstadoSokoban estado) {
            return estado.getCelulaEsquerda(estado.getCelulaEsquerda(estado.getPosicaoAgente())).estaVazia();
        }
        
    }
}