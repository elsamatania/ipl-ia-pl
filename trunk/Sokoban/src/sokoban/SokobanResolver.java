/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import agente.Agente;
import agente.Heuristica;
import agente.Operador;
import agente.Solucao;
import heuristicas.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import metodos.MetodoPesquisaInformadoBFS;
import sokoban.operadores.MoverBaixo;
import sokoban.operadores.MoverCima;
import sokoban.operadores.MoverDireita;
import sokoban.operadores.MoverEsquerda;

/**
 *
 * @author Renato
 */
public class SokobanResolver {

    private List<Operador> operadores;
    private ProblemaSokoban problema;
    private static Agente agente = new Agente();
    private Solucao solucao;
    private long tempoPesquisa;

    public SokobanResolver(char[][] tabela) {
        if (!isSokobanValido(tabela)) {
            throw new IllegalArgumentException("A tabela fornecida não corresponde a um problema de Sokoban válido.");
        }
        operadores = new ArrayList<Operador>();
        operadores.add(new MoverCima(1));
        operadores.add(new MoverDireita(1));
        operadores.add(new MoverBaixo(1));
        operadores.add(new MoverEsquerda(1));
        problema = new ProblemaSokoban(new EstadoSokoban(tabela), operadores);
    }

    public void setProblema(char[][] tabela) {
        problema = new ProblemaSokoban(new EstadoSokoban(tabela), operadores);
    }
    
    public static char[][] lerFicheiroProblema(File f) throws Exception {
        ArrayList<char[]> listaLinhas = new ArrayList<char[]>();
        BufferedReader br = new BufferedReader(new FileReader(f));

        String linha = br.readLine();
        while (linha != null) {
            listaLinhas.add(linha.toCharArray());
            linha = br.readLine();
        }
        
        br.close();

        char[][] tabela = listaLinhas.toArray(new char[0][0]);
        traduzirProblema(tabela);

        return tabela;
    }
    
    public static void traduzirProblema(char[][] tabela) {
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[i].length; j++) {
                switch (tabela[i][j]) {
                    case '#':
                        tabela[i][j] = 'P';
                        break;
                    case '.':
                        tabela[i][j] = 'O';
                        break;
                    case '@':
                        tabela[i][j] = 'A';
                        break;
                    case '$':
                        tabela[i][j] = 'C';
                        break;
                    case '*':
                        tabela[i][j] = 'X';
                        break;
                    case ' ':
                        tabela[i][j] = 'V';
                }
            }
        }
    }

    public static boolean isSokobanValido(char[][] tabela) {
        int numAgentes, numCaixotes, numObjetivos;
        numAgentes = numCaixotes = numObjetivos = 0;
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i].length != tabela[0].length) {
                return false;
            }
            for (int j = 0; j < tabela[0].length; j++) {
                switch (tabela[i][j]) {
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

        if (numAgentes != 1 || numCaixotes != numObjetivos || numCaixotes == 0) {
            return false;
        }

        return true;
    }

    public EstadoSokoban getPuzzleInicial() {
        return problema.getEstadoInicial();
    }

    public void resolverProblema(String nomeHeuristica) {
        Heuristica heuristica = null;
        if (isInformado()) {
            if (nomeHeuristica.equals(HeuristicaCaixotesForaSitio.NOME)) {
                heuristica = new HeuristicaCaixotesForaSitio(problema);
            } else if (nomeHeuristica.equals(HeuristicaManhattan.NOME)) {
                heuristica = new HeuristicaManhattan(problema);
            } else if (nomeHeuristica.equals(HeuristicaAgenteCaixoteMaisProximo.NOME)) {
                heuristica = new HeuristicaAgenteCaixoteMaisProximo(problema);
            } else if (nomeHeuristica.equals(HeuristicaSomaDistanciasMinimas.NOME)){
                heuristica = new HeuristicaSomaDistanciasMinimas(problema);
            } else if (nomeHeuristica.equals(HeuristicasDistanciasCaixoteAObjetivo.NOME)){
                heuristica = new HeuristicasDistanciasCaixoteAObjetivo(problema);
            } else if (nomeHeuristica.equals(HeuristicaEmpurrar.NOME)){
                heuristica = new HeuristicaEmpurrar(problema);
            }
            System.out.println("Teste: " + heuristica.calcular(getPuzzleInicial()));
        }

        long tempoInicial = System.nanoTime();
        solucao = agente.resolveProblema(problema, heuristica);
        tempoPesquisa = System.nanoTime() - tempoInicial;
    }

    public void mostrarSolucao() {
        if (temSolucao()) {
            problema.getEstadoInicial().aplicarOperadores(solucao.getOperadores());
        }
    }

    public boolean temSolucao() {
        return solucao != null;
    }

    public double getCustoSolucao() {
        if (temSolucao()) {
            return solucao.getCusto();
        }
        return 0;
    }

    public int getProfundidadeSolucao() {
        if (temSolucao()) {
            return solucao.getProfundidade();
        }
        return 0;

    }

    public long getTempoPesquisa() {
        return tempoPesquisa;
    }

    public long getTotalNosExpandidos() {
        return agente.getMetodoPesquisa().getTotalNosExpandidos();
    }

    public long getTotalNosGerados() {
        return agente.getMetodoPesquisa().getTotalNosGerados();
    }

    public long getTamanhoMaximoConjuntoAExpandir() {
        return agente.getMetodoPesquisa().getTamanhoMaximoConjuntoAExpandir();
    }

    public static String[] getNomesMetodos() {
        return agente.getNomesMetodos();
    }

    public static String[] getNomesHeuristicas() {
        String[] nomes = {HeuristicaCaixotesForaSitio.NOME, HeuristicaManhattan.NOME,
            HeuristicaAgenteCaixoteMaisProximo.NOME, HeuristicaSomaDistanciasMinimas.NOME,
            HeuristicasDistanciasCaixoteAObjetivo.NOME, HeuristicaEmpurrar.NOME};
        return nomes;
    }

    public void setMetodoPesquisa(String metodo) {
        agente.setMetodoPesquisa(metodo);
    }

    public boolean isInformado() {
        return agente.getMetodoPesquisa() instanceof MetodoPesquisaInformadoBFS;
    }

    public boolean isCompleto() {
        return agente.getMetodoPesquisa().isCompleto();
    }
}
