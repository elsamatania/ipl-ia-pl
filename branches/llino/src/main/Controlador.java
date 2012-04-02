/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Leonardo Lino
 */
public class Controlador {
    
    JanelaPrincipal jp = new JanelaPrincipal();
    AreaDesenho ad = new AreaDesenho();

    public Controlador() {
    }
    
    public void setJP(JanelaPrincipal jp){
        this.jp = jp;
    }
    
    public void setAD (AreaDesenho ad){
        this.ad = ad;
    }
    
}
