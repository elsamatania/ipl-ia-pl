package sokoban.operadores;

import agente.Operador;
import sokoban.Celula;
import sokoban.EstadoSokoban;

/**
 *
 * @author Renato
 */
public abstract class OperadorSokoban extends Operador<EstadoSokoban> {

    protected MoverAgente operadorAgente;
    protected MoverCaixote operadorCaixote;
    
    public OperadorSokoban(double custo) {
        super(custo);
    }
    
    @Override
    public void executar(EstadoSokoban estado) {
        SubOperadorSokoban subOperador = getSubOperador(estado);
        subOperador.executar(estado);
        estado.setOperador(subOperador);
    }
    
    protected abstract SubOperadorSokoban getSubOperador(EstadoSokoban estado);
    
    protected abstract class SubOperadorSokoban extends Operador<EstadoSokoban>{

        public SubOperadorSokoban(double custo) {
            super(custo);
        }
    }
    
     protected abstract class MoverAgente extends SubOperadorSokoban {

        public MoverAgente(double custo) {
            super(custo);
        }
    }
     
    protected abstract class MoverCaixote extends SubOperadorSokoban {

        public MoverCaixote(double custo) {
            super(custo);
        }

        protected void moverCaixote(Celula origem, Celula destino){
            origem.setCaixote(false);
            destino.setCaixote(true);
        }
    } 
}
