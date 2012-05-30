package agente;


public abstract class Heuristica<P extends Problema, E extends Estado> {
    protected P problema;

    
    public Heuristica(P problema){
        this.problema = problema;
    }

    
    public abstract double calcular(E estado);
}