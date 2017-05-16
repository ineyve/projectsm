package puzzle;

import agent.Action;
import agent.State;
import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleState extends State implements Cloneable {

    private Boolean DEBUG_ = false;
    
    public static final int SIZE = 6;
    private int[][][] matrix;
    private int[] lineBlank;
    private int[] columnBlank;

    //foreach peças todas -> mostrar no ecrã > criar sucessores -> executeActions
    public PuzzleState(int[][][] matrix) {
        lineBlank = new int[20];
        columnBlank = new int[20];
        int[] count = new int[20];
        System.out.println(ArrayIds.matrixToString(matrix));
        for(int a=0;a<count.length;a++) count[a]=0;
        
        this.matrix = new int[matrix.length][matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j][0] = matrix[i][j][0];
                this.matrix[i][j][1] = matrix[i][j][1];
                
                int id = this.matrix[i][j][0];
                if (this.matrix[i][j][1] != 0) {
                    lineBlank[id] = i;
                    columnBlank[id] = j;
                }
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp(int el) {
        if (lineBlank[el] > 0) {
            if (this.matrix[lineBlank[el] - 1][columnBlank[el]][1] == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean canMoveRight(int el) {
        if (columnBlank[el] < matrix.length - 1) {
            if (this.matrix[lineBlank[el]][columnBlank[el] + 1][1] == 0) {
                //Check for multiple pieces
                return true;
            }
        }
        return false;
    }

    public boolean canMoveDown(int el) {
        if (lineBlank[el] < matrix.length - 1) {
            int ownId = this.matrix[lineBlank[el]][columnBlank[el]][1];
            if (this.matrix[lineBlank[el] + 1][columnBlank[el]][1] == 0) {
                //Check for multiple pieces
                if(ownId == 5 && lineBlank[el] + 1 < matrix.length-1)
                    return true;
                else if(ownId == 3)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public boolean canMoveLeft(int el) {
        if (columnBlank[el] > 0) {
            if (this.matrix[lineBlank[el]][columnBlank[el] - 1][1] == 0) {
                return true;
            }
        }
        return false;
    }

    /*
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class EightPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    public void moveUp(int el) {
        if(DEBUG_)System.out.print("\nU"+ArrayIds.matrixToString(matrix)+"\n");
        
        int ownId = this.matrix[lineBlank[el]][columnBlank[el]][1];
        int pieces = 1;
        if(ownId == 5)
        {
            pieces = 2;
        }
        else if(ownId == 7)
        {
            pieces = 3;
        }
        else if(ownId == 9)
        {
            pieces = 4;
        }
        int aux;
        for(int c = 0; c < pieces; c++)
        {
            aux = matrix[lineBlank[el]+c][columnBlank[el]][0];
            matrix[lineBlank[el]+c][columnBlank[el]][0] = matrix[lineBlank[el]-1+c][columnBlank[el]][0];
            matrix[lineBlank[el]-1+c][columnBlank[el]][0] = aux;

            aux = matrix[lineBlank[el]+c][columnBlank[el]][1];
            matrix[lineBlank[el]+c][columnBlank[el]][1] = matrix[--lineBlank[el]+c][columnBlank[el]][1];
            matrix[lineBlank[el]+c][columnBlank[el]][1] = aux;
        }
    }

    public void moveRight(int el) {
        if(DEBUG_)System.out.print("\nR"+ArrayIds.matrixToString(matrix)+"\n");
        int aux = matrix[lineBlank[el]][columnBlank[el]][0];
        matrix[lineBlank[el]][columnBlank[el]][0] = matrix[lineBlank[el]][columnBlank[el]+1][0];
        matrix[lineBlank[el]][columnBlank[el]+1][0] = aux;
		
		aux = matrix[lineBlank[el]][columnBlank[el]][1];
        matrix[lineBlank[el]][columnBlank[el]][1] = matrix[lineBlank[el]][++columnBlank[el]][1];
        matrix[lineBlank[el]][columnBlank[el]][1] = aux;
    }

    public void moveDown(int el) {
        if(DEBUG_)System.out.print("\nD"+ArrayIds.matrixToString(matrix)+"\n");
        int ownId = this.matrix[lineBlank[el]][columnBlank[el]][1];
        int pieces = 1;
        if(ownId == 5)
        {
            pieces = 2;
        }
        else if(ownId == 7)
        {
            pieces = 3;
        }
        else if(ownId == 9)
        {
            pieces = 4;
        }
        int aux;
        for(int c = 0; c < pieces; c++)
        {
            aux = matrix[lineBlank[el]+c][columnBlank[el]][0];
            matrix[lineBlank[el]+c][columnBlank[el]][0] = matrix[lineBlank[el]+1+c][columnBlank[el]][0];
            matrix[lineBlank[el]+1+c][columnBlank[el]][0] = aux;

            aux = matrix[lineBlank[el]+c][columnBlank[el]][1];
            matrix[lineBlank[el]+c][columnBlank[el]][1] = matrix[++lineBlank[el]+c][columnBlank[el]][1];
            matrix[lineBlank[el]+c][columnBlank[el]][1] = aux;
        }
        /*
        int aux = matrix[lineBlank[el]][columnBlank[el]][0];
        matrix[lineBlank[el]][columnBlank[el]][0] = matrix[lineBlank[el]+1][columnBlank[el]][0];
        matrix[lineBlank[el]+1][columnBlank[el]][0] = aux;
		
		aux = matrix[lineBlank[el]][columnBlank[el]][1];
        matrix[lineBlank[el]][columnBlank[el]][1] = matrix[++lineBlank[el]][columnBlank[el]][1];
        matrix[lineBlank[el]][columnBlank[el]][1] = aux;*/
    }

    public void moveLeft(int el) {
        if(DEBUG_)System.out.print("\nL"+ArrayIds.matrixToString(matrix)+"\n");
        int aux = matrix[lineBlank[el]][columnBlank[el]][0];
        matrix[lineBlank[el]][columnBlank[el]][0] = matrix[lineBlank[el]][columnBlank[el]-1][0];
        matrix[lineBlank[el]][columnBlank[el]-1][0] = aux;
		
		aux = matrix[lineBlank[el]][columnBlank[el]][1];
        matrix[lineBlank[el]][columnBlank[el]][1] = matrix[lineBlank[el]][--columnBlank[el]][1];
        matrix[lineBlank[el]][columnBlank[el]][1] = aux;
    }

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {

        //verificar se naquela linha já está código do mesmo tipo
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column][1];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PuzzleState)) {
            return false;
        }

        PuzzleState o = (PuzzleState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public Object clone() {
        return new PuzzleState(matrix);
    }
    //Listeners
    private transient ArrayList<PuzzleListener> listeners = new ArrayList<PuzzleListener>(3);

    public synchronized void removeListener(PuzzleListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(PuzzleListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(PuzzleEvent pe) {
        for (PuzzleListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }

    int getColumnCar() {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[2][i][1] == 1) {
                return i;
            }
        }
        return 0;
    }
}
