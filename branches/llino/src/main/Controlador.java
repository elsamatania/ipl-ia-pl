/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Leonardo Lino
 */
public class Controlador {
    
    private static JanelaPrincipal jp;
    private static AreaDesenho ad;

    public Controlador() {
    }
    
    public static void main(String[] args) {
        jp = new JanelaPrincipal();
        jp.setVisible(true);
        ad = jp.areaDesenho;

    }

    
     

    
    
}
