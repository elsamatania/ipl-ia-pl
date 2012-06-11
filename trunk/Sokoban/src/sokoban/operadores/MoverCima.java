/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.operadores;

import sokoban.Celula;
import sokoban.EstadoSokoban;

/**
 *
 * @author Leonardo Lino
 */
public class MoverCima extends OperadorSokoban {

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

        if (!isDestinoValidoAgente(estado, celula)) {
            return false;
        }

        if (celula.temCaixote()) {
            Celula celulaDestinoCaixote = estado.getCelulaAcima(celula);
            return isDestinoValidoCaixote(estado, celulaDestinoCaixote);
        }
        return true;
    }
    
    @Override
    protected boolean isDeadlock(EstadoSokoban estado, Celula celula){
        if(celula.isObjetivo()){
            return false;
        }
        
        Celula acima = estado.getCelulaAcima(celula);
        Celula direita = estado.getCelulaDireita(celula);
        Celula esquerda = estado.getCelulaEsquerda(celula);
        
        if(acima.temCaixote()){
            if(direita.isParede() && estado.getCelulaDireita(acima).isParede()){
                return true;
            }
            if(esquerda.isParede() && estado.getCelulaEsquerda(acima).isParede()){
                return true;
            }
        }
        
        if(direita.temCaixote()){
            if(acima.isParede() && estado.getCelulaAcima(direita).isParede()){
                return true;
            }
        }
        
         if(esquerda.temCaixote()){
            if(acima.isParede() && estado.getCelulaAcima(esquerda).isParede()){
                return true;
            }
        }
                
        return false;
    }
}
