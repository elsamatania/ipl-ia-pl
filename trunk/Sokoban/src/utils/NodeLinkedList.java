/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import agente.Estado;
import agente.No;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Renato
 */
public class NodeLinkedList extends LinkedList<No> implements NodeCollection {

    private HashMap<Estado,No> contents;

    public NodeLinkedList() {
        super();
        contents = new HashMap<Estado, No>(128);
    }

    @Override
    public void add(int index, No element) {
        super.add(index, element);
        contents.put(element.getEstado(), element);
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
    public No remove(int index) {
        No no = super.remove(index);
        if (no != null) {
            contents.remove(no.getEstado());
        }
        return no;
    }

    @Override
    public boolean add(No n) {
        contents.put(n.getEstado(), n);
        return super.add(n);
    }

    @Override
    public void addFirst(No e) {
        super.addFirst(e);
        contents.put(e.getEstado(), e);
    }

    @Override
    public void addLast(No e) {
        super.addLast(e);
        contents.put(e.getEstado(), e);
    }

    @Override
    public No removeFirst() {
        No no = super.removeFirst();
        contents.remove(no.getEstado());
        return no;

    }

    @Override
    public No removeLast() {
        No no = super.removeLast();
        contents.remove(no.getEstado());
        return no;
    }
    
    @Override
    public boolean contemEstado(Estado e){
        return contents.containsKey(e);
    }
    
    public No getNo(Estado e){
        return contents.get(e);
    }
    
    public boolean removeNo(Estado e) {
        return remove(contents.get(e));
    }
}
