/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import agente.Estado;
import agente.No;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author Renato
 */
public class NodePriorityQueue extends PriorityQueue<No> implements NodeCollection {
    private HashSet<Estado> estados;

    public NodePriorityQueue() {
        super();
        estados = new HashSet<Estado>();
    }

    @Override
    public boolean add(No e) {
        estados.add(e.getEstado());
        return super.add(e);
    }

    @Override
    public void clear() {
        super.clear();
        estados.clear();
    }

    @Override
    public boolean remove(Object o) {
        if(o instanceof No){
            No no = (No) o;
            estados.remove(no.getEstado());
        }
        return super.remove(o);
    }

    @Override
    public No remove() {
        No no = super.remove();
        estados.remove(no.getEstado());
        return no;
    }
    
    @Override
    public boolean contemEstado(Estado e){
        return estados.contains(e);
    }
    
}
