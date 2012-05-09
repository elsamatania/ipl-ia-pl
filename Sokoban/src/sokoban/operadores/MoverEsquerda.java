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
public class MoverEsquerda extends Operador<EstadoSokoban> {

    public MoverEsquerda(double custo) {
        super(custo);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        Celula celula = estado.getCelulaEsquerda(estado.getPosicaoAgente());
       estado.setPosicaoAgente(celula);
        
        if (celula.temCaixote()) {
            Celula celulaEsquerdaCaixote = estado.getCelulaEsquerda(celula);
            celulaEsquerdaCaixote.setCaixote(true);
            celula.setCaixote(false);
        }
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula celula = estado.getCelulaEsquerda(estado.getPosicaoAgente());
        
        if (celula == null) {
            return false;
        }
        if (celula.isParede()) {
            return false;
        }
        
        if (celula.temCaixote()) {
            Celula celulaEsquerdaCaixote = estado.getCelulaEsquerda(celula);
            if (celulaEsquerdaCaixote == null) {
                return false;
            }
            if (celulaEsquerdaCaixote.isParede() || celulaEsquerdaCaixote.temCaixote()) {
                return false;
            }
        }
        return true;
    }
    
    
    
}
