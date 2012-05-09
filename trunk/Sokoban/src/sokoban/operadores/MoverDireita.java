/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.operadores;

import agente.Operador;
import sokoban.EstadoSokoban;
import sokoban.Celula;

/**
 *
 * @author Leonardo Lino
 */
public class MoverDireita extends Operador<EstadoSokoban> {

    public MoverDireita(double custo) {
        super(custo);
    }
    
    
     @Override
    public void executar(EstadoSokoban estado) {
        Celula celula = estado.getCelulaDireita(estado.getPosicaoAgente());
       estado.setPosicaoAgente(celula);
        
        if (celula.temCaixote()) {
            Celula celulaDireitaCaixote = estado.getCelulaDireita(celula);
            celulaDireitaCaixote.setCaixote(true);
            celula.setCaixote(false);
        }
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula celula = estado.getCelulaDireita(estado.getPosicaoAgente());
        
        if (celula == null) {
            return false;
        }
        if (celula.isParede()) {
            return false;
        }
        
        if (celula.temCaixote()) {
            Celula celulaDireitaCaixote = estado.getCelulaDireita(celula);
            if (celulaDireitaCaixote == null) {
                return false;
            }
            if (celulaDireitaCaixote.isParede() || celulaDireitaCaixote.temCaixote()) {
                return false;
            }
        }
        return true;
    }
    
}
