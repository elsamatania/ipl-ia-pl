/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import metodos.*;

/**
 *
 * @author Renato
 */
public class SokobanTester {

    private List<File> ficheirosTeste;
    private List<String> metodosPesquisa;
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
        resolver = new SokobanResolver();
        metodosPesquisa = Arrays.asList(resolver.getNomesMetodos());
        
        Locale.setDefault(Locale.getDefault());
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
            bw.write("Problema;Método;Heurística;Sucesso;Custo;DiferençaCustoÓtimo;TotalExpandidos;TaxaExpandidos;TotalGerados;TamanhoMáximoLista");
            bw.newLine();
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Erro ao abrir ficheiro de resultados: " + ex, ex);
            System.exit(1);
        }

        List<String> metodos = new ArrayList<String>(metodosPesquisa);
        //senão nunca mais daqui saíamos à espera do relatório
        metodos.remove(PesquisaProfundidadePrimeiro.NOME);
        metodos.remove(PesquisaProfundidadeLimitada.NOME);
        metodos.remove(PesquisaPorAprofundamentoProgressivo.NOME);
        metodos.remove(IDAAsterisco.NOME);

        try {
            for (Map.Entry<String, char[][]> entry : problemas.entrySet()) {
                String nome = entry.getKey();
                char[][] problema = entry.getValue();

                resolver.setProblema(problema);
                resolver.setMetodoPesquisa(PesquisaLarguraPrimeiro.NOME);
                resolver.resolverProblema(null);
                double solucaoOtima = resolver.getCustoSolucao();
                long expandidosLargura = resolver.getTotalNosExpandidos();

                for (String metodo : metodos) {
                    resolver.setMetodoPesquisa(metodo);
                    if (resolver.isInformado()) {
                        for (String heuristica : heuristicas) {
                            bw.write(nome + ";");
                            bw.write(metodo + ";");
                            bw.write(heuristica + ";");
                            analisarProblema(heuristica, solucaoOtima, expandidosLargura);
                        }
                    } else {
                        bw.write(nome + ";");
                        bw.write(metodo + ";");
                        bw.write(";");
                        analisarProblema(null, solucaoOtima, expandidosLargura);
                    }
                }
            }
            bw.close();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Erro: " + ex, ex);
        }
    }

    public void testarSofrega() {
        long expandidosLargura;
        double custoLargura;
        double somaTaxas;
        double somaDifCustos;
        double numPuzzles;
        try {
            bw = new BufferedWriter(new PrintWriter(new File("./sofrega.csv"), "UTF-8"));
            bw.write("Heurística;Média da taxa de nós expandidos;Média da diferença em relação à solução ótima");
            bw.newLine();
            for (String heuristica : heuristicas) {
                somaTaxas = 0;
                somaDifCustos = 0;
                numPuzzles = 0;
                for (char[][] problema : problemas.values()) {
                    resolver.setProblema(problema);
                    resolver.setMetodoPesquisa(PesquisaLarguraPrimeiro.NOME);
                    resolver.resolverProblema(null);
                    expandidosLargura = resolver.getTotalNosExpandidos();
                    custoLargura = resolver.getCustoSolucao();
                    resolver.setMetodoPesquisa(PesquisaSofrega.NOME);
                    resolver.resolverProblema(heuristica);
                    somaTaxas += ((double)resolver.getTotalNosExpandidos() / (double) expandidosLargura);
                    somaDifCustos += (resolver.getCustoSolucao() - custoLargura);
                    numPuzzles++;
                }
                double mediaTaxas = somaTaxas/numPuzzles;
                double mediaDif = somaDifCustos/numPuzzles;
                bw.write(heuristica + ";");
                bw.write(mediaTaxas + ";");
                bw.write(mediaDif + ";");
                bw.newLine();
            }
            bw.close();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Erro: " + ex, ex);
        }
    }
    
    public void testarHeuristicas(){
        long expandidosLargura;
        double custoLargura;
        double somaTaxas;
        double somaDifCustos;
        double numPuzzles;
        double numPuzzlesNaoOtimos;
        try {
            bw = new BufferedWriter(new PrintWriter(new File("./heuristicas.csv"), "UTF-8"));
            bw.write("Heurística;Média da taxa de nós expandidos;Taxa de soluções não ótimas;Média do desvio quanto à solução ótima");
            bw.newLine();
            for (String heuristica : heuristicas) {
                somaTaxas = 0;
                somaDifCustos = 0;
                numPuzzles = 0;
                numPuzzlesNaoOtimos = 0;
                for (char[][] problema : problemas.values()) {
                    resolver.setProblema(problema);
                    resolver.setMetodoPesquisa(PesquisaLarguraPrimeiro.NOME);
                    resolver.resolverProblema(null);
                    expandidosLargura = resolver.getTotalNosExpandidos();
                    custoLargura = resolver.getCustoSolucao();
                    resolver.setMetodoPesquisa(PesquisaAAsterisco.NOME);
                    resolver.resolverProblema(heuristica);
                    somaTaxas += ((double)resolver.getTotalNosExpandidos() / (double) expandidosLargura);
                    if(resolver.getCustoSolucao() > custoLargura){
                        somaDifCustos += (resolver.getCustoSolucao() - custoLargura);
                        numPuzzlesNaoOtimos++;
                    }
                    numPuzzles++;
                }
                double mediaDif = 0;
                double mediaTaxas = somaTaxas/numPuzzles;
                if(numPuzzlesNaoOtimos > 0){
                    mediaDif = somaDifCustos/numPuzzlesNaoOtimos;
                }
                double taxaNaoOtimas = numPuzzlesNaoOtimos/numPuzzles;
                bw.write(heuristica + ";");
                bw.write(mediaTaxas + ";");
                bw.write(taxaNaoOtimas + ";");
                bw.write(mediaDif + ";");
                bw.newLine();
            }
            bw.close();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Erro: " + ex, ex);
        }
    }
    
        public void testarFeixe() {
        long expandidosLargura;
        double custoLargura;
        double somaTaxas;
        double somaDifCustos;
        double numPuzzles;
        int numNaoResolvidos;
        try {
            bw = new BufferedWriter(new PrintWriter(new File("./feixe.csv"), "UTF-8"));
            bw.write("Heurística;Taxa de resolução;Média da taxa de nós expandidos;Média da diferença em relação à solução ótima");
            bw.newLine();
            for (String heuristica : heuristicas) {
                somaTaxas = 0;
                somaDifCustos = 0;
                numPuzzles = 0;
                numNaoResolvidos = 0;
                for (char[][] problema : problemas.values()) {
                    resolver.setProblema(problema);
                    resolver.setMetodoPesquisa(PesquisaLarguraPrimeiro.NOME);
                    resolver.resolverProblema(null);
                    expandidosLargura = resolver.getTotalNosExpandidos();
                    custoLargura = resolver.getCustoSolucao();
                    resolver.setMetodoPesquisa(PesquisaEmFeixe.NOME);
                    resolver.resolverProblema(heuristica);
                    if(resolver.temSolucao()){
                        somaTaxas += ((double)resolver.getTotalNosExpandidos() / (double) expandidosLargura);
                        somaDifCustos += (resolver.getCustoSolucao() - custoLargura);
                    } else {
                        numNaoResolvidos++;
                    }
                    numPuzzles++;
                }
                double taxaResolucao = (double)(numPuzzles-numNaoResolvidos)/(double)numPuzzles;
                double mediaTaxas = somaTaxas/(double)(numPuzzles-numNaoResolvidos);
                double mediaDif = somaDifCustos/(double)(numPuzzles-numNaoResolvidos);
                bw.write(heuristica + ";");
                bw.write(taxaResolucao + ";");
                bw.write(mediaTaxas + ";");
                bw.write(mediaDif + ";");
                bw.newLine();
            }
            bw.close();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Erro: " + ex, ex);
        }
    }

    private void analisarProblema(String heuristica, double solucaoOtima, long expandidosLargura) throws Exception {
        resolver.resolverProblema(heuristica);
        StringBuilder sb = new StringBuilder();
        sb.append(resolver.temSolucao() ? "Sim" : "Não");
        sb.append(";");
        sb.append(String.format("%.1f", resolver.getCustoSolucao()));
        sb.append(";");
        sb.append(String.format("%.1f", resolver.getCustoSolucao() - solucaoOtima));
        sb.append(";");
        sb.append(resolver.getTotalNosExpandidos());
        sb.append(";");
        double taxaExpandidos = (double) resolver.getTotalNosExpandidos() / (double) expandidosLargura;
        sb.append(String.format("%.3f", taxaExpandidos));
        sb.append(";");
        sb.append(resolver.getTotalNosGerados());
        sb.append(";");
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
