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
public class MoverBaixo extends OperadorSokoban {

    public MoverBaixo(double custo) {
        super(custo);
    }

    @Override
    public void executar(EstadoSokoban estado) {
        Celula celula = estado.getCelulaAbaixo(estado.getPosicaoAgente());
        estado.setPosicaoAgente(celula);

        if (celula.temCaixote()) {
            Celula celulaAbaixoCaixote = estado.getCelulaAbaixo(celula);
            celulaAbaixoCaixote.setCaixote(true);
            celula.setCaixote(false);
        }
    }

    @Override
    public boolean podeSerAplicado(EstadoSokoban estado) {
        Celula celula = estado.getCelulaAbaixo(estado.getPosicaoAgente());

        if (!isDestinoValidoAgente(estado, celula)) {
            return false;
        }

        if (celula.temCaixote()) {
            Celula celulaDestinoCaixote = estado.getCelulaAbaixo(celula);
            return isDestinoValidoCaixote(estado, celulaDestinoCaixote);
        }
        return true;
    }
    
    protected boolean isDeadlock(EstadoSokoban estado, Celula celula){
        if(celula.isObjetivo()){
            return false;
        }
        
        Celula direita = estado.getCelulaDireita(celula);
        Celula abaixo = estado.getCelulaAbaixo(celula);
        Celula esquerda = estado.getCelulaEsquerda(celula);

        if(abaixo.temCaixote()){
            if(direita.isParede() && estado.getCelulaDireita(abaixo).isParede()){
                return true;
            }
            if(esquerda.isParede() && estado.getCelulaEsquerda(abaixo).isParede()){
                return true;
            }
        }
        
        if(direita.temCaixote()){
            if(abaixo.isParede() && estado.getCelulaAbaixo(direita).isParede()){
                return true;
            }
        }
        
         if(esquerda.temCaixote()){
            if(abaixo.isParede() && estado.getCelulaAbaixo(esquerda).isParede()){
                return true;
            }
        }
                
        return false;
    }
}
