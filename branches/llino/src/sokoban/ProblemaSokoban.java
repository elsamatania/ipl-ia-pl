/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Operador;
import agente.Problema;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Renato
 */
public class ProblemaSokoban extends Problema<EstadoSokoban> {

    private LinkedList<Point> posicoesObjetivo;
    
    public ProblemaSokoban(EstadoSokoban estadoInicial, List<Operador> listaOperadores) {
        super(estadoInicial, listaOperadores);
        posicoesObjetivo = estadoInicial.getPosicoesObjetivo();
    }

    @Override
    public boolean isObjetivoAtingido(EstadoSokoban e) {
        for (Point p : posicoesObjetivo) {
            if(!e.getCelula(p).temCaixote()){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<EstadoSokoban> aplicarOperadores(EstadoSokoban e) {
        LinkedList<EstadoSokoban> estados = new LinkedList<EstadoSokoban>();
        for (Operador o : listaOperadores) {
            if(o.podeSerAplicado(e)){
                EstadoSokoban copia = e.clone();
                o.executar(copia);
                estados.add(copia);
            }
        }
        return estados;
    }
    
}
