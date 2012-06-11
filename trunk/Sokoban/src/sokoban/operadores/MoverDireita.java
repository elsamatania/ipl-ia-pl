/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.operadores;

import sokoban.EstadoSokoban;
import sokoban.Celula;

/**
 *
 * @author Leonardo Lino
 */
public class MoverDireita extends OperadorSokoban {

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

        if (!isDestinoValidoAgente(estado, celula)) {
            return false;
        }

        if (celula.temCaixote()) {
            Celula celulaDestinoCaixote = estado.getCelulaDireita(celula);
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
        Celula abaixo = estado.getCelulaAbaixo(celula);
        
        if(acima.temCaixote()){
            if(direita.isParede() && estado.getCelulaDireita(acima).isParede()){
                return true;
            }
        }
        
        
        if(abaixo.temCaixote()){
            if(direita.isParede() && estado.getCelulaDireita(abaixo).isParede()){
                return true;
            }
        }
        
        if(direita.temCaixote()){
            if(acima.isParede() && estado.getCelulaAcima(direita).isParede()){
                return true;
            }
            if(abaixo.isParede() && estado.getCelulaAbaixo(direita).isParede()){
                return true;
            }
        }
                
        return false;
    }
}
