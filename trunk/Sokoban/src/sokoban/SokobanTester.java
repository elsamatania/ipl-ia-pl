/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import metodos.PesquisaAAsterisco;
import metodos.PesquisaLarguraPrimeiro;

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
    private static Logger logger;
    private ArrayList<HashMap<String, Double>> resultados;
    private HashMap<String, char[][]> problemas;

    public SokobanTester() {
        ficheirosTeste = new ArrayList<File>();
        resultados = new ArrayList<HashMap<String, Double>>();
        for (int i = 1; i < 53; i++) {
            if (i < 10) {
                ficheirosTeste.add(new File("src/puzzles/soko00" + i + ".txt"));
            } else {
                ficheirosTeste.add(new File("src/puzzles/soko0" + i + ".txt"));
            }
        }
        problemas = lerFicheiros();
        results = new File("./resultados.csv");
    }

    private HashMap<String, char[][]> lerFicheiros() {
        char[][] problema;
        HashMap<String, char[][]> lista = new HashMap<String, char[][]>();

        for (File file : ficheirosTeste) {
            try {
                problema = SokobanResolver.lerFicheiroProblema(file);
                lista.put(file.getName(), problema);
            } catch (Exception ex) {
                getLogger().log(Level.SEVERE, "Erro na leitura do ficheiro {0}: {1}", new Object[]{file.getName(), ex});
                continue;
            }
        }
        return lista;
    }

    public void testar() {
        try {
            bw = new BufferedWriter(new PrintWriter(results, "UTF-8"));
            bw.write("Problema,Método,Heurística,Custo,Profundidade,TotalExpandidos,TotalGerados,TamanhoMáximoLista");
            bw.newLine();
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Erro ao abrir ficheiro de resultados: " + ex, ex);
            System.exit(1);
        }

        try {
            for (Map.Entry<String, char[][]> entry : problemas.entrySet()) {
                String nome = entry.getKey();
                char[][] problema = entry.getValue();

                resolver = new SokobanResolver(problema);

                for (String metodo : metodosPesquisa) {
                    resolver.setMetodoPesquisa(metodo);
                    if (resolver.isInformado()) {
                        for (String heuristica : heuristicas) {
                            bw.write(nome + ",");
                            bw.write(metodo + ",");
                            bw.write(heuristica + ",");
                            analisarProblema(heuristica);
                        }
                    } else {
                        bw.write(nome + ",");
                        bw.write(metodo + ",");
                        bw.write(",");
                        analisarProblema(null);
                    }
                }
            }
            bw.close();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Erro: " + ex, ex);
        }
    }

    public void compararHeuristicas() {
        double custoLargura;
        long expandidosLargura;
        HashMap<String, Double> heurRes;
        int numPuzzles = 0;
        try {
            for (char[][] problema : problemas.values()) {
                resolver = new SokobanResolver(problema);
                resolver.setMetodoPesquisa(PesquisaLarguraPrimeiro.NOME);
                resolver.resolverProblema(null);
                expandidosLargura = resolver.getTotalNosExpandidos();

                heurRes = new HashMap<String, Double>();
                resolver.setMetodoPesquisa(PesquisaAAsterisco.NOME);
                for (String heuristica : heuristicas) {
                    resolver.resolverProblema(heuristica);
                    heurRes.put(heuristica, ((double) resolver.getTotalNosExpandidos() / expandidosLargura)
                            + (heurRes.get(heuristica) == null ? 0 : heurRes.get(heuristica)));
                }

                resultados.add(heurRes);
                numPuzzles++;
            }
            double media = 0;
            double temp = 0;
            bw = new BufferedWriter(new PrintWriter(new File("./comparaHeuristicas.csv"), "UTF-8"));
            for (String heuristica : heuristicas) {
                for (HashMap<String, Double> res : resultados) {
                    temp += res.get(heuristica);
                }
                bw.write(heuristica + ",");
                bw.write(Double.toString(temp / numPuzzles));
                bw.newLine();
                temp = 0;
            }
            bw.close();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Erro: " + ex, ex);
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

    private Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(SokobanTester.class.getName());
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
        return logger;
    }
}
