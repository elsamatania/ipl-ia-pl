/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Renato
 */
public class SokobanTester {

    private List<File> ficheirosTeste;
    private List<String> metodosPesquisa = Arrays.asList(SokobanResolver.getNomesMetodos());
    private List<String> heuristicas = Arrays.asList(SokobanResolver.getNomesHeuristicas());
    private SokobanResolver resolver;
    private File results;
    private BufferedWriter bw;
    private static final Logger logger = Logger.getLogger(SokobanTester.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("./sokoban.log");
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (SecurityException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public SokobanTester() {
        ficheirosTeste = new ArrayList<File>();
        ficheirosTeste.add(new File("src/puzzles/soko001.txt"));
        ficheirosTeste.add(new File("src/puzzles/soko002.txt"));
        ficheirosTeste.add(new File("src/puzzles/soko003.txt"));
        ficheirosTeste.add(new File("src/puzzles/soko004.txt"));
        results = new File("./resultados.csv");
    }

    public void testar() {
        char[][] problema;
        try {
            bw = new BufferedWriter(new PrintWriter(results, "UTF-8"));
            bw.write("Problema,Método,Heurística,Custo,Profundidade,TotalExpandidos,TotalGerados,TamanhoMáximoLista");
            bw.newLine();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Erro ao abrir ficheiro de resultados: " + ex, ex);
            System.exit(1);
        }

        //bw.write("Problema,Método,Heurística,");
        try {
            for (File file : ficheirosTeste) {
                try {
                    problema = SokobanResolver.lerFicheiroProblema(file);
                    resolver = new SokobanResolver(problema);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Erro na leitura do ficheiro {0}: {1}", new Object[]{file.getName(), ex});
                    continue;
                }

                //bw.write(file.getName() + ",");
                for (String metodo : metodosPesquisa) {
                    resolver.setMetodoPesquisa(metodo);
                    if (resolver.isInformado()) {
                        for (String heuristica : heuristicas) {
                            bw.write(file.getName() + ",");
                            bw.write(metodo + ",");
                            bw.write(heuristica + ",");
                            analisarProblema(heuristica);
                        }
                    } else {
                        bw.write(file.getName() + ",");
                        bw.write(metodo + ",");
                        bw.write(",");
                        analisarProblema(null);
                    }
                }
            }
            bw.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro: " + ex, ex);
        }
    }

    private void analisarProblema(String heuristica) throws Exception {
        resolver.resolverProblema(heuristica);
        StringBuilder sb = new StringBuilder();
        sb.append(resolver.getCustoSolucao());
        sb.append(",");
        sb.append(resolver.getProfundidadeSolucao());
        sb.append(",");
        sb.append(resolver.getTotalNosExpandidos());
        sb.append(",");
        sb.append(resolver.getTotalNosGerados());
        sb.append(",");
        sb.append(resolver.getTamanhoMaximoConjuntoAExpandir());
        bw.write(sb.toString());
        bw.newLine();
    }
}
