/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import agente.Estado;
import agente.No;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Renato
 */
public class NodePriorityQueue extends PriorityQueue<No> implements NodeCollection {

    private HashMap<Estado, No> contents;

    public NodePriorityQueue() {
        super();
        contents = new HashMap<Estado, No>(128);
    }

    @Override
    public boolean add(No e) {
        contents.put(e.getEstado(), e);
        return super.add(e);
    }

    @Override
    public void clear() {
        super.clear();
        contents.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof No) {
            No no = (No) o;
            contents.remove(no.getEstado());
        }
        return super.remove(o);
    }

    @Override
    public No remove() {
        No no = super.remove();
        contents.remove(no.getEstado());
        return no;
    }

    @Override
    public boolean contemEstado(Estado e) {
        return contents.containsKey(e);
    }

    public No getNo(Estado e) {
        return contents.get(e);
    }

    public boolean removeNo(Estado e) {
        return remove(contents.get(e));
    }
}
