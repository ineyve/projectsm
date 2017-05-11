package gui;

import puzzle.PuzzleEvent;
import puzzle.PuzzleState;
import javax.swing.table.AbstractTableModel;
import puzzle.PuzzleListener;

public class PuzzleTableModel extends AbstractTableModel implements PuzzleListener{

    private PuzzleState puzzle;

    public PuzzleTableModel(PuzzleState puzzle) {
        if(puzzle == null){
            throw new NullPointerException("Puzzle cannot be null");
        }
        this.puzzle = puzzle;
        this.puzzle.addListener(this);
    }

    public int getColumnCount() {
        return puzzle.getNumLines();
    }

    public int getRowCount() {
        return puzzle.getNumColumns();
    }

    public Object getValueAt(int row, int col) {
        return new Integer(puzzle.getTileValue(row, col));
    }

    public void puzzleChanged(PuzzleEvent pe){
        fireTableDataChanged();
        try{
            Thread.sleep(500);
        }catch(InterruptedException ignore){
        }
    }

    public void setPuzzle(PuzzleState puzzle){
        if(puzzle == null){
          throw new NullPointerException("Puzzle cannot be null");
        }
        this.puzzle.removeListener(this);
        this.puzzle = puzzle;
        puzzle.addListener(this);
        fireTableDataChanged();
    }
}
