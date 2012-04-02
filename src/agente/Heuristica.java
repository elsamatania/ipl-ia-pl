package agente;


public abstract class Heuristica {
    protected Problema problema;

    
    public Heuristica(Problema problema){
        this.problema = problema;
    }

    
    public abstract double calcular(Estado estado);
}