package main;

import java.util.EventObject;
import javax.swing.table.AbstractTableModel;
import sokoban.Celula;
import sokoban.EstadoSokoban;

public class PuzzleTableModel extends AbstractTableModel implements PuzzleListener {

    private EstadoSokoban puzzle;

    public PuzzleTableModel(EstadoSokoban puzzle) {
        if(puzzle == null)
            throw new NullPointerException("Puzzle nao pode ser null");
        this.puzzle = puzzle;
        this.puzzle.addPuzzleListener(this);
    }

    @Override
    public int getColumnCount() {
        return puzzle.getNumColunas();
    }

    @Override
    public int getRowCount() {
        return puzzle.getNumLinhas();
    }

    @Override
    public Celula getValueAt(int row, int col) {
        return puzzle.getValueAt(row, col);
    }

    @Override
    public void puzzleChanged(EventObject eo){
        fireTableDataChanged();
        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
        }
    }

    public void setPuzzle(EstadoSokoban puzzle){
        if(puzzle == null)
          throw new IllegalArgumentException("Puzzle não pode ser null");
        puzzle.removePuzzleListener(this);
        this.puzzle = puzzle;
        puzzle.addPuzzleListener(this);
        fireTableDataChanged();
    }
}
