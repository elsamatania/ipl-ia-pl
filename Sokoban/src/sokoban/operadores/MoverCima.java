/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.operadores;

import agente.Operador;
import sokoban.Celula;
import sokoban.EstadoSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class MoverCima extends Operador<EstadoSokoban> {
    
    public MoverCima(double custo) {
        super(custo);
    }
    
    @Override
    public void executar(EstadoSokoban estado) {
        Celula celula = estado.getCelulaAcima(estado.getPosicaoAgente());
       estado.setPosicaoAgente(celula);
        
        if (celula.temCaixote()) {
            Celula celulaAcimaCaixote = estado.getCelulaAcima(celula);
            celulaAcimaCaixote.setCaixote(true);
            celula.setCaixote(false);
        }
    }
    
    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula celula = estado.getCelulaAcima(estado.getPosicaoAgente());
        
        if (celula == null) {
            return false;
        }
        if (celula.isParede()) {
            return false;
        }
        
        if (celula.temCaixote()) {
            Celula celulaAcimaCaixote = estado.getCelulaAcima(celula);
            if (celulaAcimaCaixote == null) {
                return false;
            }
            if (celulaAcimaCaixote.isParede() || celulaAcimaCaixote.temCaixote()) {
                return false;
            }
        }
        return true;
    }

    
}
