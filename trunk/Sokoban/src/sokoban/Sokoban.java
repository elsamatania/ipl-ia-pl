/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Lino
 */
public class Sokoban {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[][] chars = null;
        try {
            chars = lerFicheiroProblema(new File("G:/chars.txt"));
        } catch (Exception ex) {
            System.out.println("Não foi possível ler o ficheiro: " + ex);
        }
        
        if(eValido(chars)){
            System.out.println("Ficheiro válido! :)");
        } else{
            System.out.println("Ficheiro inválido... :(");
        }
    }
    
    public static char[][] lerFicheiroProblema(File f) throws Exception{
        ArrayList<char[]> listaLinhas = new ArrayList<char[]>();
        BufferedReader br = new BufferedReader(new FileReader(f));

        String linha = br.readLine();
        while(linha != null){
            listaLinhas.add(linha.toCharArray());
            linha = br.readLine();
        }

        char[][] tabela = new char[listaLinhas.size()][listaLinhas.get(0).length];

        return (char[][]) listaLinhas.toArray(tabela);
    }
    
    public static boolean eValido(char[][] tabela){
        int numAgentes, numCaixotes, numObjetivos;
        numAgentes = numCaixotes = numObjetivos = 0;
        for (int i = 0; i < tabela.length; i++) {
            if(tabela[i].length != tabela[0].length){
                return false;
            }
            for (int j = 0; j < tabela[0].length; j++) {
                switch(tabela[i][j]){
                    case 'P':
                    case 'V':
                        break;
                    case 'O':
                        numObjetivos++;
                        break;
                    case 'C':
                        numCaixotes++;
                        break;
                    case 'X':
                        numObjetivos++;
                        numCaixotes++;
                        break;
                    case 'A':
                        numAgentes++;
                        break;
                    default:
                        return false;
                }
            } 
        }
        
        if(numAgentes != 1 || numCaixotes != numObjetivos || numCaixotes == 0){
            return false;
        }
        
        return true;
    }
}
