/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import agente.Estado;
import agente.No;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Renato
 */
public class NodeLinkedList extends LinkedList<No> implements NodeCollection {

    private HashSet<Estado> estados;

    public NodeLinkedList() {
        super();
        estados = new HashSet<Estado>();
    }

    @Override
    public void add(int index, No element) {
        super.add(index, element);
        estados.add(element.getEstado());
    }

    @Override
    public void clear() {
        super.clear();
        estados.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof No) {
            No no = (No) o;
            estados.remove(no.getEstado());
        }
        return super.remove(o);
    }

    @Override
    public No remove(int index) {
        No no = super.remove(index);
        if (no != null) {
            estados.remove(no.getEstado());
        }
        return no;
    }

    @Override
    public boolean add(No e) {
        estados.add(e.getEstado());
        return super.add(e);
    }

    @Override
    public void addFirst(No e) {
        super.addFirst(e);
        estados.add(e.getEstado());
    }

    @Override
    public void addLast(No e) {
        super.addLast(e);
        estados.add(e.getEstado());
    }

    @Override
    public No removeFirst() {
        No no = super.removeFirst();
        estados.remove(no.getEstado());
        return no;

    }

    @Override
    public No removeLast() {
        No no = super.removeLast();
        estados.remove(no.getEstado());
        return no;
    }
    
    @Override
    public boolean contemEstado(Estado e){
        return estados.contains(e);
    }
}
