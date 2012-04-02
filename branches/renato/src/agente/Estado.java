package agente;

import java.util.List;

/**
 * <p>Title: Algoritmo Geral de Pesquisa</p>
 * <p>Description: Classe abstracta que representa um estado do um problema.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ESTG</p>
 * @author Carlos Grilo
 * @version 1.0
 */
public abstract class Estado {

    /**
     * Operador que deu origem a este estado.
     */
    protected Operador operador;


    /**
     * Construtor que permite construir um estado.
     */
    public Estado(){
    }


    /**
     * Devolve o operador que deu origem a este estado.
     * @return Operador que deu origem a este estado.
     */
    public Operador getOperador(){
        return operador;
    }


    /**
     * Permite definir o operador que deu origem a este estado.
     * @param operador Operador que deu origem a este estado.
     */
    public void setOperador(Operador operador){
        this.operador = operador;
    }

    //serve para mostrar uma solução
    public abstract void aplicarOperadores(List<Operador> operadores);


    @Override
    public int hashCode(){
        return toString().hashCode();
    }
}