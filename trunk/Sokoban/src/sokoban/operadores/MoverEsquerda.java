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
public class MoverEsquerda extends OperadorSokoban {

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

        if (!isDestinoValidoAgente(estado, celula)) {
            return false;
        }

        if (celula.temCaixote()) {
            Celula celulaDestinoCaixote = estado.getCelulaEsquerda(celula);
            return isDestinoValidoCaixote(estado, celulaDestinoCaixote);
        }
        
        return true;
    }
    
    protected boolean isDeadlock(EstadoSokoban estado, Celula celula){
        if(celula.isObjetivo()){
            return false;
        }
        
        Celula acima = estado.getCelulaAcima(celula);
        Celula abaixo = estado.getCelulaAbaixo(celula);
        Celula esquerda = estado.getCelulaEsquerda(celula);
        
        if(acima.temCaixote()){
            if(esquerda.isParede() && estado.getCelulaEsquerda(acima).isParede()){
                return true;
            }
        }
        
        
        if(abaixo.temCaixote()){
            if(esquerda.isParede() && estado.getCelulaEsquerda(abaixo).isParede()){
                return true;
            }
        }
        
         if(esquerda.temCaixote()){
            if(acima.isParede() && estado.getCelulaAcima(esquerda).isParede()){
                return true;
            }
            if(abaixo.isParede() && estado.getCelulaAbaixo(esquerda).isParede()){
                return true;
            }
        }
                
        return false;
    }
}
