/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;

/**
 *
 * @author Leonardo Lino
 */
public class Puzzle {
    
    private int gridX;
    private int gridY;
    LinkedList<String> linhas;

    public Puzzle() {
        linhas = new LinkedList<String>();
    }
    
    public void addLinha(String linha){
        this.linhas.add(linha);
    }
    
    public String outputString() {
        String str = null;
        for (String linha : linhas) {
            if (str == null) {
                str = linha;
            } else {
                str = str + linha;
            }
        }

        return str;
    }
    
    
    
}
